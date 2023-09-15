package org.example.contactcategories;

import org.example.contact.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaseContactCategory implements ContactCategory {
    List<Contact> contacts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void addContact() {
        System.out.println("Please enter the following information for the contact you " +
                "would like to add:\nName:");
        String name = scanner.next();
        System.out.println("Email: ");
        String email = scanner.next();
        System.out.println("Phone number: ");
        String phoneNumber = scanner.next();
        contacts.add(new Contact(name, email, phoneNumber));
        System.out.println("Contact added!");
    }

    @Override
    public void removeContact() {
        System.out.println("Please enter the name of the contact to remove");
        String name = scanner.next();
        List<Contact> matches = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().contains(name)) {
                matches.add(contact);
            }
        }
        switch (matches.size()) {
            case 0 -> {
                System.out.println("Contact not found.");
            }
            case 1 -> {
                contacts.remove(matches.get(0));
                System.out.println("Contact removed!");
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
                System.out.println("Contact removed!");
            }
        }
    }

    @Override
    public void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Contact list is empty.");
        }
        for (Contact c : contacts) {
            System.out.println(c);
        }
        System.out.println();
    }

    @Override
    public void searchContacts() {
        System.out.println("Enter the name/characters you would like to search for.");
        String search = scanner.next();
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().contains(search)) {
                found = true;
                System.out.println(contact);
            }
        }

        if (found == false) {
            System.out.println("Contact not found. Would you like to add them to your personal contacts (y/n)?");
            String userDecision = scanner.next();
            if (userDecision.equalsIgnoreCase("y")) {
                addContact();
            }
        }
    }
}
