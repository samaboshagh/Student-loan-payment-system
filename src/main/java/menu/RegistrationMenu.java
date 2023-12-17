package menu;

import entity.Card;
import entity.person.Student;
import entity.person.StudentSpouse;
import entity.enumeration.AcademicLevel;
import entity.enumeration.BankType;
import entity.enumeration.UniversityType;
import service.CardService;
import service.StudentService;
import service.StudentSpouseService;
import utility.ApplicationContext;
import utility.Validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RegistrationMenu extends MainMenu{

    StudentService studentService = ApplicationContext.getStudentService();
    StudentSpouseService studentSpouseService = ApplicationContext.getStudentSpouseService();
    Student student = new Student();
    MainMenu menu = new MainMenu();
    StudentSpouse studentSpouse;
    Scanner scanner = new Scanner(System.in);

    public void singUp() {
        String text = """
                *** WELL COME ***
                              
                PLEASE ENTER THE REQUESTED INFORMATION :
                """;
        System.out.println(text);

        System.out.print("FIRST NAME : ");
        String firstname = scanner.next();
        student.setFirstname(firstname);

        System.out.print("LAST NAME : ");
        String lastname = scanner.next();
        student.setLastname(lastname);

        System.out.print("FATHER NAME : ");
        String fatherName = scanner.next();
        student.setFatherName(fatherName);

        System.out.print("MOTHER NAME : ");
        String motherName = scanner.next();
        student.setMotherName(motherName);

        System.out.print("NATIONAL ID : ");
        String nationalId = scanner.next();
        student.setNationalId(nationalId);

        System.out.print("NATIONAL CODE : ");
        String nationalCode = scanner.next();
        student.setNationalCode(nationalCode);

        System.out.print("DATE OF BIRTHDAY (in this format yyyy-MM-dd) : ");
        String dob = scanner.next();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            student.setBirthDate(formatter.parse(dob));
        } catch (ParseException e) {
            e.printStackTrace();
            singUp();
        }


        System.out.print("STUDENT NUMBER : ");
        String studentNumber = scanner.next();
        student.setStudentNumber(studentNumber);

        System.out.print("USER NAME (NATIONAL CODE) : ");
        String username = scanner.next();
        student.setUsername(username);

        System.out.print("PASSWORD : ");
        String password = generatePassword();
        student.setPassword(password);
        System.out.println(password);

        System.out.print("UNIVERSITY NAME : ");
        String universityName = scanner.next();
        student.setUniversityName(universityName);

        UniversityType universityType = getUniversityType();

        isUniversityDailyCheck(universityType);

        System.out.print("ENTERING YEAR : ");
        String enteringYear = scanner.next();
        student.setEnteringYear(enteringYear);

        getAcademicLevel();

        hasDormCheck();

        marriageStateCheck();

        System.out.print("CITY : ");
        String city = scanner.next();
        student.setCity(city);

        System.out.print("FULL ADDRESS : ");
        String fullAddress = scanner.next();
        student.setFullAddress(fullAddress);

//        addCardInfo();

        studentService.saveOrUpdate(student);
        System.out.println("SUCCESSFULLY REGISTERED !\n");

    }

    public static String generatePassword() {
        Random random = new Random();
        String validCharacters = "@#$%&abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String password;
        do {
            StringBuilder passwordBuilder = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                int randomIndex = random.nextInt(validCharacters.length());
                passwordBuilder.append(validCharacters.charAt(randomIndex));
            }
            password = passwordBuilder.toString();
        } while (!Validation.isValidPassword(password));

        return password;
    }

