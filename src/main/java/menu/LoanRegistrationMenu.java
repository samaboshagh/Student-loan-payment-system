package menu;

import entity.person.Student;
import service.StudentService;
import utility.ApplicationContext;
import utility.SecurityContext;

import java.util.InputMismatchException;
import java.util.Scanner;
@SuppressWarnings("unused")
public class LoanRegistrationMenu extends MainMenu {

    StudentService studentService = ApplicationContext.getStudentService();

    Scanner scanner = new Scanner(System.in);

    MainMenu menu = new MainMenu();

    String username;

    String password;

    public LoanRegistrationMenu(String username, String password) {
        this.username = username;
        this.password = password;
        login();
    }

    public void login() {
        if (studentService.existByUserNameAndPassword(username, password)) {
            System.out.println("YOU SUCCESSFULLY LOGGED IN ");
            Student student = studentService.findByUserName(username);
            SecurityContext.fillContext(student);
//            todo call next menu
        }else {
            System.out.println("INVALID USERNAME AND PASSWORD");
            menu.start();
        }
    }
//    TODO HOW TO NOT GET DUPLICATE WARNING
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