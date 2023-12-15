package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

   public static Scanner scanner = new Scanner(System.in);

    String userName;
    String passWord;

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
                    new LoanRegistrationMenu(userName, passWord);
                }

                case 3 -> {
                    login();
                    new LoanRepaymentMenu(userName, passWord);
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