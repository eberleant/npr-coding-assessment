package org.npr.email_validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {
    @Test
    public void testValid() {
        Email email = new Email("abc@def.com");
        assertEquals("abc", email.getLocalPart());
        assertEquals("def.com", email.getDomain());
    }

    @Test
    public void testInvalid() {
        assertFalse(new Email("Abc.example.com").isValid());
    }

    @Test
    public void testFilterAndSort() {
        String input = "abc@def.com\na.@a.com\ndef@abc.com\n";
        assertEquals("def@abc.com\nabc@def.com", Email.filterAndSortList(input));
    }
}