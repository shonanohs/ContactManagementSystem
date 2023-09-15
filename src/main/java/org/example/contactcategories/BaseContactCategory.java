package org.example.contactcategories;

import org.example.contact.Contact;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BaseContactCategory implements ContactCategory {
    public List<Contact> contacts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void addContact() {
        System.out.println("Please enter the following information for the contact you " +
                "would like to add:");
        List<String> contactInfo = getContactDetails();
        contacts.add(new Contact(contactInfo.get(0), contactInfo.get(1), contactInfo.get(2)));
        System.out.println("Contact added!\n");
    }

    @Override
    public void removeContact() {
        System.out.println("Please enter the name of the contact to remove:");
        String name = scanner.nextLine();
        List<Contact> matches = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().contains(name)) {
                matches.add(contact);
            }
        }
        switch (matches.size()) {
            case 0 -> {
                System.out.println("Contact not found.\n");
            }
            case 1 -> {
                contacts.remove(matches.get(0));
                System.out.println("Contact removed!\n");
            }
            default -> {
                System.out.println("Multiple contacts found, which would you like to remove?");
                int count = 0;
                for (Contact c : matches) {
                    System.out.print("(" + ++count + ") ");
                    System.out.println(c);
                }
                int userDecision = scanner.nextInt();
                contacts.remove(matches.get(userDecision - 1));
                System.out.println("Contact removed!\n");
            }
        }
    }

    @Override
    public void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Contact list is empty.\n");
        }
        contacts.sort(Comparator.comparing(Contact::getName));
        for (Contact c : contacts) {
            System.out.println(c);
        }
        System.out.println();
    }

    @Override
    public void searchContacts() {
        System.out.println("Enter the name/characters you would like to search for.");
        String search = scanner.nextLine();
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().contains(search)) {
                found = true;
                System.out.println(contact);
            }
        }
        System.out.println();

        if (!found) {
            System.out.println("Contact not found. Would you like to add them to your personal contacts (y/n)?");
            String userDecision = scanner.nextLine();
            if (userDecision.equalsIgnoreCase("y")) {
                addContact();
            }
        }
    }

    @Override
    public void editContact() {
        System.out.println("Enter the name of the contact you would like to edit: ");
        String name = scanner.nextLine();
        List<Contact> matches = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().contains(name)) {
                matches.add(contact);
            }
        }
        int userDecision;
        if (matches.size() == 0) {
            System.out.println("Contact not found.\n");
        } else {
            System.out.println("Which contact would you like to edit?");
            int count = 0;
            for (Contact c : matches) {
                System.out.print("(" + ++count + ") ");
                System.out.println(c);
            }
            userDecision = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Please enter the new details for " + matches.get(userDecision - 1).getName() + ": ");
            List<String> contactInfo = getContactDetails();

            contacts.remove(matches.get(userDecision - 1));
            contacts.add(new Contact(contactInfo.get(0), contactInfo.get(1), contactInfo.get(2)));
            System.out.println("Contact edited!\n");
        }
    }

    @Override
    public void readFromFile() {

    }

    @Override
    public void writeToFile() {

    }

    public List<String> getContactDetails() {
        final String emailREGEX = "^\\S+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        final String phoneREGEX = "^(\\d{5}[- .]?){3}\\d{3}$";

        List<String> contactInfo = new ArrayList<>();
        System.out.println("Name: ");
        String name = scanner.nextLine();
        contactInfo.add(name);

        System.out.println("Email: ");
        String email = scanner.nextLine();
        boolean isValidEmail = isValidEmailAddrRegex(emailREGEX, email);
        while (!isValidEmail) {
            System.out.println("Invalid email. Please enter another email: ");
            String newEmail = scanner.nextLine();
            isValidEmail = isValidEmailAddrRegex(emailREGEX, newEmail);
        }
        contactInfo.add(email);

        System.out.println("Phone number: ");
        String phoneNumber = scanner.nextLine();
//        boolean isValidPhoneNum = isValidPhoneNumRegex(phoneREGEX, phoneNumber);
//        while (!isValidPhoneNum) {
//            System.out.println("Invalid phone number. Please enter another number: ");
//            String newNumber = scanner.nextLine();
//            isValidPhoneNum = isValidPhoneNumRegex(emailREGEX, newNumber);
//        } // TODO fix this
        contactInfo.add(phoneNumber);

        return contactInfo;
    }

    public boolean isValidEmailAddrRegex(String emailValidationRegex, String emailAddrToValidate) {
        return Pattern.matches(emailValidationRegex, emailAddrToValidate);
    }

    public boolean isValidPhoneNumRegex(String phoneValidationRegex, String phoneNumToValidate) {
        return Pattern.matches(phoneValidationRegex, phoneNumToValidate);
    }
}
