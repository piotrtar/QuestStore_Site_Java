package com.school.dao;

import com.school.models.Course;
import com.school.models.Student;
import com.school.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CourseDAO extends DBConnection {

    private static final String tableName = "courses";

    public CourseDAO() {

        super(tableName);
    }

    public ArrayList<Course> getAllCourses() {

        Course course;
        ArrayList<Course> courses = new ArrayList<>();

        String query = "SELECT * FROM " + tableName + ";";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {

                course = createCourseFromResultSet(rs);
                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            return courses;
        }
    }

    public Course getCourseById(Integer id) {

        Course course = null;

        String query = "SELECT * FROM courses WHERE crs_id = " + id + ";";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {
                course = createCourseFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            return course;
        }
    }


    public static Course createCourseFromResultSet(ResultSet rs) throws SQLException {

        Integer id = rs.getInt("crs_id");
        String name = rs.getString("course_name");

        Course course = new Course(name);
        course.setId(id);

        return course;

    }

    public void saveCourse(Course course) {

        String query = "INSERT INTO courses (course_name) VALUES(?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, course.getName());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Student> getAllStudentsOfCourse(Integer id) {

        ArrayList<Student> allStudents = new ArrayList();
        String query = "SELECT * FROM students WHERE course_id = " + id + ";";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while(rs.next()){
                Integer studentID = rs.getInt("student_id");
                UserDAO userDAO = new UserDAO();
                User studentObject = userDAO.getUserById(studentID);
                Student student = (Student) studentObject;
                allStudents.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            return allStudents;

        }
    }
}

