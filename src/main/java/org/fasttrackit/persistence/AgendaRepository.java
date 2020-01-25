package org.fasttrackit.persistence;

import org.fasttrackit.domain.Agenda;
import org.fasttrackit.transfer.CreateAgendaRequest;
import org.fasttrackit.transfer.SearchAgendaRequest;
import org.fasttrackit.transfer.UpdateAgendaRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaRepository {

    public void createContact(CreateAgendaRequest request) throws IOException, SQLException {

        String sql = "INSERT INTO agenda (first_name,last_name,phone_number) VALUES (?,?,?) ";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getFirst_name());
            preparedStatement.setString(2, request.getLast_name());
            preparedStatement.setString(3, request.getPhone_number());

            preparedStatement.executeUpdate();
        }
    }

    public void updateContact(long id, UpdateAgendaRequest request) throws IOException, SQLException {

        String sql = "UPDATE agenda SET first_name=?, last_name=?, phone_number=? WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getFirst_name());
            preparedStatement.setString(2, request.getLast_name());
            preparedStatement.setString(3, request.getPhone_number());
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
                agenda.setFirst_name(resultSet.getString("first_name"));
                agenda.setLast_name(resultSet.getString("last_name"));
                agenda.setPhone_number(resultSet.getString("phone_number"));

                contacts.add(agenda);
            }
            return contacts;
        }
    }

    public List<Agenda> searchContact(SearchAgendaRequest request) throws IOException, SQLException {
        String sql = "SELECT id,first_name,last_name,phone_number FROM agenda WHERE first_name LIKE \"%?%\"" +
                " OR last_name LIKE \"%?%\"";
        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getPattern());

            ResultSet resultSet = preparedStatement.executeQuery(sql);

            List<Agenda> contacts1 = new ArrayList<>();

            while (resultSet.next()) {
                Agenda agenda = new Agenda();
                agenda.setId(resultSet.getLong("id"));
                agenda.setFirst_name(resultSet.getString("first_name"));
                agenda.setLast_name(resultSet.getString("last_name"));
                agenda.setPhone_number(resultSet.getString("phone_number"));

                contacts1.add(agenda);
            }
            return contacts1;
        }
    }
}
