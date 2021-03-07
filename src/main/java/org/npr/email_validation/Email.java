package org.npr.email_validation;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Email {
    private String localPart;
    private String domain;

    /**
     * Creates Email object from given email address string by setting localPart to the substring before @
     * and domain to the substring after @. If the input string is not a valid email, then localPart and domain
     * remain null.
     * @param email email address string
     */
    public Email(String email) {
        if (EmailValidator.isValid(email)) {
            String[] splitEmail = email.split("@");
            this.localPart = splitEmail[0];
            this.domain = splitEmail[1];
        }
    }

    /**
     * @return local part of email (part before @)
     */
    public String getLocalPart() {
        return localPart;
    }

    /**
     * @return domain of email (part after @)
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Checks if email object is valid
     * @return true if localPart and domain are both nonnull; false otherwise
     */
    public boolean isValid() {
        return localPart != null && domain != null;
    }

    /**
     * Converts email object to string with format localPart@domain
     * @return email address string
     */
    @Override
    public String toString() {
        return localPart + "@" + domain;
    }

    /**
     * Filters list of emails (keeping only valid ones) and sorts by domain
     * @param emails newline-separated string of emails
     * @return newline-separated string containing valid emails from input, sorted by domain
     */
    public static String filterAndSortList(String emails) {
        return Stream.of(emails.split("\n"))
                .map(Email::new)
                .filter(Email::isValid)
                .sorted(new EmailComparator())
                .map(Email::toString)
                .collect(Collectors.joining("\n"));
    }
}
