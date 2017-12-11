package com.school.controllers.WebControllers.mentor.artifacts_controllers;

import com.school.controllers.WebControllers.UserSessionController;
import com.school.dao.UserDAO;
import com.school.models.Mentor;
import com.school.models.Student;
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

public class MarkStudentArtifactController extends UserSessionController implements HttpHandler  {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String response = "";
        String method = httpExchange.getRequestMethod();

        Headers requestHeaders = httpExchange.getRequestHeaders();
        Integer userID = getIdFromExistingCookies(requestHeaders);


        if (userID == null) {

            httpExchange.getResponseHeaders().set("Location", "/loginForm");
            httpExchange.sendResponseHeaders(302, -1);

        } else if (method.equals("GET")) {

            Mentor mentor = loadMentor(userID);

            if (mentor != null) {
                String cookie = setupCookies(mentor);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie);
            }

            UserDAO userDAO = new UserDAO();
            ArrayList allStudents = userDAO.getAllUsersByStatus("student");

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/MentorTemplates/markstudentartifact.html");
            JtwigModel model = JtwigModel.newModel();
            model.with("students", allStudents);

            response = template.render(model);

        } else if (method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            Integer studentId = Integer.parseInt(inputs.get("id").toString());
            Student student = loadStudent(studentId);
            setupStudentArtifacts(student);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/MentorTemplates/markstudentartifact2.html");
            JtwigModel model = JtwigModel.newModel();
            model.with("student", student);
            model.with("student_artifacts", student.getArtifacts());

            response = template.render(model);
        }

        final byte[] finalResponseBytes = response.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, finalResponseBytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

