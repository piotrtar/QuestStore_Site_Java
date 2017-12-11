package com.school.controllers.WebControllers.admin;

import com.school.controllers.WebControllers.UserSessionController;
import com.school.dao.CourseDAO;
import com.school.models.Admin;
import com.school.models.Course;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.util.Map;


public class AddCourseController extends UserSessionController implements HttpHandler {


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

            Admin admin = loadAdmin(userID);

            if (admin != null) {
                String cookie = setupCookies(admin);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie);
            }

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/AdminTemplates/addcourse.html");

            JtwigModel model = JtwigModel.newModel();
            response = template.render(model);

        } else if (method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            String courseName = inputs.get("course_name").toString();
            Course course = new Course(courseName);

            CourseDAO courseDAO = new CourseDAO();
            courseDAO.saveCourse(course);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/AdminTemplates/admin_account.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("course_added", true);

            response = template.render(model);

        }

        final byte[] finalResponseBytes = response.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, finalResponseBytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

