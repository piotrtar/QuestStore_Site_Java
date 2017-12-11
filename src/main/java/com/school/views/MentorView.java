package com.school.views;

import com.school.models.Course;
import com.school.models.User;

import java.util.ArrayList;
import java.util.Scanner;

public class MentorView extends AbstractView {

    private static Scanner scan = new Scanner(System.in);
    private static int inputNum;

    public static String getChoice() {
        Scanner scanForNumbers = new Scanner(System.in);
        do {
            System.out.println("Please choose an option: ");
            inputNum = scanForNumbers.nextInt();
        } while (inputNum < 0 | inputNum > 8);

        return Integer.toString(inputNum);
    }

    public static void showMenu() {
        String menu = "\n1. Create student"
                + "\n2. Show all courses"
                + "\n3. Manage Quests"
                + "\n4. Manage Artefacts"
                + "\n5. Show info about all Students"
                + "\n6. Mark Student's Artefacts"
                + "\n7. Mark Student's Quests"
                + "\n8. Set level experience"
                + "\n0. Exit";

        System.out.println(menu);
    }

    public static String typeStudentName() {

        System.out.println("\nPlease provide the student's name: ");
        String name = scan.nextLine();
        return name;
    }

    public static String typeStudentSurname() {

        System.out.println("Please provide the student's surname: ");
        String surname = scan.nextLine();
        return surname;
    }

    public static String typeStudentPassword() {

        System.out.println("Please provide the password for student: ");
        String password = scan.nextLine();
        return password;
    }

    public static String typeStudentMail() {
        System.out.println("Please provide the student's mail: ");
        String mail = scan.nextLine();
        return mail;
    }

    public static Integer getMentorId() {
        System.out.println("Please provide admin's id: ");
        Integer id = scan.nextInt();
        return id;
    }

    public static Integer getCourseId() {
        System.out.println("Please provide course id: ");
        Integer id = scan.nextInt();
        return id;
    }

    public static void showStudent(User mentor) {

        System.out.println(mentor.getName());
        System.out.println(mentor.getSurname());
        System.out.println(mentor.getId());

    }

    public static String getQuestTitle() {

        System.out.println("Please provide title for quest: ");
        String title = scan.nextLine();
        return title;
    }

    public static String getQuestInfo() {

        System.out.println("Please provide info for quest: ");
        String info = scan.nextLine();
        return info;
    }

    public static Integer getQuestPrize() {

        System.out.println("Please provide prize for quest: ");
        Scanner scanForNumbers = new Scanner(System.in);
        Integer prize = scanForNumbers.nextInt();

        return prize;
    }

    public static String getQuestCategory(){

        System.out.println("Please provide category for quest: ");
        String category = scan.nextLine();

        return category;
    }

    public static void showAllCourses(ArrayList<Course> courses) {

        for (Course course : courses) {
            System.out.println(course.getId() + " " + course.getName());
        }
    }

    public static void showAllStudents(ArrayList<User> students){

        for (User student: students) {
            System.out.println(student.getName() + " " + student.getSurname());

        }
    }
}





