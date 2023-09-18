package org.example.contactcategories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmailValidationTests {
    BaseContactCategory contactCategory;
    String emailREGEX = "^\\S+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    @BeforeEach
    public void setup() {
        contactCategory = new PersonalContactCategory();
    }

    @Test
    public void gmailAddress() {
        String testString = "email@gmail.com";
        boolean expectedOutput = true;

        boolean actualOutput = contactCategory.validateEmail(emailREGEX, testString);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void hotmailAddress() {
        String testString = "email@hotmail.co.uk";
        boolean expectedOutput = true;

        boolean actualOutput = contactCategory.validateEmail(emailREGEX, testString);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void emailSpecialCharacters() {
        String testString = "email£$%^&*@gmail.com";
        boolean expectedOutput = false;

        boolean actualOutput = contactCategory.validateEmail(emailREGEX, testString);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void emailNoAt() {
        String testString = "emailgmail.com";
        boolean expectedOutput = false;

        boolean actualOutput = contactCategory.validateEmail(emailREGEX, testString);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void emailMultipleAts() {
        String testString = "email@@gmail.com";
        boolean expectedOutput = false;

        boolean actualOutput = contactCategory.validateEmail(emailREGEX, testString);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void emailNumbers() {
        String testString = "email12345@gmail.com";
        boolean expectedOutput = true;

        boolean actualOutput = contactCategory.validateEmail(emailREGEX, testString);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void emailUpperCase() {
        String testString = "EMAIL@GMAIL.COM";
        boolean expectedOutput = true;

        boolean actualOutput = contactCategory.validateEmail(emailREGEX, testString);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void emailMixedCase() {
        String testString = "EmAiL@gMaIl.CoM";
        boolean expectedOutput = true;

        boolean actualOutput = contactCategory.validateEmail(emailREGEX, testString);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void emailNoDot() {
        String testString = "email@gmailcom";
        boolean expectedOutput = false;

        boolean actualOutput = contactCategory.validateEmail(emailREGEX, testString);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void emailMultipleDots() {
        String testString = "email@gmail..com";
        boolean expectedOutput = false;

        boolean actualOutput = contactCategory.validateEmail(emailREGEX, testString);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void emailDotBeforeAt() {
        String testString = "e.mail@gmail.com";
        boolean expectedOutput = true;

        boolean actualOutput = contactCategory.validateEmail(emailREGEX, testString);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}
