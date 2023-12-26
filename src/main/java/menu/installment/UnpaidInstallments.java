package menu.installment;

import entity.person.Student;
import service.InstallmentService;
import utility.ApplicationContext;
import utility.SecurityContext;

import java.util.Arrays;
import java.util.stream.Collectors;

public class UnpaidInstallments {

    InstallmentService installmentService = ApplicationContext.getInstallmentService();

    public void seeUnpaidInstallments() {
        Student user = SecurityContext.getCurrentUser();
        installmentService.unpaidInstallments(user)
                .stream()
                .map(row -> Arrays.stream(row)
                        .map(Object::toString)
                        .collect(Collectors.joining(" ")))
                .forEach(System.out::println);
        new LoanRepaymentMenu().installmentMenu();
    }
}