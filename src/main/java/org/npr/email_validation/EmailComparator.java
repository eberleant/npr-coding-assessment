package org.npr.email_validation;

import java.util.Comparator;

public class EmailComparator implements Comparator<Email> {
    @Override
    public int compare(Email firstEmail, Email secondEmail) {
        return CharSequence.compare(firstEmail.getDomain(), secondEmail.getDomain());
    }
}
