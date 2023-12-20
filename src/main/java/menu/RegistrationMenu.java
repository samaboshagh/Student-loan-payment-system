package menu;

import entity.City;
import entity.person.Student;
import entity.enumeration.AcademicLevel;
import entity.enumeration.UniversityType;
import service.CityService;
import service.StudentService;
import utility.ApplicationContext;
import utility.Validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RegistrationMenu extends MainMenu {

    StudentService studentService = ApplicationContext.getStudentService();
    CityService cityService = ApplicationContext.getCityService();
    Student student = new Student();
    MainMenu menu = new MainMenu();
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
        String nationalId;
        boolean validNationalId = true;
        while (validNationalId) {
            nationalId = scanner.next();
            if (Validation.isValidNationalId(nationalId)) {
                student.setNationalId(nationalId);
                validNationalId = false;
            } else System.out.println("PLEASE ENTER VALID INFO");
        }


        System.out.print("NATIONAL CODE : ");
        boolean validNationalCode = true;
        String nationalCode = null;
        while (validNationalCode) {
            nationalCode = scanner.next();
            if (Validation.isValidStudentCode(nationalCode)) {
                student.setNationalCode(nationalCode);
                validNationalCode = false;
            } else System.out.println("PLEASE ENTER VALID INFO");
        }


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
        boolean validStudentId = true;
        String studentNumber;
        while (validStudentId) {
            studentNumber = scanner.next();
            if (Validation.isValidStudentCode(studentNumber)) {
                student.setStudentNumber(studentNumber);
                validStudentId = false;
            } else System.out.println("PLEASE ENTER VALID INFO");
        }


        System.out.print("USER NAME (NATIONAL CODE) : ");
        boolean isUsernameEqualToNID = true;
        String username;
        while (isUsernameEqualToNID) {
            username = scanner.next();
            if (username.equals(nationalCode)) {
                student.setUsername(username);
                isUsernameEqualToNID = false;
            } else System.out.println("PLEASE ENTER VALID INFO");
        }


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
        boolean isValidYear = true;
        Integer enteringYear;
        while (isValidYear) {
            enteringYear = scanner.nextInt();
            if (Validation.isValidEnteringYear(enteringYear)) {
                student.setEnteringYear(enteringYear);
                isValidYear = false;
            } else System.out.println("PLEASE ENTER VALID INFO");
        }


        getAcademicLevel();

        hasDormCheck();

        marriageStateCheck();

        chooseCity();

        City city = cityService.fidById(1);
        student.setCity(city);

        studentService.saveOrUpdate(student);
        System.out.println("SUCCESSFULLY REGISTERED !\n");

    }

    private void chooseCity() {
        String cityText = """
                CHOOSE YOUR CITY :
                ________________________________
                1- Tehran
                2- Gilan
                3- Esfahan
                4- AzarbayjanSharghi
                5- Fars
                6- Khozestan
                7 -Qom
                8- KhorasanRazavi
                9- Alborz
                10- OTHER
                ________________________________
                
                """;
        System.out.print(cityText);
        int cityInput = menu.input();
        switch (cityInput) {
            case 1 -> {
                City cityTehran = cityService.fidById(1);
                student.setCity(cityTehran);
            }
            case 2 -> {
                City cityGilan = cityService.fidById(2);
                student.setCity(cityGilan);
            }
            case 3 -> {
                City cityEsfahan = cityService.fidById(3);
                student.setCity(cityEsfahan);
            }
            case 4 -> {
                City cityAzarbayjanSharghi = cityService.fidById(4);
                student.setCity(cityAzarbayjanSharghi);
            }
            case 5 -> {
                City cityFars = cityService.fidById(5);
                student.setCity(cityFars);
            }
            case 6 -> {
                City cityKhozestan = cityService.fidById(6);
                student.setCity(cityKhozestan);
            }
            case 7 -> {
                City cityQom = cityService.fidById(7);
                student.setCity(cityQom);
            }
            case 8 -> {
                City cityKhorasanRazavi = cityService.fidById(8);
                student.setCity(cityKhorasanRazavi);
            }
            case 9 -> {
                City cityAlborz = cityService.fidById(9);
                student.setCity(cityAlborz);
            }
            case 10 -> {
                City cityOther = cityService.fidById(10);
                student.setCity(cityOther);
            }

            default -> {
                System.out.println("INVALID INPUT ! ");
                singUp();
            }
        }
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
            case 1 -> student.setMarried(true);
            case 2 -> student.setMarried(false);
        }
    }
}