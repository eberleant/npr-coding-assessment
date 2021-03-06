package org.npr.email_validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static Pattern pattern = Pattern.compile("([\\w!#$%&'*+\\-/=?^`{|}~]+\\.?)*[\\w!#$%&'*+\\-/=?^_`{|}~]+@((([A-Za-z0-9]+-?)*[A-Za-z0-9]+)\\.)*(([A-Za-z0-9]+-?)*[A-Za-z0-9]+)");

    public static boolean isValid(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
