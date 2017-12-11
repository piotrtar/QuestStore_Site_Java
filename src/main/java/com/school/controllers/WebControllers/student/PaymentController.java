package com.school.controllers.WebControllers.student;

import com.school.controllers.WebControllers.UserSessionController;
import com.school.dao.QuestDAO;
import com.school.dao.StudentDAO;
import com.school.models.Artifact;
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
import java.util.Map;

public class PaymentController extends UserSessionController implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String response = "";

        Headers requestHeaders = httpExchange.getRequestHeaders();
        Integer userID = getIdFromExistingCookies(requestHeaders);

        Student student = loadStudent(userID);

        setupStudentBalance(student);
        setupStudentBasket(student);


        if (userID == null) {

            httpExchange.getResponseHeaders().set("Location", "/loginForm");
            httpExchange.sendResponseHeaders(302, response.length());

        } else if (method.equals("GET")) {

            if (student != null) {
                String cookie = setupCookies(student);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie);
            }

            JtwigTemplate template = JtwigTemplate.classpathTemplate("static/StudentTemplates/shoppingcart.html");

            JtwigModel model = JtwigModel.newModel();


            model.with("student_artifacts", student.getBasket().getProductList());
            model.with("student", student);
            model.with("sumProducts", student.getBasket().getSumOfBasket());


            response = template.render(model);

       } else if (method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);
            Integer artifactId = Integer.parseInt(inputs.get("id_to_delete").toString());

            StudentDAO studentDAO = new StudentDAO(student);
            studentDAO.deleteProductFromBasket(artifactId);
            setupStudentBasket(student);


            JtwigTemplate template = JtwigTemplate.classpathTemplate("static/StudentTemplates/shoppingcart.html");
            JtwigModel model = JtwigModel.newModel();

            model.with("student_artifacts", student.getBasket().getProductList());
            model.with("student", student);
            model.with("sumProducts", student.getBasket().getSumOfBasket());

            response = template.render(model);

        }
        final byte[] finalResponseBytes = response.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, finalResponseBytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

