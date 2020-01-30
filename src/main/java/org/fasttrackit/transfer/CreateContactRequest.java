package org.fasttrackit.transfer;

public class CreateContactRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "CreateAgendaRequest{" +
                "first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", phone_number='" + phoneNumber + '\'' +
                '}';
    }
}