//    private void addCardInfo() {
//
//        System.out.println("***PLEASE ENTER YOUR CARD INFORMATION : ***\n");
//
//        System.out.print("CARD NUMBER : ");
//        String cardNumber = scanner.next();
//        card.setCardNumber(cardNumber);
//
//        System.out.print("CVV2 : ");
//        int cvv2 = menu.input();
//        card.setCvv2(cvv2);
//
//        System.out.print("EXPIRATION DATE (in this format yyyy-MM-dd) : ");
//        String expirationDate = scanner.next();
//        DateFormat cardFormatter = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            card.setExpirationDate(cardFormatter.parse(expirationDate));
//        } catch (ParseException e) {
//            e.printStackTrace();
//            singUp();
//        }
//
//        chooseBankType();
//
//        card.setStudent(student);
//        cardService.saveOrUpdate(card);
//    }
//
//    private void chooseBankType() {
//        String bankText = """
//                ________________________________
//
//                PLEASE CHOOSE YOUR BANK :
//                1 - MELLI
//                2 - REFAH
//                3 - TEJARAT
//                4 - MASKAN
//
//                ________________________________
//                """;
//        System.out.println(bankText);
//        int bankInput = menu.input();
//        BankType bankType = null;
//        switch (bankInput) {
//            case 1 -> bankType = BankType.MELLI;
//            case 2 -> bankType = BankType.REFAH;
//            case 3 -> bankType = BankType.TEJARAT;
//            case 4 -> bankType = BankType.MASKAN;
//            default -> {
//                System.out.println("INVALID INPUT ! ");
//                singUp();
//            }
//        }
//        card.setBank(bankType);
//    }

    private void hasDormCheck() {
        String hasDormText = """
                DO YOU HAVE DORM ?
                1 - YES
                2 - NO
                """;
        System.out.print(hasDormText);
        int hasDormInput = menu.input();
        switch (hasDormInput) {
            case 1 -> student.setHasDorm(true);
            case 2 -> student.setHasDorm(false);
            default -> {
                System.out.println("INVALID INPUT ! ");
                singUp();
            }
        }
    }

    private void isUniversityDailyCheck(UniversityType universityType) {
        assert universityType != null;
        if (universityType.equals(UniversityType.GOVERNMENTAL_UNIVERSITY)) {
            String isDaily = """
                    IS IT DAILY ?
                    1 - YES
                    2 - NO
                    """;
            System.out.print(isDaily);
            int isDailyInput = menu.input();
            switch (isDailyInput) {
                case 1 -> student.setDaily(true);
                case 2 -> student.setDaily(false);
                default -> {
                    System.out.println("INVALID INPUT ! ");
                    singUp();
                }
            }
        }
    }


    private UniversityType getUniversityType() {
        String universityTypeText = """
                  ________________________________
                  
                  CHOOSE YOUR UNIVERSITY TYPE :
                  
                  1 - GOVERNMENTAL_UNIVERSITY
                  2 - NON_GOVERNMENTAL_UNIVERSITY
                  3 - NON_PROFIT_UNIVERSITY
                  4 - PARDIS_UNIVERSITY
                  5 - EXCESS_CAPACITY_UNIVERSITY
                  6 - PAYAM_NORE_UNIVERSITY
                  7 - ELMIKARBORDI_UNIVERSITY
                  8 - AZAD_UNIVERSITY
                  ________________________________
                """;
        System.out.println(universityTypeText);
        int uniType = menu.input();
        UniversityType universityType = null;
        switch (uniType) {

            case 1 -> universityType = UniversityType.GOVERNMENTAL_UNIVERSITY;

            case 2 -> universityType = UniversityType.NON_GOVERNMENTAL_UNIVERSITY;

            case 3 -> universityType = UniversityType.NON_PROFIT_UNIVERSITY;

            case 4 -> universityType = UniversityType.PARDIS_UNIVERSITY;

            case 5 -> universityType = UniversityType.EXCESS_CAPACITY_UNIVERSITY;

            case 6 -> universityType = UniversityType.PAYAM_NORE_UNIVERSITY;

            case 7 -> universityType = UniversityType.ELMIKARBORDI_UNIVERSITY;

            case 8 -> universityType = UniversityType.AZAD_UNIVERSITY;

            default -> {
                System.out.println("INVALID INPUT ! ");
                singUp();
            }
        }
        student.setUniversityType(universityType);
        return universityType;
    }

    private void getAcademicLevel() {
        String academicLevelText = """
                  ________________________________
                  
                  CHOOSE YOUR ACADEMIC LEVEL :
                  
                  1 - KARDANI
                  2 - CONTINUOUS_BACHELOR
                  3 - DISCONTINUOUS_BACHELOR
                  4 - CONTINUOUS_MASTER
                  5 - DISCONTINUOUS_MASTER
                  6 - DOCTORATE
                  7 - CONTINUOUS_DOCTORATE
                  8 - DISCONTINUOUS_SPECIALIZED_DOCTORATE
                  
                  ________________________________
                """;
        System.out.println(academicLevelText);
        int academicLevelInput = menu.input();
        AcademicLevel academicLevel = null;
        switch (academicLevelInput) {

            case 1 -> academicLevel = AcademicLevel.KARDANI;

            case 2 -> academicLevel = AcademicLevel.CONTINUOUS_BACHELOR;

            case 3 -> academicLevel = AcademicLevel.DISCONTINUOUS_BACHELOR;

            case 4 -> academicLevel = AcademicLevel.CONTINUOUS_MASTER;

            case 5 -> academicLevel = AcademicLevel.DISCONTINUOUS_MASTER;

            case 6 -> academicLevel = AcademicLevel.DOCTORATE;

            case 7 -> academicLevel = AcademicLevel.CONTINUOUS_DOCTORATE;

            case 8 -> academicLevel = AcademicLevel.DISCONTINUOUS_SPECIALIZED_DOCTORATE;

            default -> {
                System.out.println("INVALID INPUT ! ");
                singUp();
            }
        }
        student.setAcademicLevel(academicLevel);
    }

    private void marriageStateCheck() {
        String isMarriedText = """
                DO TOU MARRIED ?
                1 - YES
                2 - NO
                """;
        System.out.print(isMarriedText);
        int isMarriedInput = menu.input();
        switch (isMarriedInput) {
            case 1 -> {
                student.setMarried(true);
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
                String spouseNationalId = scanner.next();
                studentSpouse.setNationalId(spouseNationalId);

                System.out.print("SPOUSE NATIONAL CODE : ");
                String spouseNationalCode = scanner.next();
                studentSpouse.setNationalCode(spouseNationalCode);

                System.out.print("SPOUSE DATE OF BIRTHDAY (in this format yyyy-MM-dd) : ");
                String spouseDob = scanner.next();
                DateFormat spouseFormatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    studentSpouse.setBirthDate(spouseFormatter.parse(spouseDob));
                } catch (ParseException e) {
                    e.printStackTrace();
                    singUp();
                }

                isStudentCheck();
                studentSpouseService.saveOrUpdate(studentSpouse);
            }
            case 2 -> student.setMarried(false);
        }
        student.setSpouse(studentSpouse);
    }

    private void isStudentCheck() {
        String isStudentText = """
                IS YOUR SPOUSE STUDENT :
                1 - YES
                2 - NO
                """;
        System.out.println(isStudentText);
        int isStudentInput = menu.input();
        switch (isStudentInput) {
            case 1 -> studentSpouse.setIsStudent(true);
            case 2 -> studentSpouse.setIsStudent(false);
            default -> {
                System.out.println("INVALID INPUT ! ");
                singUp();
            }
        }
    }
}