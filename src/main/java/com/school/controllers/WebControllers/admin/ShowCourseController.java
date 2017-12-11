package com.school.controllers.WebControllers.admin;

import com.school.controllers.WebControllers.UserSessionController;
import com.school.dao.CourseDAO;
import com.school.dao.StudentDAO;
import com.school.models.Admin;
import com.school.models.Course;
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

public class ShowCourseController extends UserSessionController implements HttpHandler {


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String response = "";

        Headers requestHeaders = httpExchange.getRequestHeaders();
        Integer userID = getIdFromExistingCookies(requestHeaders);

        CourseDAO courseDAO = new CourseDAO();

        if (userID == null) {

            httpExchange.getResponseHeaders().set("Location", "/loginForm");
            httpExchange.sendResponseHeaders(302, response.length());

        } else if (method.equals("GET")) {

            Admin admin = loadAdmin(userID);

            if (admin != null) {
                String cookie = setupCookies(admin);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie);
            }

            ArrayList<Course> courses = courseDAO.getAllCourses();

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/AdminTemplates/showcourse.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("courses", courses);
            response = template.render(model);

        } else if (method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            String courseID = inputs.get("id").toString();

            Course course = courseDAO.getCourseById(Integer.parseInt(courseID));
            ArrayList<Student> students = courseDAO.getAllStudentsOfCourse(Integer.parseInt(courseID));
            course.setStudents(students);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/static/AdminTemplates/coursedetails.html");

            JtwigModel model = JtwigModel.newModel();
            model.with("courses", course);
            model.with("course_students", course.getStudents());
            response = template.render(model);
        }

        final byte[] finalResponseBytes = response.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, finalResponseBytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}


