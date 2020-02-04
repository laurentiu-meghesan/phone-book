package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.domain.Agenda;
import org.fasttrackit.service.AgendaService;
import org.fasttrackit.transfer.CreateContactRequest;
import org.fasttrackit.transfer.GetContactRequest;
import org.fasttrackit.transfer.UpdateContactRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/agenda")
public class AgendaServlet extends HttpServlet {

    private AgendaService agendaService = new AgendaService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CreateContactRequest request = new ObjectMapper().readValue(req.getReader(), CreateContactRequest.class);

        try {
            agendaService.createContact(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("id") == null) {

            try {
                agendaService.deleteAllContacts();
            } catch (SQLException | ClassNotFoundException e) {
                resp.sendError(500, "Internal server error: " + e.getMessage());
            }
        } else if (req.getParameterValues("id").length < 2) {

            String id = req.getParameter("id");

            try {
                agendaService.deleteContact(Long.parseLong(id));
            } catch (SQLException | ClassNotFoundException e) {
                resp.sendError(500, "Internal server error: " + e.getMessage());
            }

        } else {
            List<Long> idList = new ArrayList<>();
            String[] ids = req.getParameterValues("id");

            for (int i = 0; i < req.getParameterValues("id").length; i++) {
                idList.add(Long.parseLong(ids[i]));
            }

            try {
                agendaService.deleteContacts(idList);
            } catch (SQLException | ClassNotFoundException e) {
                resp.sendError(500, "Internal server error: " + e.getMessage());
            }

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        UpdateContactRequest request =
                new ObjectMapper().readValue(req.getReader(), UpdateContactRequest.class);

        try {
            agendaService.updateContact(Long.parseLong(id), request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("pattern") == null) {

            try {
                List<Agenda> contactsList = agendaService.getContacts();

                String response = new ObjectMapper().writeValueAsString(contactsList);
                resp.getWriter().print(response);
            } catch (SQLException | ClassNotFoundException e) {
                resp.sendError(500, "Internal server error: " + e.getMessage());
            }
        } else {

            GetContactRequest request = new GetContactRequest();
            String pattern = req.getParameter("pattern");
            request.setPattern(pattern);
            try {

                String response = new ObjectMapper().writeValueAsString(agendaService.getContact(request));
                resp.getWriter().print(response);

            } catch (SQLException | ClassNotFoundException e) {
                resp.sendError(500, "Internal server error: " + e.getMessage());
            }
        }
    }
}
