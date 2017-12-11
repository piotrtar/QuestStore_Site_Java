package com.school.controllers.WebControllers.mentor.student_controllers;

import com.school.controllers.WebControllers.UserSessionController;
import com.school.dao.UserDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;

public class DeleteStudentSubmit extends UserSessionController implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String response = "";

        if(method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);
            Integer student_id = Integer.parseInt(inputs.get("id").toString());

            UserDAO userDAO = new UserDAO();
            userDAO.deleteUser(student_id);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/MentorTemplates/mentor_account.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("student_deleted", true);
            response = template.render(model);
        }

        final byte[] finalResponseBytes = response.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, finalResponseBytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }
}

