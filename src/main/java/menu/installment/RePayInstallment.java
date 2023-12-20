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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
@SuppressWarnings("unused")
public class RePayInstallment {

    InstallmentService installmentService = ApplicationContext.getInstallmentService();
    LoanService loanService =ApplicationContext.getLoanService();
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
        new UnpaidInstallments().seeUnpaidInstallments();
        System.out.print("PLEASE ENTER THE LOAN NUMBER THAT YOU WANT TO PAY : ");
        Integer loanNumber = scanner.nextInt();
        Installment installment = installmentService.findByLoanNumber(loanNumber,loan);
        installmentService.changeIsPaidState(installment);
        addCardInfo();
        System.out.println(" SUCCESSFULLY REPAID :) ");
    }

    public void addCardInfo() {

        System.out.println("***PLEASE ENTER YOUR CARD INFORMATION : ***\n");

        System.out.print("CARD NUMBER : ");
        String cardNumber = scanner.next();
        if (cardService.isSameCard(student,cardNumber)) {

            System.out.print("CVV2 : ");
            int cvv2 = menu.input();

            System.out.print("EXPIRATION DATE (in this format yyyy-MM-dd) : ");
            String expirationDate = scanner.next();
            DateFormat dareFormatter = new SimpleDateFormat("yyyy-MM-dd");
            chooseBankType();

        }else{
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