package com.school.controllers.WebControllers.admin;

import com.school.controllers.WebControllers.UserSessionController;
import com.school.dao.UserDAO;
import com.school.models.Admin;
import com.school.models.Mentor;
import com.school.models.User;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

public class ShowMentorController extends UserSessionController implements HttpHandler {


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String response = "";

        Headers requestHeaders = httpExchange.getRequestHeaders();
        Integer userID = getIdFromExistingCookies(requestHeaders);

        UserDAO userDAO = new UserDAO();

        if (userID == null) {

            httpExchange.getResponseHeaders().set("Location", "/loginForm");
            httpExchange.sendResponseHeaders(302, response.length());

        } else if (method.equals("GET")) {

            Admin admin = loadAdmin(userID);

            if (admin != null) {
                String cookie = setupCookies(admin);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie);
            }

            ArrayList mentors = userDAO.getAllUsersByStatus("mentor");

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/AdminTemplates/showmentor.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("mentors", mentors);
            response = template.render(model);

        } else if (method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            String mentorID = inputs.get("id").toString();

            User chosenMentor = userDAO.getUserById(Integer.parseInt(mentorID));
            Mentor mentor = (Mentor) chosenMentor;

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/AdminTemplates/mentordetails.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("mentors", mentor);
            response = template.render(model);

        }

        final byte[] finalResponseBytes = response.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, finalResponseBytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}


