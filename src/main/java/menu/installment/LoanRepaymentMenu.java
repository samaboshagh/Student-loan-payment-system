package menu.installment;

import entity.person.Student;
import menu.MainMenu;
import service.StudentService;
import utility.ApplicationContext;
import utility.SecurityContext;

import java.time.LocalDate;

public class LoanRepaymentMenu extends MainMenu {

    StudentService studentService = ApplicationContext.getStudentService();
    MainMenu menu = new MainMenu();
    String username;
    String password;
    String today;

    public LoanRepaymentMenu(String username, String password, String today) {
        this.username = username;
        this.password = password;
        this.today = today;
        login();
    }

    public void login() {
        if (studentService.existByUserNameAndPassword(username, password)) {
            System.out.println("YOU SUCCESSFULLY LOGGED IN ");
            Student student = studentService.findByUserName(username);
            SecurityContext.fillContext(student);
            installmentMenu();
        } else {
            System.out.println("INVALID USERNAME AND PASSWORD");
            menu.start();
        }
    }

    public void installmentMenu() {

        Student student = SecurityContext.getCurrentUser();
        LocalDate localDate = SecurityContext.getTodayDate();

        if (studentService.isStudentGraduated(student, localDate)) {

            String installmentMenuText = """
                    ***                       ***
                                        
                    1- SEE PAID INSTALLMENT
                    2- SEE UNPAID INSTALLMENT
                    3- REPAY
                    4- BACH TO MAIN MENU
                                        
                    """;
            System.out.println(installmentMenuText);
            int installmentInput = menu.input();
            switch (installmentInput) {
                case 1 -> new PaidInstallments().seePaidInstallments();
                case 2 -> new UnpaidInstallments().seeUnpaidInstallments();
                case 3 -> new RePayInstallment().repayment();
                case 4 -> menu.start();
                default -> {
                    System.out.println("INVALID INPUT ! ");
                    installmentMenu();
                }
            }
        } else {
            System.out.println("YOU HAVEN'T GRADUATED YET !");
            menu.start();
        }
    }
}