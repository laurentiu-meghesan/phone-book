package org.fasttrackit;

import org.fasttrackit.domain.Agenda;
import org.fasttrackit.persistence.AgendaRepository;
import org.fasttrackit.transfer.CreateContactRequest;
import org.fasttrackit.transfer.GetContactRequest;
import org.fasttrackit.transfer.UpdateContactRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\n***** Welcome to the phone book! *****\n");
        System.out.println("        ****[Main Menu]****    ");
        System.out.println("--------------------------------------");

        AgendaRepository agendaRepository = new AgendaRepository();
        boolean flag;

        do {
            System.out.println("What kind of operations do you want to do?");
            System.out.println("1.Create a new contact \n" +
                    "2.Read all available contacts \n" +
                    "3.Search a contact by name or surname \n" +
                    "4.Edit an existing contact \n" +
                    "5.Delete contacts");

            int a = 0;
            try {
                Scanner scanner11 = new Scanner(System.in);
                a = scanner11.nextInt();
                scanner11.nextLine();
            } catch (Exception e) {
                flag = true;
            }

            switch (a) {
                case 1: {
                    //        Create contact
                    System.out.println("Enter contact details");
                    CreateContactRequest request = new CreateContactRequest();

                    boolean flag1 = false;
                    String phoneNumber;
                    do {
                        System.out.println("Phone number:");
                        Scanner scanner = new Scanner(System.in);
                        phoneNumber = scanner.nextLine();
                        char[] ch = phoneNumber.toCharArray();
                        if (Character.isDigit(ch[0]) | phoneNumber.charAt(0) == '+') {
                            flag1 = true;
                        } else {
                            System.out.println("You entered an invalid number. Please try again:");
                        }
                    } while (!flag1);

                    System.out.println("First name:");
                    Scanner scanner1 = new Scanner(System.in);
                    String firstName = scanner1.nextLine();

                    System.out.println("Last name: ");
                    Scanner scanner2 = new Scanner(System.in);
                    String lastName = scanner2.nextLine();

                    request.setPhoneNumber(phoneNumber);
                    request.setFirstName(firstName);
                    request.setLastName(lastName);

                    agendaRepository.createContact(request);
                    System.out.println("Contact " + request.getFirstName() + " " +
                            request.getLastName() + " with phone number " +
                            request.getPhoneNumber() + " has been created.");

                    flag = agendaRepository.continueMakingOperations();
                    break;
                }

                case 4: {
                    //        Update contact
                    UpdateContactRequest request1;
                    long id = 0;
                    boolean flag4;

                    do {
                        System.out.println("Which contact ID do you want to update?");
                        request1 = new UpdateContactRequest();
                        try {
                            Scanner scanner3 = new Scanner(System.in);
                            id = scanner3.nextLong();
                            scanner3.nextLine();
                            flag4 = true;
                        } catch (Exception e) {
                            flag4 = false;
                            System.out.println("You entered an invalid ID. Try again.");
                        }
                    } while (!flag4);

                    System.out.println("Are you sure that you want to update the contact number " + id + "?");
                    System.out.println("Type y for \"yes\" or n for \"no\":");
                    boolean flag1 = false, flag2 = false;
                    do {
                        Scanner scanner4 = new Scanner(System.in);
                        String check = scanner4.nextLine();
                        if (check.charAt(0) == 'y' | check.charAt(0) == 'Y') {
                            flag1 = true;
                        } else if (check.charAt(0) == 'n' | check.charAt(0) == 'N') {
                            flag1 = true;
                            flag2 = true;
                        } else {
                            System.out.println("Please type y or n!");
                        }
                    } while (!flag1);

                    if (flag1 & !flag2) {

                        System.out.println("Enter new first name:");
                        Scanner scanner5 = new Scanner(System.in);
                        String first_name1 = scanner5.nextLine();

                        System.out.println("Enter new last name: ");
                        Scanner scanner6 = new Scanner(System.in);
                        String last_name1 = scanner6.nextLine();

                        boolean flag3 = false;
                        String phone_number1;
                        do {
                            System.out.println("Enter new phone number:");
                            Scanner scanner7 = new Scanner(System.in);
                            phone_number1 = scanner7.nextLine();
                            char[] ch = phone_number1.toCharArray();
                            if (Character.isDigit(ch[0]) | phone_number1.charAt(0) == '+') {
                                flag3 = true;
                            } else {
                                System.out.println("You entered an invalid number. Please try again:");
                            }
                        } while (!flag3);

                        request1.setPhoneNumber(phone_number1);
                        request1.setFirstName(first_name1);
                        request1.setLastName(last_name1);

                        agendaRepository.updateContact(id, request1);
                        System.out.println("Contact number " + id + " has been updated to:");
                        System.out.println(id + "." + first_name1 + " " + last_name1 + " " + phone_number1 + ".");
                    } else {
                        System.out.println("Thank you!");
                    }

                    flag = agendaRepository.continueMakingOperations();
                    break;
                }

                case 5: {
                    //        Delete contact
                    System.out.println("What do you want to delete?");
                    System.out.println("Delete one contact (type 1), delete more contacts at once (type 2)" +
                            " or delete all contacts (type 3)?");

                    boolean flag1 = false;
                    int choice = 0;
                    do {

                        try {
                            Scanner scanner10 = new Scanner(System.in);
                            choice = scanner10.nextInt();
                            scanner10.nextLine();
                        } catch (Exception e) {
                            flag1 = false;
                        }

                        if (choice == 1 | choice == 2) {
                            flag1 = true;
                        } else {
                            System.out.println("Please type 1 or 2!");
                        }
                    } while (!flag1);

                    if (choice == 1) {

                        long id1 = 0;
                        boolean flag4;
                        do {
                            System.out.println("Which contact ID do you want to delete?");
                            try {
                                Scanner scanner8 = new Scanner(System.in);
                                id1 = scanner8.nextLong();
                                scanner8.nextLine();
                                flag4 = true;
                            } catch (Exception e) {
                                flag4 = false;
                                System.out.println("You entered an invalid ID. Try again.");
                            }
                        } while (!flag4);

                        System.out.println("Are you sure that you want to delete contact number " + id1 + "?");
                        System.out.println("Type y for \"yes\" or n for \"no\":");

                        boolean flag2 = false, flag3 = false;
                        do {
                            Scanner scanner9 = new Scanner(System.in);
                            String check = scanner9.nextLine();
                            if (check.charAt(0) == 'y' | check.charAt(0) == 'Y') {
                                flag2 = true;
                            } else if (check.charAt(0) == 'n' | check.charAt(0) == 'N') {
                                flag2 = true;
                                flag3 = true;
                            } else {
                                System.out.println("Please type y or n!");
                            }
                        } while (!flag2);

                        if (flag2 & !flag3) {
                            agendaRepository.deleteContact(id1);
                            System.out.println("Contact number " + id1 + " has been deleted!");
                        } else {
                            System.out.println("Thank you!");
                        }
                    } else if (choice == 2) {
                        System.out.println("How many contacts do you want to delete?");
                        Scanner scanner = new Scanner(System.in);
                        int nrContactsToBeDeleted = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Enter the IDs of the " + nrContactsToBeDeleted +
                                " contacts you want to delete: ");

                        List<Long> idList = new ArrayList<>();
                        for (int i = 0; i<nrContactsToBeDeleted; i++){
                            Scanner scanner1 = new Scanner(System.in);
                            idList.add(scanner1.nextLong());
                        }
                        agendaRepository.deleteContacts(idList);
                        System.out.println("Contacts with IDs " + idList + " have been deleted. Thank you!");

                    } else if (choice == 3) {
                        System.out.println("Are you sure that you want to delete entire agenda?");
                        System.out.println("Type y for \"yes\" or n for \"no\":");

                        boolean flag4 = false, flag5 = false;
                        do {
                            Scanner scanner9 = new Scanner(System.in);
                            String check = scanner9.nextLine();
                            if (check.charAt(0) == 'y' | check.charAt(0) == 'Y') {
                                flag4 = true;
                            } else if (check.charAt(0) == 'n' | check.charAt(0) == 'N') {
                                flag4 = true;
                                flag5 = true;
                            } else {
                                System.out.println("Please type y or n!");
                            }
                        } while (!flag4);

                        if (flag4 & !flag5) {
                            agendaRepository.deleteAllContacts();
                            System.out.println("The entire agenda has been deleted!");
                        } else {
                            System.out.println("Thank you!");
                        }
                    }

                    flag = agendaRepository.continueMakingOperations();
                    break;
                }

                case 2: {
                    //        View Agenda
                    List<Agenda> agenda = agendaRepository.getContacts();
                    System.out.println(agenda);

                    flag = agendaRepository.continueMakingOperations();
                    break;
                }

                case 3: {
                    //         Search a contact by name or surname
                    System.out.println("Enter the name or surname of the contact you" +
                            "want to search for:");
                    Scanner scanner12 = new Scanner(System.in);
                    String pattern = scanner12.nextLine();

                    GetContactRequest request2 = new GetContactRequest();
                    request2.setPattern(pattern);

                    Agenda contact = agendaRepository.getContact(request2);

                    if (contact.getFirstName() == null & contact.getLastName() == null) {
                        System.out.println("No matches found.");
                    } else {
                        System.out.println(contact);
                    }

                    flag = agendaRepository.continueMakingOperations();
                    break;
                }

                default: {
                    flag = true;
                    System.out.println("You entered a non-existent mode! Try again..");
                    System.out.println();
                    break;
                }
            }
        } while (flag);
    }
}
