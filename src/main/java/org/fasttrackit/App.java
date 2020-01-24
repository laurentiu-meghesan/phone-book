package org.fasttrackit;

import org.fasttrackit.persistence.AgendaRepository;
import org.fasttrackit.transfer.CreateAgendaRequest;
import org.fasttrackit.transfer.UpdateAgendaRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, SQLException {
        System.out.println("Welcome to the phone book!");
        System.out.println("--------------------------");

        AgendaRepository agendaRepository = new AgendaRepository();

//        Create contact
/*        System.out.println("Enter contact details");
        CreateAgendaRequest request = new CreateAgendaRequest();

        boolean flag = false;
        String phone_number;
        do {
            System.out.println("Phone number:");
            Scanner scanner = new Scanner(System.in);
            phone_number = scanner.nextLine();
            char[] ch = phone_number.toCharArray();
            if (Character.isDigit(ch[0]) | phone_number.charAt(0) == '+') {
                flag = true;
            } else {
                System.out.println("You entered an invalid number. Please try again:");
            }
        } while (!flag);

        System.out.println("First name:");
        Scanner scanner1 = new Scanner(System.in);
        String first_name = scanner1.nextLine();

        System.out.println("Last name: ");
        Scanner scanner2 = new Scanner(System.in);
        String last_name = scanner2.nextLine();

        request.setPhone_number(phone_number);
        request.setFirst_name(first_name);
        request.setLast_name(last_name);

        agendaRepository.createContact(request);*/

//        Update contact
/*        System.out.println("Which contact ID do you want to update?");
        UpdateAgendaRequest request1 = new UpdateAgendaRequest();
        Scanner scanner3 = new Scanner(System.in);
        long id = scanner3.nextLong();
        scanner3.nextLine();

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
            String first_name = scanner5.nextLine();

            System.out.println("Enter new last name: ");
            Scanner scanner6 = new Scanner(System.in);
            String last_name = scanner6.nextLine();

            boolean flag3 = false;
            String phone_number;
            do {
                System.out.println("Enter new phone number:");
                Scanner scanner = new Scanner(System.in);
                phone_number = scanner.nextLine();
                char[] ch = phone_number.toCharArray();
                if (Character.isDigit(ch[0]) | phone_number.charAt(0) == '+') {
                    flag3 = true;
                } else {
                    System.out.println("You entered an invalid number. Please try again:");
                }
            } while (!flag3);

            request1.setPhone_number(phone_number);
            request1.setFirst_name(first_name);
            request1.setLast_name(last_name);

            agendaRepository.updateContact(id, request1);
            System.out.println("Contact number " + id + " has been updated to:");
            System.out.println(id + "." + first_name + " " + last_name + " " + phone_number + ".");
        } else {
            System.out.println("Thank you!");
        }*/

//        Delete contact


    }
}
