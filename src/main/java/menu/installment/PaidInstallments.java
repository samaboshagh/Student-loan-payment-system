package menu.installment;

import entity.person.Student;
import service.InstallmentService;
import utility.ApplicationContext;
import utility.SecurityContext;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PaidInstallments {

    InstallmentService installmentService = ApplicationContext.getInstallmentService();

    public void seePaidInstallments() {
        Student user = SecurityContext.getCurrentUser();
        installmentService.paidInstallments(user)
                .stream()
                .map(row -> Arrays.stream(row)
                        .map(Object::toString)
                        .collect(Collectors.joining(" ")))
                .forEach(System.out::println);
    }
}