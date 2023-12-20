package menu;

import menu.installment.LoanRepaymentMenu;
import menu.loanRegistration.LoanRegistrationMenu;
import utility.SecurityContext;

import java.time.LocalDate;
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
                    4- EXIT
                    """;
            System.out.println(text);
            int input = input();
            switch (input) {
                case 1 -> new RegistrationMenu().singUp();

                case 2 -> {
                    login();
                    new LoanRegistrationMenu(userName, passWord, today);
                }

                case 3 -> {
                    login();
                    new LoanRepaymentMenu(userName, passWord, today);
                }

                case 4 -> System.exit(-1);

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