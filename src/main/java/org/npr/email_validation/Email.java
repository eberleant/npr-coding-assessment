package org.npr.email_validation;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Email {
    private String localPart;
    private String domain;

    public Email(String email) {
        if (EmailValidator.isValid(email)) {
            String[] splitEmail = email.split("@");
            this.localPart = splitEmail[0];
            this.domain = splitEmail[1];
        }
    }

    public String getLocalPart() {
        return localPart;
    }

    public String getDomain() {
        return domain;
    }

    public boolean isValid() {
        return localPart != null && domain != null;
    }

    @Override
    public String toString() {
        return localPart + "@" + domain;
    }

    public static String filterAndSortList(String emails) {
        return Stream.of(emails.split("\n"))
                .map(Email::new)
                .filter(Email::isValid)
                .sorted(new EmailComparator())
                .map(Email::toString)
                .collect(Collectors.joining("\n"));
    }
}
