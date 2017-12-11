package com.school.controllers.WebControllers.mentor.student_controllers;

import com.school.controllers.WebControllers.UserSessionController;
import com.school.dao.UserDAO;
import com.school.models.Mentor;
import com.school.models.Student;
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
import java.util.Map;

public class EditStudentController extends UserSessionController implements HttpHandler {

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

            Mentor mentor = loadMentor(userID);

            if (mentor != null) {
                String cookie = setupCookies(mentor);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie);
            }

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/MentorTemplates/editstudent.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("students", userDAO.getAllUsersByStatus("student"));
            response = template.render(model);


        } else if (method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            String id = inputs.get("id").toString();
            User chosenStudent = userDAO.getUserById(Integer.parseInt(id));
            Student student = (Student) chosenStudent;

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/MentorTemplates/editstudent2.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("student", student);
            response = template.render(model);

        }

        final byte[] finalResponseBytes = response.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, finalResponseBytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}

