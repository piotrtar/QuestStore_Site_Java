package com.school.models;

public class UserLogger {

    public static void logIn(User user) {

        if (user.getStatus().equals("student")) {

            Student student = (Student) user;

        }
        if (user.getStatus().equals("admin")) {

            Mentor mentor = (Mentor) user;

        }
        if (user.getStatus().equals("admin")) {

            Admin admin = (Admin) user;

        }
    }
}
