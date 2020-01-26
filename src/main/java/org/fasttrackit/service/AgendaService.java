package org.fasttrackit.service;

import org.fasttrackit.domain.Agenda;
import org.fasttrackit.persistence.AgendaRepository;
import org.fasttrackit.transfer.CreateAgendaRequest;
import org.fasttrackit.transfer.SearchAgendaRequest;
import org.fasttrackit.transfer.UpdateAgendaRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AgendaService {

    private AgendaRepository agendaRepository = new AgendaRepository();

    public void createContact(CreateAgendaRequest request) throws IOException, SQLException {
        System.out.println("Creating contact : " + request);

        agendaRepository.createContact(request);
    }

    public void updateContact(long id, UpdateAgendaRequest request) throws IOException, SQLException {
        System.out.println("Updating task " + id + ": " + request);

        agendaRepository.updateContact(id, request);
    }

    public void deleteContact(long id) throws IOException, SQLException {
        System.out.println("Deleting contact " + id + ".");

        agendaRepository.deleteContact(id);
    }

    public void deleteAllContacts() throws IOException, SQLException {
        System.out.println("Deleting entire agenda.");

        agendaRepository.deleteAllContacts();
    }

    public List<Agenda> getContacts() throws IOException, SQLException {
        System.out.println("Retrieving all contacts.");
        return agendaRepository.getAgenda();
    }

    public List<Agenda> searchContact(SearchAgendaRequest request) throws IOException, SQLException {
        System.out.println("Searching contact after pattern " + request.getPattern() + ".");
        return agendaRepository.searchContact(request);
    }
}
