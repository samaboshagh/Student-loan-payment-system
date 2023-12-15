package utility;

import entity.person.Person;
import lombok.Getter;

public class SecurityContext {

    private SecurityContext() {
    }

    @Getter
    private static Person currentUser;

    public static void fillContext(Person baseUser) {
        currentUser = baseUser;
    }

    public static void logout() {
        currentUser = null;
    }

    public static boolean isAnyoneAuthenticated() {
        return currentUser != null;
    }

    public static Integer getCurrentUserId() {
        return currentUser.getId();
    }



}