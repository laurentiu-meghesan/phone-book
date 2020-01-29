package org.fasttrackit.persistence;

import org.fasttrackit.domain.Agenda;
import org.fasttrackit.transfer.CreateAgendaRequest;
import org.fasttrackit.transfer.SearchAgendaRequest;
import org.fasttrackit.transfer.UpdateAgendaRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AgendaRepository {

    public void createContact(CreateAgendaRequest request) throws IOException, SQLException {

        String sql = "INSERT INTO agenda (first_name,last_name,phone_number) VALUES (?,?,?) ";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setString(3, request.getPhoneNumber());

            preparedStatement.executeUpdate();
        }
    }

    public void updateContact(long id, UpdateAgendaRequest request) throws IOException, SQLException {

        String sql = "UPDATE agenda SET first_name=?, last_name=?, phone_number=? WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setString(3, request.getPhoneNumber());
            preparedStatement.setLong(4, id);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteContact(long id) throws IOException, SQLException {

        String sql = "DELETE FROM agenda WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteAllContacts() throws IOException, SQLException {

        String sql = "TRUNCATE TABLE agenda";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.executeUpdate();
        }
    }

    public List<Agenda> getAgenda() throws IOException, SQLException {
        String sql = "SELECT id,first_name,last_name,phone_number FROM agenda";
        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<Agenda> contacts = new ArrayList<>();

            while (resultSet.next()) {
                Agenda agenda = new Agenda();
                agenda.setId(resultSet.getLong("id"));
                agenda.setFirstName(resultSet.getString("first_name"));
                agenda.setLastName(resultSet.getString("last_name"));
                agenda.setPhoneNumber(resultSet.getString("phone_number"));

                contacts.add(agenda);
            }
            return contacts;
        }
    }

    public List<Agenda> searchContact(SearchAgendaRequest request) throws IOException, SQLException {
        String sql = "SELECT id, first_name, last_name, phone_number FROM agenda " +
                "WHERE first_name LIKE ?  OR last_name LIKE ?";
        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getPattern());
            preparedStatement.setString(2, request.getPattern());

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Agenda> contacts1 = new ArrayList<>();

            while (resultSet.next()) {
                Agenda agenda = new Agenda();
                agenda.setId(resultSet.getLong("id"));
                agenda.setFirstName(resultSet.getString("first_name"));
                agenda.setLastName(resultSet.getString("last_name"));
                agenda.setPhoneNumber(resultSet.getString("phone_number"));

                contacts1.add(agenda);
            }
            return contacts1;
        }
    }

    public boolean continueMakingOperations() {

        System.out.println("Do you want to make another operation? Type y for yes, n for no.");
        boolean flag = false, flag1 = false;
        do {
            Scanner scanner15 = new Scanner(System.in);
            String check2 = scanner15.nextLine();
            if (check2.charAt(0) == 'y' | check2.charAt(0) == 'Y') {
                flag = true;
                flag1 = true;
            } else if (check2.charAt(0) == 'n' | check2.charAt(0) == 'N') {
                flag = true;
                flag1 = false;
                System.out.println("Thank you! Goodbye.");
            } else {
                System.out.println("Please type y or n!");
            }
        } while (!flag);
        return flag1;
    }
}
