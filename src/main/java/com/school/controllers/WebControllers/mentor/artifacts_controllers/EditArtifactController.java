package com.school.controllers.WebControllers.mentor.artifacts_controllers;

import com.school.controllers.WebControllers.UserSessionController;
import com.school.dao.ArtifactDAO;
import com.school.models.Artifact;
import com.school.models.Mentor;
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

public class EditArtifactController extends UserSessionController implements HttpHandler {
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

            Mentor mentor = loadMentor(userID);

            if (mentor != null) {
                String cookie = setupCookies(mentor);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie);
            }

            ArtifactDAO artefactDAO = new ArtifactDAO();
            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/MentorTemplates/editartifact.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("artifact", artefactDAO.getAllArtifacts());
            response = template.render(model);


        } else if (method.equals("POST")) {

            ArtifactDAO artefactDAO = new ArtifactDAO();

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            String id = inputs.get("id").toString();
            Artifact artifact = artefactDAO.getArtefactById(Integer.parseInt(id));

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/MentorTemplates/editartifact2.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("artifact", artifact);
            response = template.render(model);

        }

        final byte[] finalResponseBytes = response.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, finalResponseBytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
