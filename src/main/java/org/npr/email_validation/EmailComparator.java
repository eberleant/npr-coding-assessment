package org.npr.email_validation;

import java.util.Comparator;

public class EmailComparator implements Comparator<String> {
    @Override
    public int compare(String firstEmail, String secondEmail) {
        return CharSequence.compare(firstEmail.split("@")[1], secondEmail.split("@")[1]);
    }
}
