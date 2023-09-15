package org.example;

import org.example.contactcategories.BaseContactCategory;
import org.example.contactcategories.PersonalContactCategory;
import org.example.contactcategories.WorkContactCategory;

import java.util.Scanner;

public class ContactManager {
    private Scanner scanner = new Scanner(System.in);
    private PersonalContactCategory personal = new PersonalContactCategory();
    private WorkContactCategory work = new WorkContactCategory();

    public void initialiseProgram() {
        System.out.println("""
                Welcome to contact manager. Please select a category to manage:
                (1) Personal contacts
                (2) Work contacts""");
        int userDecision = scanner.nextInt();
        switch (userDecision) {
            case 1 -> {
                performOperation(personal);
            }
            case 2 -> {
                performOperation(work);
            }
        }
    }

    public void performOperation(BaseContactCategory category) {
        boolean shouldRepeat = true;
        while (shouldRepeat) {
            System.out.println("""
                    Which operation would you like to perform?
                    (1) Add a contact
                    (2) Remove a contact
                    (3) View contacts
                    (4) Search contacts
                    (5) Edit a contact
                    (6) Switch categories
                    (7) Exit program"""); //TODO - edit a contact
            int userDecision = scanner.nextInt();
            switch (userDecision) {
                case 1 -> {
                    category.addContact();
                }
                case 2 -> {
                    category.removeContact();
                }
                case 3 -> {
                    category.viewContacts();
                }
                case 4 -> {
                    category.searchContacts();
                }
                case 6 -> {
                    shouldRepeat = false;
                    initialiseProgram();
                }
                case 7 -> {
                    shouldRepeat = false;
                }
                default -> {
                    System.out.println("Invalid input. Please enter a number from 1-7.\n");
                }
            }
        }
    }
}
