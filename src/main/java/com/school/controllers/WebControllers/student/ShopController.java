package com.school.controllers.WebControllers.student;

import com.school.controllers.WebControllers.UserSessionController;
import com.school.dao.ArtifactDAO;
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
import java.util.ArrayList;
import java.util.Map;

public class ShopController extends UserSessionController implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String response = "";

        Headers requestHeaders = httpExchange.getRequestHeaders();

        Integer userID = getIdFromExistingCookies(requestHeaders);
        Student student = loadStudent(userID);
        setupStudentBalance(student);
        setupStudentBasket(student);


        ArtifactDAO artefactDAO = new ArtifactDAO();
        ArrayList<Artifact> artifacts = artefactDAO.getAllArtifacts();

        if (userID == null) {

            httpExchange.getResponseHeaders().set("Location", "/loginForm");
            httpExchange.sendResponseHeaders(302, response.length());

        } else if (method.equals("GET")) {


            if (student != null) {
                String cookie = setupCookies(student);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie);
            }

            JtwigTemplate template = JtwigTemplate.classpathTemplate("static/StudentTemplates/shop.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("students", student);
            model.with("artifacts", artifacts);
            response = template.render(model);


        } else if (method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            String artifactId = inputs.get("id").toString();

            Artifact artifact = artefactDAO.getArtefactById(Integer.parseInt(artifactId));
            student.getBasket().addProduct(artifact);

            StudentDAO studentDAO = new StudentDAO(student);
            studentDAO.saveStudentBasketItem(artifact);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("static/StudentTemplates/shop.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("students", student);
            model.with("artifacts", artifacts);
            model.with("added_to_basket",true);


            response = template.render(model);

        }

        final byte[] finalResponseBytes = response.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, finalResponseBytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}


