package com.school.controllers.WebControllers.mentor;

import com.school.controllers.WebControllers.UserSessionController;
import com.school.dao.CourseDAO;
import com.school.models.Course;
import com.school.models.Mentor;
import com.school.models.Student;
import com.school.models.User;
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


public class ShowCoursesController extends UserSessionController implements HttpHandler {

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



            CourseDAO courseDAO = new CourseDAO();

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/MentorTemplates/showcourses.html");
            JtwigModel model = JtwigModel.newModel();

            model.with("courses", courseDAO.getAllCourses());

            response = template.render(model);

        } else if (method.equals("POST")) {


            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            Integer courseID = Integer.parseInt(inputs.get("id").toString());


            CourseDAO courseDAO = new CourseDAO();

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/MentorTemplates/coursedetails.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("students_of_course", courseDAO.getAllStudentsOfCourse(courseID));

            response = template.render(model);

        }

        final byte[] finalResponseBytes = response.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, finalResponseBytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

