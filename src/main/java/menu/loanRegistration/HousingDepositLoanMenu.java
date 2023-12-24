package menu.loanRegistration;

import entity.Installment;
import entity.Loan;
import entity.LoanCategory;
import entity.person.Student;
import entity.person.StudentSpouse;
import menu.MainMenu;
import service.InstallmentService;
import service.LoanCategoryService;
import service.LoanService;
import service.StudentSpouseService;
import utility.ApplicationContext;
import utility.SecurityContext;
import utility.Validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


@SuppressWarnings("unused")
public class HousingDepositLoanMenu {

    LoanService loanService = ApplicationContext.getLoanService();
    LoanCategoryService loanCategoryService = ApplicationContext.getLoanCategoryService();
    StudentSpouseService studentSpouseService = ApplicationContext.getStudentSpouseService();
    InstallmentService installmentService = ApplicationContext.getInstallmentService();

    StudentSpouse studentSpouse;
    Student student;
    Scanner scanner = new Scanner(System.in);


    public void housingDepositLoanRegistration() {
        student = SecurityContext.getCurrentUser();
        LocalDate date = SecurityContext.getTodayDate();
        if (student.isMarried()) {
            fillSpouseInfo();
            if (loanService.housingDepositLoanRegistration(student)) {
                Loan loan = new Loan();
                LoanCategory category = loanCategoryService.findLoanCategoryForHousingDepositLoan(student);
                loan.setCreationDate(date);
                loan.setLoanCategory(category);
                loan.setStudent(student);

                getAddress(loan);

                LoanRegistrationMenu.addCardInfo();

                loanService.saveOrUpdate(loan);

                SecurityContext.fillContext(loan);
                List<Installment> installments = installmentService.fillInstallment();
                for (Installment installment : installments) {
                    installmentService.saveOrUpdate(installment);
                }
                System.out.println("SUCCESSFULLY REGISTERED \n");
            }
        } else
            System.out.println("YOU ARE NOT QUALIFIED ! \n");
    }

    private void getAddress(Loan loan) {

        System.out.print("PLEASE ENTER YOUR FULL ADDRESS : ");
        String fullAddress = scanner.next();
        student.setFullAddress(fullAddress);

        System.out.print("PLEASE ENTER YOUR RENTAL NUMBER : ");
        String rentalNumber = scanner.next();
        loan.setRentalNumber(rentalNumber);
    }

    public void fillSpouseInfo() {
        studentSpouse = new StudentSpouse();
        System.out.println("*** PLEASE ENTER YOUR SPOUSE INFO : ");
        System.out.print("SPOUSE FIRST NAME : ");
        String spouseFirstname = scanner.next();
        studentSpouse.setFirstname(spouseFirstname);

        System.out.print("SPOUSE LAST NAME : ");
        String spouseLastname = scanner.next();
        studentSpouse.setLastname(spouseLastname);

        System.out.print("SPOUSE FATHER NAME : ");
        String spouseFatherName = scanner.next();
        studentSpouse.setFatherName(spouseFatherName);

        System.out.print("SPOUSE MOTHER NAME : ");
        String spouseMotherName = scanner.next();
        studentSpouse.setMotherName(spouseMotherName);

        System.out.print("SPOUSE NATIONAL ID : ");
        boolean spouseValidNationalId = true;
        String SpouseNationalId;
        while (spouseValidNationalId) {
            SpouseNationalId = scanner.next();
            if (Validation.isValidNationalId(SpouseNationalId)) {
                studentSpouse.setNationalId(SpouseNationalId);
                spouseValidNationalId = false;
            } else System.out.println("PLEASE ENTER VALID INFO");
        }


        System.out.print("SPOUSE NATIONAL CODE : ");
        boolean spouseValidNationalCode = true;
        String nationalCode;
        while (spouseValidNationalCode) {
            nationalCode = scanner.next();
            if (Validation.isValidStudentCode(nationalCode)) {
                studentSpouse.setNationalCode(nationalCode);
                spouseValidNationalCode = false;
            } else System.out.println("PLEASE ENTER VALID INFO");
        }


        System.out.print("SPOUSE DATE OF BIRTHDAY (in this format yyyy-MM-dd) : ");
        String spouseDob = scanner.next();
        DateFormat spouseFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            studentSpouse.setBirthDate(spouseFormatter.parse(spouseDob));
        } catch (ParseException e) {
            e.printStackTrace();
            housingDepositLoanRegistration();
        }

        isStudentCheck();
        studentSpouseService.saveOrUpdate(studentSpouse);
        student.setSpouse(studentSpouse);
    }

    private void isStudentCheck() {
        String isStudentText = """
                IS YOUR SPOUSE STUDENT :
                1 - YES
                2 - NO
                """;
        System.out.println(isStudentText);
        int isStudentInput = new MainMenu().input();
        switch (isStudentInput) {
            case 1 -> studentSpouse.setIsStudent(true);
            case 2 -> studentSpouse.setIsStudent(false);
            default -> {
                System.out.println("INVALID INPUT ! ");
                housingDepositLoanRegistration();
            }
        }
    }
}