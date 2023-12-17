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

    public static boolean isValidNationalCode(String nationalCode) {
        Pattern pattern =
                Pattern.compile("^(0[1-9]|[1-3]\\d|40)([0-9]{3})([0-9]{3})([0-9])$");
        return nationalCode.matches(pattern.pattern());
    }

    public static Boolean isValidNationalId(String IdOfBirthCertificate) {
        Pattern pattern = Pattern.compile("\\d{10}");
        return pattern.matcher(IdOfBirthCertificate).matches();
    }

    public static Boolean isValidStudentCode(String studentCode) {
        Pattern pattern = Pattern.compile("\\d{8}");
        return pattern.matcher(studentCode).matches();
    }

    public static boolean cardValidation(String cardNumber) {
        Pattern regex = Pattern.compile("\\d{16}");
        Matcher matcher = regex.matcher(cardNumber);
        return matcher.matches();
    }

    public static boolean cvv2Validation(String cvv2) {
        Pattern regex = Pattern.compile("\\d{3}");
        Matcher matcher = regex.matcher(String.valueOf(cvv2));
        return matcher.matches();
    }
}
