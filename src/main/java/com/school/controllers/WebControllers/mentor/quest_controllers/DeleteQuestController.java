package com.school.controllers.WebControllers.mentor.quest_controllers;

import com.school.controllers.WebControllers.UserSessionController;

import com.school.dao.QuestDAO;
import com.school.models.Mentor;
import com.school.models.Quest;
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

public class DeleteQuestController  extends UserSessionController implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String response = "";

        Headers requestHeaders = httpExchange.getRequestHeaders();
        Integer userID = getIdFromExistingCookies(requestHeaders);

        QuestDAO questDAO = new QuestDAO();

        if (userID == null) {

            httpExchange.getResponseHeaders().set("Location", "/loginForm");
            httpExchange.sendResponseHeaders(302, response.length());

        } else if (method.equals("GET")) {

            Mentor mentor = loadMentor(userID);

            if (mentor != null) {
                String cookie = setupCookies(mentor);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie);
            }

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/MentorTemplates/deletequest.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("quests", questDAO.getAllQuests());
            response = template.render(model);


        } else if (method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            Integer id = Integer.parseInt(inputs.get("quest_id").toString());
            Quest quest  = questDAO.getQuestById(id);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/MentorTemplates/deletequest2.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("quest", quest);
            model.with("quests", questDAO.getAllQuests());

            response = template.render(model);

        }

        final byte[] finalResponseBytes = response.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, finalResponseBytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
