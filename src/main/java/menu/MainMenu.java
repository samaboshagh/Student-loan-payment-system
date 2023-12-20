package menu;

import menu.installment.LoanRepaymentMenu;
import menu.loanRegistration.LoanRegistrationMenu;
import utility.SecurityContext;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    public static Scanner scanner = new Scanner(System.in);

    String userName;
    String passWord;
    String today;

    public void start() {
        while (true) {
            String text = """
                    *** PLEASE ENTER YOUR CHOICE : ***
                    1- SING UP
                    2- LOAN REGISTRATION
                    3- LOAN REPAYMENT
                    4- STUDENT PANEL
                    5- EXIT
                    """;
            System.out.println(text);
            int input = input();
            switch (input) {
                case 1 -> new RegistrationMenu().singUp();

                case 2 -> {
                    login();
                    if (isRightTime()) {
                        new LoanRegistrationMenu(userName, passWord, today);
                    } else {
                        System.out.println("ITS NOT REGISTRATION TIME");
                        start();
                    }
                }

                case 3 -> {
                    login();
                    new LoanRepaymentMenu(userName, passWord, today);
                }

                case 4 -> {
                    login();
                    new StudentPanel();
                }

                case 5 -> System.exit(-1);

                default -> {
                    System.out.println("INVALID INPUT !\n");
                    start();
                }
            }
        }
    }

    void login() {

        System.out.println("PLEASE ENTER YOUR USERNAME : ");
        this.userName = scanner.next();
        System.out.println("PLEASE ENTER YOUR PASSWORD : ");
        this.passWord = scanner.next();
        System.out.println("Enter Current Date:(yyyy-MM-dd)");
        this.today = scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate localDate = LocalDate.parse(today, formatter);
            SecurityContext.fillContext(localDate);
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
        }
    }

    public boolean isRightTime() {

        if (SecurityContext.getTodayDate().getMonth().equals(Month.AUGUST) && SecurityContext.getTodayDate().getDayOfMonth() <= 7) {
            return true;
        } else if (SecurityContext.getTodayDate().getMonth().equals(Month.NOVEMBER) && SecurityContext.getTodayDate().getDayOfMonth() >= 25) {
            return true;
        } else
            return SecurityContext.getTodayDate().getMonth().equals(Month.DECEMBER) && SecurityContext.getTodayDate().getDayOfMonth() < 2;
    }

    public Integer input() {
        int i;
        while (true) {
            try {
                i = scanner.nextInt();
                scanner.nextLine();
                return i;
            } catch (InputMismatchException in) {
                scanner.nextLine();
                System.out.println("PLEASE ENTER VALID NUMBER !");
            }
        }
    }
}