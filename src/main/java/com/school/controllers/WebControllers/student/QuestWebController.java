package com.school.controllers.WebControllers.student;

import com.school.controllers.WebControllers.UserSessionController;
import com.school.dao.QuestDAO;
import com.school.models.Student;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;

public class QuestWebController extends UserSessionController implements HttpHandler {

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

            Student student = loadStudent(userID);
            setupStudentquests(student);
            QuestDAO questDAO = new QuestDAO();


            if (student != null) {
                String cookie = setupCookies(student);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie);
            }

            JtwigTemplate template = JtwigTemplate.classpathTemplate("static/StudentTemplates/all_quests.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("all_quests", questDAO.getAllQuests());
            model.with("students", student);

            response = template.render(model);

            final byte[] finalResponseBytes = response.getBytes("UTF-8");
            httpExchange.sendResponseHeaders(200, finalResponseBytes.length);

        }

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}


