package com.school.controllers.WebControllers.admin;

import com.school.controllers.WebControllers.UserSessionController;
import com.school.dao.MentorDAO;
import com.school.dao.UserDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class SubmitMentorEdition extends UserSessionController implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String response = "";

        if(method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);
            String mail = inputs.get("email").toString();
            String name = inputs.get("first_name").toString();
            String surname = inputs.get("last_name").toString();
            Integer id = Integer.parseInt(inputs.get("id").toString());
            String password = inputs.get("password").toString();

            UserDAO.editUser(name, surname, mail, password, id);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/AdminTemplates/admin_account.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("mentor_edited", true);
            response = template.render(model);

        }

        final byte[] finalResponseBytes = response.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, finalResponseBytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }
}


