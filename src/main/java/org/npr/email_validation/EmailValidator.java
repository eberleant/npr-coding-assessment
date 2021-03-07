package org.npr.email_validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    // https://en.wikipedia.org/wiki/Email_address#Syntax with some simplifications (details in readme.txt)
    private static final Pattern pattern = Pattern.compile("^(?=.{1,64}@)([\\w!#$%&'*+\\-/=?^`{|}~]+\\.?)*[\\w!#$%&'*+\\-/=?^_`{|}~]+@((([A-Za-z0-9]+-?)*[A-Za-z0-9]+)\\.)*(([A-Za-z0-9]+-?)*[A-Za-z0-9]+)");

    /**
     * Validates the given email address. If it is valid, returns true; if not, false.
     * @param email email address string
     * @return true if valid email address; false if not
     */
    public static boolean isValid(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
