package org.npr.email_validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {
    @Test
    public void testBasicValid() {
        // from https://en.wikipedia.org/wiki/Email_address#Examples (excluding quoted local parts)
        assertTrue(EmailValidator.isValid("simple@example.com"));
        assertTrue(EmailValidator.isValid("very.common@example.com"));
        assertTrue(EmailValidator.isValid("disposable.style.email.with+symbol@example.com"));
        assertTrue(EmailValidator.isValid("other.email-with-hyphen@example.com"));
        assertTrue(EmailValidator.isValid("fully-qualified-domain@example.com"));
        assertTrue(EmailValidator.isValid("user.name+tag+sorting@example.com"));
        assertTrue(EmailValidator.isValid("x@example.com"));
        assertTrue(EmailValidator.isValid("example-indeed@strange-example.com"));
        assertTrue(EmailValidator.isValid("admin@mailserver1"));
        assertTrue(EmailValidator.isValid("example@s.example"));
        assertTrue(EmailValidator.isValid("mailhost!username@example.org"));
        assertTrue(EmailValidator.isValid("user%example.com@example.org"));
        assertTrue(EmailValidator.isValid("user-@example.org"));
    }

    @Test
    public void testBasicInvalid() {
        // from https://en.wikipedia.org/wiki/Email_address#Examples
        assertFalse(EmailValidator.isValid("Abc.example.com"));
        assertFalse(EmailValidator.isValid("A@b@c@example.com"));
        assertFalse(EmailValidator.isValid("a\"b(c)d,e:f;g<h>i[j\\k]l@example.com"));
        assertFalse(EmailValidator.isValid("just\"not\"right@example.com"));
        assertFalse(EmailValidator.isValid("this is\"not\\allowed@example.com"));
        assertFalse(EmailValidator.isValid("this\\ still\\\"not\\\\allowed@example.com"));
        assertFalse(EmailValidator.isValid("1234567890123456789012345678901234567890123456789012345678901234+x@example.com"));
        assertFalse(EmailValidator.isValid("i_like_underscore@but_its_not_allowed_in_this_part.example.com"));
    }

    @Test
    public void testLocalPartValid() {
        assertTrue(EmailValidator.isValid("!#$%&'*+-/=?^_`{|}~0123456789abczABCZ@abc.com"));
        assertTrue(EmailValidator.isValid("a@abc.com"));
        assertTrue(EmailValidator.isValid("a.b.c.e@abc.com"));
        assertTrue(EmailValidator.isValid("abc.d.ef@abc.com"));
    }

    @Test
    public void testLocalPartInvalidPeriod() {
        assertFalse(EmailValidator.isValid(".abc@abc.com"));
        assertFalse(EmailValidator.isValid("abc.@abc.com"));
        assertFalse(EmailValidator.isValid("abc..abc@abc.com"));
        assertFalse(EmailValidator.isValid(".@abc.com"));
    }

    @Test
    public void testLocalPartEmpty() {
        assertFalse(EmailValidator.isValid("@abc.com"));
    }

    @Test
    public void testDomainValid() {

    }
}