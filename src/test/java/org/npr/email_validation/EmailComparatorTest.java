package org.npr.email_validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailComparatorTest {
    @Test
    public void testCompare() {
        Email first = new Email("abc@abc.com");
        Email second = new Email("abc@bcd.com");
        EmailComparator emailComparator = new EmailComparator();
        assertTrue(emailComparator.compare(first, second) < 0);
        assertTrue(emailComparator.compare(second, first) > 0);
    }
}