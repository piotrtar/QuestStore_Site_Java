package com.school.controllers.WebControllers.admin;

import com.school.controllers.WebControllers.UserSessionController;
import com.school.dao.MentorDAO;
import com.school.models.Admin;
import com.school.models.Mentor;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;


import java.io.*;
import java.util.Map;


public class AddMentorController extends UserSessionController implements HttpHandler {


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String response = "";

        Headers requestHeaders = httpExchange.getRequestHeaders();
        Integer userID = getIdFromExistingCookies(requestHeaders);

        if (userID == null) {

            httpExchange.getResponseHeaders().set("Location", "/loginForm");
            httpExchange.sendResponseHeaders(302, response.length());

        } else if (method.equals("GET")) {

            Admin admin = loadAdmin(userID);

            if (admin != null) {
                String cookie = setupCookies(admin);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie);
            }

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/AdminTemplates/addmentor.html");

            JtwigModel model = JtwigModel.newModel();
            response = template.render(model);

        } else if (method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            String firstName = inputs.get("first_name").toString();
            String lastName = inputs.get("last_name").toString();
            String email = inputs.get("email").toString();
            String course = inputs.get("class").toString();
            String password = inputs.get("password").toString();

            Mentor mentor = new Mentor(firstName, lastName, password, email);
            MentorDAO mentorDAO = new MentorDAO(mentor);
            mentorDAO.save();

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/AdminTemplates/admin_account.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("mentor_added", true);

            response = template.render(model);

        }

        final byte[] finalResponseBytes = response.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, finalResponseBytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
