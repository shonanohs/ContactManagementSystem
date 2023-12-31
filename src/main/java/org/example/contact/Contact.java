package org.example.contact;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private String email;
    private String phoneNumber;

    public Contact(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } // Could use setters to edit contacts eventually instead of removing then adding?

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toFileString() {
        return name + "," + email + "," + phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("%-20s| %-30s| %-25s", name, email, phoneNumber);
    }
}
