package org.fasttrackit.service;

import org.fasttrackit.domain.Agenda;
import org.fasttrackit.persistence.AgendaRepository;
import org.fasttrackit.transfer.CreateContactRequest;
import org.fasttrackit.transfer.GetContactRequest;
import org.fasttrackit.transfer.UpdateContactRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AgendaService {

    private AgendaRepository agendaRepository = new AgendaRepository();

    public void createContact(CreateContactRequest request) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Creating contact : " + request);

        agendaRepository.createContact(request);
    }

    public void updateContact(long id, UpdateContactRequest request) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Updating task " + id + ": " + request);

        agendaRepository.updateContact(id, request);
    }

    public void deleteContact(long id) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Deleting contact " + id + ".");

        agendaRepository.deleteContact(id);
    }

    public void deleteContacts(List<Long> idList) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Deleting contacts " + idList + ".");

        agendaRepository.deleteContacts(idList);
    }

    public void deleteAllContacts() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Deleting entire agenda.");

        agendaRepository.deleteAllContacts();
    }

    public List<Agenda> getContacts() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Retrieving all contacts.");
        return agendaRepository.getContacts();
    }

    public Agenda searchContact(GetContactRequest request) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Searching contact after pattern " + request.getPattern() + ".");
        return agendaRepository.getContact(request);
    }
}
