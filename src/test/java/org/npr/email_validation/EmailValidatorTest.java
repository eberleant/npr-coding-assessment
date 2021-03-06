package org.npr.email_validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @Test
    public void testBasic() {
        assertTrue(EmailValidator.isValid("abc@abc.com"));
    }
}