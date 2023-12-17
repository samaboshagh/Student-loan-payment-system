package utility;

import lombok.Getter;
import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@SuppressWarnings("unused")
public class ApplicationContext {

    @Getter
    private static final EntityManager entityManager =
            Persistence.createEntityManagerFactory(
                    "default"
            ).createEntityManager();

    private static CardRepository cardRepository;
    private static InstallmentRepository installmentRepository;

    private static LoanRepository loanRepository;

    private static LoanCategoryRepository loanCategoryRepository;

    private static StudentRepository studentRepository;
    private static StudentSpouseRepository studentSpouseRepository;
    private static CardService cardService;
    private static InstallmentService installmentService;

    private static LoanService loanService;

    private static LoanCategoryService loanCategoryService;

    private static StudentService studentService;
    private static StudentSpouseService studentSpouseService;

    public static CardRepository getCardRepository() {
        if (cardRepository == null) {
            cardRepository = new CardRepositoryImpl(entityManager);
        }
        return cardRepository;
    }

    public static InstallmentRepository getInstallmentRepository() {
        if (installmentRepository == null) {
            installmentRepository = new InstallmentRepositoryImpl(entityManager);
        }
        return installmentRepository;
    }

    public static LoanRepository getLoanRepository() {
        if (loanRepository == null) {
            loanRepository = new LoanRepositoryImpl(entityManager);
        }
        return loanRepository;
    }

    public static LoanCategoryRepository getLoanCategoryRepository() {
        if (loanCategoryRepository == null){
            loanCategoryRepository = new LoanCategoryRepositoryImpl(entityManager) ;

        }
        return loanCategoryRepository;
    }

    public static StudentRepository getStudentRepository() {
        if (studentRepository == null) {
            studentRepository = new StudentRepositoryImpl(entityManager);
        }
        return studentRepository;
    }

    public static StudentSpouseRepository getStudentSpouseRepository() {
        if (studentSpouseRepository == null) {
            studentSpouseRepository = new StudentSpouseRepositoryImpl(entityManager);
        }
        return studentSpouseRepository;
    }

    public static CardService getCardService() {
        if (cardService == null) {
            cardService = new CardServiceImpl(
                    getCardRepository()
            );
        }
        return cardService;
    }


    public static LoanService getLoanService() {
        if (loanService == null) {
            loanService = new LoanServiceImpl(
                    getLoanRepository()
            );
        }
        return loanService;
    }

    public static LoanCategoryService getLoanCategoryService() {
        if (loanCategoryService == null) {
            loanCategoryService= new LoanCategoryServiceImpl(
                    getLoanCategoryRepository()
            );
        }
        return loanCategoryService;
    }

    public static InstallmentService getInstallmentService() {
        if (installmentService == null) {
            installmentService = new InstallmentServiceImpl(
                    getInstallmentRepository()
            );
        }
        return installmentService;
    }

    public static StudentService getStudentService() {
        if (studentService == null) {
            studentService = new StudentServiceImpl(
                    getStudentRepository()
            );
        }
        return studentService;
    }

    public static StudentSpouseService getStudentSpouseService() {
        if (studentSpouseService == null) {
            studentSpouseService = new StudentSpouseServiceImpl(
                    getStudentSpouseRepository()
            );
        }
        return studentSpouseService;
    }
}