package menu.loanRegistration;

import entity.Card;
import entity.Installment;
import entity.Loan;
import entity.enumeration.BankType;
import entity.person.Student;
import lombok.NoArgsConstructor;
import menu.MainMenu;
import service.CardService;
import service.InstallmentService;
import service.LoanService;
import service.StudentService;
import utility.ApplicationContext;
import utility.SecurityContext;
import utility.Validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor
@SuppressWarnings("unused")
public class LoanRegistrationMenu {

    private static final StudentService studentService = ApplicationContext.getStudentService();
    private static final CardService cardService = ApplicationContext.getCardService();
    private static final Scanner scanner = new Scanner(System.in);
    private static final MainMenu menu = new MainMenu();
    private static final Card card = new Card();
    String username;
    String password;
    String today;

    public LoanRegistrationMenu(String username, String password, String today) {
        this.username = username;
        this.password = password;
        this.today = today;
        login();
    }

    public void login() {
        if (studentService.existByUserNameAndPassword(username, password)) {
            System.out.println("YOU SUCCESSFULLY LOGGED IN \n");
            Student student = studentService.findByUserName(username);
            SecurityContext.fillContext(student);
            chooseLoanType();
        } else {
            System.out.println("INVALID USERNAME AND PASSWORD \n");
            menu.start();
        }
    }

    public void chooseLoanType() {

        Student student = SecurityContext.getCurrentUser();
        LocalDate date = SecurityContext.getTodayDate();

        if (!studentService.isStudentGraduated(student, date) && isRightTime()) {
            String text = """
                    *** WHICH TYPE OF LOAN DO YOU WANT REGISTER TO : ***
                    1 - EDUCATIONAL LOAN
                    2 - HOUSING DEPOSIT LOAN
                    3 - TUITION LOAN
                    4 - BACK TO MAIN MENU
                    """;
            System.out.println(text);
            int chooseType = menu.input();
            switch (chooseType) {

                case 1 -> new EducationalLoanMenu().educationalLoanRegistration();

                case 2 -> new HousingDepositLoanMenu().housingDepositLoanRegistration();

                case 3 -> new TuitionLoanMenu().tuitionLoanRegistration();

                case 4 -> menu.start();

                default -> {
                    System.out.println("INVALID INPUT ! ");
                    chooseLoanType();
                }
            }
        } else {
            System.out.println(" YOU ARE NOT ALLOWED TO ENTER ! \n");
            menu.start();
        }
    }

    public static void addCardInfo() {

        System.out.println("***PLEASE ENTER YOUR CARD INFORMATION : ***\n");

        System.out.print("CARD NUMBER : ");
        boolean isValidCardNumber = true;
        while (isValidCardNumber) {
            String cardNumber = scanner.next();
            if (Validation.cardValidation(cardNumber)) {
                card.setCardNumber(cardNumber);
                isValidCardNumber = false;
            } else System.out.println(" PLEASE ENTER VALID CARD NUMBER !");
        }


        System.out.print("CVV2 : ");
        boolean isValidCvv = true;
        while (isValidCvv) {
            int cvv2 = menu.input();
            if (Validation.isValidCvv2(cvv2)) {
                card.setCvv2(cvv2);
                isValidCvv = false;
            } else System.out.println(" PLEASE ENTER VALID CVV !");
        }


        System.out.print("EXPIRATION DATE (in this format yyyy-MM-dd) : ");
        String expirationDate = scanner.next();
        DateFormat dareFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            card.setExpirationDate(dareFormatter.parse(expirationDate));
        } catch (ParseException e) {
            e.printStackTrace();
            addCardInfo();
        }

        chooseBankType();
        Student user = SecurityContext.getCurrentUser();
        card.setStudent(user);
        cardService.saveOrUpdate(card);
    }

    public static void chooseBankType() {
        String bankText = """
                ________________________________
                                
                PLEASE CHOOSE YOUR BANK :
                1 - MELLI
                2 - REFAH
                3 - TEJARAT
                4 - MASKAN
                                
                ________________________________
                """;
        System.out.println(bankText);
        int bankInput = menu.input();
        BankType bankType = null;
        switch (bankInput) {
            case 1 -> bankType = BankType.MELLI;
            case 2 -> bankType = BankType.REFAH;
            case 3 -> bankType = BankType.TEJARAT;
            case 4 -> bankType = BankType.MASKAN;
            default -> {
                System.out.println("INVALID INPUT ! ");
                chooseBankType();
            }
        }
        card.setBank(bankType);
    }

    static void tuitionAndEducationLoanRegistration(Student currentUser, Loan loan, LoanService loanService, InstallmentService installmentService) {
        LoanRegistrationMenu.addCardInfo();
        loan.setStudent(currentUser);
        loanService.saveOrUpdate(loan);
        SecurityContext.fillContext(loan);
        List<Installment> installments = installmentService.fillInstallment();
        for (Installment installment : installments) {
            installmentService.saveOrUpdate(installment);
        }
        if (loan.getStudent() != null)
            System.out.println("SUCCESSFULLY REGISTERED \n");
    }

    public boolean isRightTime() {
        if (SecurityContext.getTodayDate().getMonth().equals(Month.AUGUST) && SecurityContext.getTodayDate().getDayOfMonth() <= 7) {
            return true;
        } else if (SecurityContext.getTodayDate().getMonth().equals(Month.NOVEMBER) && SecurityContext.getTodayDate().getDayOfMonth() >= 25) {
            return true;
        } else
            return SecurityContext.getTodayDate().getMonth().equals(Month.DECEMBER) && SecurityContext.getTodayDate().getDayOfMonth() < 2;
    }
}