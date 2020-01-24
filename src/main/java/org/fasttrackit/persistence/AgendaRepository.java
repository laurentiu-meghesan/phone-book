package org.fasttrackit.persistence;

import org.fasttrackit.transfer.CreateAgendaRequest;
import org.fasttrackit.transfer.UpdateAgendaRequest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
