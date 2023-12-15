package utility;

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
}
