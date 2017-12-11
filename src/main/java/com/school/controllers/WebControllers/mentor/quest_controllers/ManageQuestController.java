package com.school.controllers.WebControllers.mentor.quest_controllers;

import com.school.controllers.WebControllers.UserSessionController;
import com.school.dao.QuestDAO;
import com.school.models.Mentor;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;

public class ManageQuestController extends UserSessionController implements HttpHandler {

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

            QuestDAO questDAO = new QuestDAO();

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/MentorTemplates/managequests.html");
            JtwigModel model = JtwigModel.newModel();
            model.with("quests", questDAO.getAllQuests());

            response = template.render(model);
            httpExchange.sendResponseHeaders(200, response.length());
        }

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }
}

