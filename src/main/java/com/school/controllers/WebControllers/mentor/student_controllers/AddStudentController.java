package com.school.controllers.WebControllers.mentor.student_controllers;

import com.school.controllers.WebControllers.UserSessionController;
import com.school.dao.StudentDAO;
import com.school.dao.WalletDAO;
import com.school.models.Mentor;
import com.school.models.Student;
import com.school.models.Wallet;
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

public class AddStudentController extends UserSessionController implements HttpHandler {


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

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/MentorTemplates/addstudent.html");
            JtwigModel model = JtwigModel.newModel();

            response = template.render(model);

        } else if (method.equals("POST")) {

            System.out.println("is post");

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            String firstName = inputs.get("first_name").toString();
            String lastName = inputs.get("last_name").toString();
            String email = inputs.get("email").toString();
            String course = inputs.get("course").toString();
            String password = inputs.get("password").toString();

            Student student = new Student(firstName, lastName, password, email);
            StudentDAO studentDAO = new StudentDAO(student);

            Wallet wallet = new Wallet();
            WalletDAO walletDAO = new WalletDAO();

            walletDAO.saveWallet(wallet);
            studentDAO.save();

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/MentorTemplates/mentor_account.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("student_added", true);

            response = template.render(model);

        }

        final byte[] finalResponseBytes = response.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, finalResponseBytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
