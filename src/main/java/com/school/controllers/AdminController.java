package com.school.controllers;

import com.school.dao.CourseDAO;
import com.school.dao.MentorDAO;
import com.school.dao.UserDAO;
import com.school.models.Admin;
import com.school.models.Course;
import com.school.models.Mentor;
import com.school.models.User;
import com.school.views.AdminView;
import com.school.views.MentorView;

import java.util.ArrayList;

public class AdminController {

    private Admin myAdmin;

    public AdminController(Admin admin){

        myAdmin = admin;
        startController(admin);
    }

    public static void startController(Admin admin) {

        AdminView.welcomeMsg(admin.getName());
        AdminView.showMenu();

        String choice = AdminView.getChoice();
        startRequestProcess(choice);

    }

    public Admin getAdmin(){
        return myAdmin;
    }



    public static void startRequestProcess(String choice){

        switch (choice) {
            case "1":
                createMentor();
                break;

            case "2":
                createCourse();
                break;



            case "4":
                showCourseInfo();
                break;

            case "5":
                showMentorProfile();
                break;

            case "0":
                System.exit(0);
        }


    }

    public static Mentor createMentor(){


        String name = AdminView.typeMentorName();
        String surname = AdminView.typeMentorSurname();
        String password = AdminView.typeMentorPassword();
        String mail = AdminView.typeMentorEmail();

        Mentor mentor = new Mentor(name, surname, password, mail);

        saveMentor(mentor);

        return mentor;

    }

    public static void saveMentor(Mentor mentor){

        MentorDAO myMentor = new MentorDAO(mentor);
        myMentor.save();
    }

    public static void createCourse(){

        String courseName = AdminView.typeCourseName();
        Course course = new Course(courseName);
        CourseDAO courseDao = new CourseDAO();
        courseDao.saveCourse(course);
    }


    public static void showCourseInfo(){

        CourseDAO courseDAO = new CourseDAO();
        ArrayList<Course> courses = courseDAO.getAllCourses();
        AdminView.showAllCourses(courses);
        Integer courseId = AdminView.typeCourseId();

        Course course = courseDAO.getCourseById(courseId);
        AdminView.showCourseInfo(course);

    }

    public static void showMentorProfile(){

        ArrayList<User> mentorsList = new UserDAO().getAllUsersByStatus("admin");

        for(User mentor : mentorsList){
            MentorView.showStudent(mentor);
        }
        Integer id = AdminView.typeMentorId();
        User user = new UserDAO().getUserById(id);

        MentorView.showStudent(user);
        }
    }

