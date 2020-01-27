package org.fasttrackit;

import org.fasttrackit.domain.Agenda;
import org.fasttrackit.persistence.AgendaRepository;
import org.fasttrackit.transfer.CreateAgendaRequest;
import org.fasttrackit.transfer.SearchAgendaRequest;
import org.fasttrackit.transfer.UpdateAgendaRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, SQLException {
        System.out.println("Welcome to the phone book!");
        System.out.println("--------------------------");

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
                    CreateAgendaRequest request = new CreateAgendaRequest();

                    boolean flag1 = false;
                    String phone_number;
                    do {
                        System.out.println("Phone number:");
                        Scanner scanner = new Scanner(System.in);
                        phone_number = scanner.nextLine();
                        char[] ch = phone_number.toCharArray();
                        if (Character.isDigit(ch[0]) | phone_number.charAt(0) == '+') {
                            flag1 = true;
                        } else {
                            System.out.println("You entered an invalid number. Please try again:");
                        }
                    } while (!flag1);

                    System.out.println("First name:");
                    Scanner scanner1 = new Scanner(System.in);
                    String first_name = scanner1.nextLine();

                    System.out.println("Last name: ");
                    Scanner scanner2 = new Scanner(System.in);
                    String last_name = scanner2.nextLine();

                    request.setPhone_number(phone_number);
                    request.setFirst_name(first_name);
                    request.setLast_name(last_name);

                    agendaRepository.createContact(request);
                    System.out.println("Contact " + request.getFirst_name() + " " +
                            request.getLast_name() + " with phone number " +
                            request.getPhone_number() + " has been created.");

                    flag = agendaRepository.continueMakingOperations();
                    break;
                }

                case 4: {
                    //        Update contact
                    UpdateAgendaRequest request1;
                    long id = 0;
                    boolean flag4;

                    do {
                        System.out.println("Which contact ID do you want to update?");
                        request1 = new UpdateAgendaRequest();
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

                        request1.setPhone_number(phone_number1);
                        request1.setFirst_name(first_name1);
                        request1.setLast_name(last_name1);

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
                    System.out.println("Delete one contact (type 1) or delete all contacts (type 2)?");

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
                    List<Agenda> agenda = agendaRepository.getAgenda();
                    System.out.println(agenda);

                    flag = agendaRepository.continueMakingOperations();
                    break;
                }

                case 3: {
                    //         Search a contact by pattern
                    System.out.println("Enter a pattern to search in agenda:");
                    Scanner scanner12 = new Scanner(System.in);
                    String pattern = "%" + scanner12.nextLine() + "%";

                    SearchAgendaRequest request2 = new SearchAgendaRequest();
                    request2.setPattern(pattern);
                    List<Agenda> agenda2 = agendaRepository.searchContact(request2);

                    if (agenda2 != null) {
                        System.out.println(agenda2);
                    } else if (agenda2 == null) {
                        System.out.println("No matches found.");
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
