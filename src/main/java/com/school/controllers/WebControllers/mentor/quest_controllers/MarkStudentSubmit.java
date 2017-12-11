package com.school.controllers.WebControllers.mentor.quest_controllers;

import com.school.controllers.WebControllers.UserSessionController;
import com.school.dao.QuestDAO;
import com.school.dao.WalletDAO;
import com.school.models.Quest;
import com.school.models.Student;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;

public class MarkStudentSubmit extends UserSessionController implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String response = "";
        String method = httpExchange.getRequestMethod();

         if (method.equals("POST")) {

            System.out.println("here");

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            Integer studentId = Integer.parseInt(inputs.get("student_id").toString());
            Integer questId = Integer.parseInt(inputs.get("quest_id").toString());

            QuestDAO questDAO = new QuestDAO();
            Quest quest = questDAO.getQuestById(questId);
            questDAO.deleteStudentQuest(studentId, questId);

            Student student = loadStudent(studentId);
            setupStudentBalance(student);

            Integer questPrize = quest.getPrize();

            Integer studentBalance = student.getWallet().getBalance();

            Integer newStudentBalance = questPrize + studentBalance;
            student.getWallet().setBalance(newStudentBalance);

            WalletDAO walletDAO = new WalletDAO();
            walletDAO.editWallet(student.getWallet());

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/MentorTemplates/mentor_account.html");
            JtwigModel model = JtwigModel.newModel();
            model.with("quest_marked", true);
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

