package org.fasttrackit;

import org.fasttrackit.persistence.AgendaRepository;
import org.fasttrackit.transfer.CreateAgendaRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, SQLException {
        System.out.println("Welcome to the phone book!");

        AgendaRepository agendaRepository = new AgendaRepository();
        CreateAgendaRequest request = new CreateAgendaRequest();

        System.out.println("Enter contact details");
        System.out.println("---------------------");

        boolean flag = false;
        String phone_number;
        do {
            System.out.println("Phone number:");
            Scanner scanner = new Scanner(System.in);
            phone_number = scanner.nextLine();
            char[] ch = phone_number.toCharArray();
            if (Character.isDigit(ch[0])  | phone_number.charAt(0) == '+'){
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

        agendaRepository.createContact(request);

    }
}
