package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean isValidPassword(String password) {
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            return false;
        }
        if (!password.matches(".*[@#$%&].*")) {
            return false;
        }
        return password.matches(".*\\d.*");
    }

    public static Boolean isValidNationalId(String IdOfBirthCertificate) {
        Pattern pattern = Pattern.compile("\\d{10}");
        return pattern.matcher(IdOfBirthCertificate).matches();
    }

    public static Boolean isValidStudentCode(String studentCode) {
        Pattern pattern = Pattern.compile("\\d{10}");
        return pattern.matcher(studentCode).matches();
    }

    public static Boolean isValidEnteringYear(Integer enteringYear) {
        return enteringYear > 1360 && enteringYear < 2000;
    }

    public static boolean cardValidation(String cardNumber) {
        Pattern regex = Pattern.compile("\\d{16}");
        Matcher matcher = regex.matcher(cardNumber);
        return matcher.matches();
    }

    public static boolean isValidCvv2(int cvv2) {
        return cvv2 > 99 && cvv2 <= 9999;
    }
}