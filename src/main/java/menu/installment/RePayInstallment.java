package menu.installment;

import entity.Installment;
import entity.Loan;
import entity.enumeration.BankType;
import entity.person.Student;
import menu.MainMenu;
import service.CardService;
import service.InstallmentService;
import service.LoanService;
import utility.ApplicationContext;
import utility.SecurityContext;
import utility.Validation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class RePayInstallment {

    InstallmentService installmentService = ApplicationContext.getInstallmentService();
    LoanService loanService = ApplicationContext.getLoanService();
    CardService cardService = ApplicationContext.getCardService();
    Scanner scanner = new Scanner(System.in);
    Student student = SecurityContext.getCurrentUser();
    MainMenu menu = new MainMenu();

    public void repayment() {
        System.out.println("WITCH LOAN DO TO WANT TO REPAY FOR : ");
        loanService.findByStudent(student).forEach(System.out::println);
        Integer loanId = scanner.nextInt();
        Loan loan = loanService.findById(loanId).orElse(null);
        SecurityContext.fillContext(loan);
        installmentService.seeUnpaidInstallmentsForEachStudent(student, loan)
                .stream()
                .map(row -> Arrays.stream(row)
                        .map(Object::toString)
                        .collect(Collectors.joining(" ")))
                .forEach(System.out::println);
        System.out.print("PLEASE ENTER THE LOAN NUMBER THAT YOU WANT TO PAY : ");
        Integer loanNumber = scanner.nextInt();
        Optional<Installment> optionalInstallment = Optional.ofNullable(installmentService.findByLoanNumber(loanNumber, loan));
        if (optionalInstallment.isPresent()) {
            Installment installment = optionalInstallment.get();
            installmentService.changeIsPaidState(installment);
            addCardInfo();
            System.out.println(" SUCCESSFULLY REPAID :) \n");
        } else {
            System.out.println(" INVALID ID. PLEASE TRY AGAIN  ! \n");
        }
    }

    public void addCardInfo() {

        System.out.println("***PLEASE ENTER YOUR CARD INFORMATION : ***\n");

        System.out.print("CARD NUMBER : ");
        String cardNumber = scanner.next();
        if (cardService.isSameCard(student, cardNumber)) {

            System.out.print("CVV2 : ");
            boolean isValidCvv = true;
            while (isValidCvv) {
                int cvv2 = menu.input();
                if (Validation.isValidCvv2(cvv2)) {
                    isValidCvv = false;
                } else System.out.println(" PLEASE ENTER VALID CVV !");
            }

            System.out.print("EXPIRATION DATE (in this format yyyy-MM-dd) : ");
            String expirationDate = scanner.next();
            DateFormat dareFormatter = new SimpleDateFormat("yyyy-MM-dd");
            chooseBankType();

        } else {
            System.out.println("UNMATCHED CARD INFO");
            repayment();
        }
    }

    public void chooseBankType() {
        String bankTypeText = """
                ________________________________
                                
                PLEASE CHOOSE YOUR BANK :
                1 - MELLI
                2 - REFAH
                3 - TEJARAT
                4 - MASKAN
                                
                ________________________________
                """;
        System.out.println(bankTypeText);
        int bankInput = menu.input();
        BankType bankType = null;
        switch (bankInput) {
            case 1 -> bankType = BankType.MELLI;
            case 2 -> bankType = BankType.REFAH;
            case 3 -> bankType = BankType.TEJARAT;
            case 4 -> bankType = BankType.MASKAN;
            default -> {
                System.out.println("INVALID INPUT ! ");
                chooseBankType();
            }
        }
    }
}