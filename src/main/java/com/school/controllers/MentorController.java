package com.school.controllers;


import com.school.dao.*;
import com.school.models.*;
import com.school.views.MentorView;

import java.util.ArrayList;

public class MentorController {

    private Mentor myMentor;

    public MentorController(Mentor mentor){

        myMentor = mentor;
        startController(mentor);
    }

    public static void startController(Mentor mentor) {

        MentorDAO myMentorDao = new MentorDAO(mentor);

        MentorView.welcomeMsg(mentor.getName());
        MentorView.showMenu();

        String choice = MentorView.getChoice();
        startRequestProcess(choice);
    }

    public Mentor getMentor(){
        return myMentor;
    }

    public static void startRequestProcess(String choice) {


        switch (choice) {

            case "1":
                createNewStudentProcess();
                break;

            case "2":
                showAvailableCourses();
                break;

            case "3":
                manageQuests();
                break;

            case "4":
                manageArtefacts();
                break;

            case "5":
                showStudentInfo();
                break;

            case "6":
                markStudentArtefacts();
                break;

            case "7":
                markStudentQuest();
                break;

            case "8":
                setLvlExperience();
                break;

            case "0":
                System.exit(0);
        }
    }

    public static void createNewStudentProcess(){

        String name = MentorView.typeStudentName();
        String surname = MentorView.typeStudentSurname();
        String password = MentorView.typeStudentPassword();
        String mail = MentorView.typeStudentMail();

        showAvailableCourses();
        Integer id = MentorView.getCourseId();

        createNewStudentObject(name, surname, password, mail, id);
    }

    static void createNewStudentObject(String name, String surname, String password,
                                       String mail, Integer id){

        Student student = new Student(name, surname, password, mail);
        CourseDAO courseDAO = new CourseDAO();
        assignStudentToCourse(student, courseDAO.getCourseById(id));

        saveStudent(student);
    }

    public static void assignStudentToCourse(Student student, Course course) {

        student.setCourse(course);
    }

    private static void saveStudent(Student student) {

        StudentDAO myStudent = new StudentDAO(student);
        WalletDAO myWallet = new WalletDAO();

        myWallet.saveWallet(student.getWallet());
        myStudent.save();
    }

    public static void showStudentInfo() {

        String status = "student";
        ArrayList<User> studentsList = new UserDAO().getAllUsersByStatus(status);
        MentorView.showAllStudents(studentsList);

    }

    public static void showAvailableCourses() {

        ArrayList<Course> courses = new CourseDAO().getAllCourses();
        MentorView.showAllCourses(courses);
    }

    public static void manageQuests() {

        String title = MentorView.getQuestTitle();
        String info = MentorView.getQuestInfo();
        Integer prize = MentorView.getQuestPrize();
        String category = MentorView.getQuestCategory();

        Quest quest = new Quest(title, info, prize, category);
        QuestDAO questDAO = new QuestDAO();

        questDAO.saveQuest(quest);
    }

    public static void manageArtefacts() {

        System.out.println("To be implemented");
    }

    public static void markStudentArtefacts() {

        System.out.println("To be implemented");
    }

    public static void markStudentQuest() {

        System.out.println("To be implemented");
    }

    public static void setLvlExperience() {

        UserDAO dao = new UserDAO();
        ArrayList<User> students = dao.getAllUsersByStatus("student");
        Integer id;

        for (User student : students) {
            System.out.println(student.getName());
            System.out.println(student.getId());
        }
           id = MentorView.typeUserId();

           User user = dao.getUserById(id);

        }
    }



