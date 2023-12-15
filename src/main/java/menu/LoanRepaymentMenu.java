package menu;

import entity.person.Student;
import service.StudentService;
import utility.ApplicationContext;
import utility.SecurityContext;

public class LoanRepaymentMenu extends MainMenu{

    StudentService studentService = ApplicationContext.getStudentService();

    MainMenu menu = new MainMenu();

    String username;

    String password;

    public LoanRepaymentMenu(String username, String password) {
        this.username = username;
        this.password = password;
        login();
    }

    public void login(){
        if (studentService.existByUserNameAndPassword(username, password)){
            System.out.println("YOU SUCCESSFULLY LOGGED IN ");
            Student student = studentService.findByUserName(username);
            SecurityContext.fillContext(student);
//          TODO CALL NEXT MENU
        }else {
            System.out.println("INVALID USERNAME AND PASSWORD");
            menu.start();
        }
    }


}
