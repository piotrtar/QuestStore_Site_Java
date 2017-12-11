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

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class EditMentorController extends UserSessionController implements HttpHandler {

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

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/AdminTemplates/editmentor.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("mentors", userDAO.getAllUsersByStatus("mentor"));
            response = template.render(model);


        } else if (method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            String id = inputs.get("id").toString();
            User chosenMentor = userDAO.getUserById(Integer.parseInt(id));
            Mentor mentor = (Mentor) chosenMentor;

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/AdminTemplates/editmentor2.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("mentor", mentor);
            response = template.render(model);

        }

        final byte[] finalResponseBytes = response.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, finalResponseBytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}

