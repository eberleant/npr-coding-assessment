package org.npr.email_validation;

import java.util.Comparator;

public class EmailComparator implements Comparator<Email> {

    /**
     * Compares two email objects based on domain.
     * @param firstEmail the first email
     * @param secondEmail the second email
     * @return the value 0 if the two email domains are equal; negative if first domain is less than second; positive if first domain is greater than second
     */
    @Override
    public int compare(Email firstEmail, Email secondEmail) {
        return CharSequence.compare(firstEmail.getDomain(), secondEmail.getDomain());
    }
}
