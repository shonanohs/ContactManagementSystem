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
                Welcome to Contact Manager. Please select a category to manage:
                (1) Personal contacts
                (2) Work contacts""");

        String userDecision = scanner.nextLine();
        switch (userDecision) {
            case "1" -> {
                performOperation(personal);
            }
            case "2" -> {
                performOperation(work);
            }
            default -> {
                System.out.println("Invalid input. Please enter 1 or 2.");
                initialiseProgram();
            }
        }
    }

    public void performOperation(BaseContactCategory category) {
        boolean shouldRepeat = true;
        while (shouldRepeat) {
            System.out.println("\nYou are currently in your " + category + " contact list.");
            System.out.println("""
                    Which operation would you like to perform?
                    (1) Add a contact
                    (2) Remove a contact
                    (3) View contacts
                    (4) Search contacts
                    (5) Edit a contact
                    (6) Save contacts to file
                    (7) Switch categories
                    (8) Exit program""");

            String userDecision = scanner.nextLine();
            switch (userDecision) {
                case "1" -> { // I don't like this nested switch statement
                    System.out.println("Would you like to add contacts from:\n(1) The command line\n(2) A file");
                    String addMethod = scanner.nextLine();
                    switch (addMethod) {
                        case "1" -> {
                            category.addContact();
                        }
                        case "2" -> {
                            category.readFromFile();
                        }
                        default -> {
                            System.out.println("Invalid input. Please enter 1 or 2.");
                        }
                    }
                }
                case "2" -> {
                    category.removeContact();
                }
                case "3" -> {
                    category.viewContacts();
                }
                case "4" -> {
                    category.searchContacts();
                }
                case "5" -> {
                    category.editContact();
                }
                case "6" -> {
                    category.writeToFile();
                }
                case "7" -> {
                    shouldRepeat = false;
                    initialiseProgram();
                }
                case "8" -> {
                    shouldRepeat = false;
                }
                default -> {
                    System.out.println("Invalid input. Please enter a number from 1-7.\n");
                }
            }
        }
    }
}
