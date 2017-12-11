package com.school.models;



public class Mentor extends User {

    private static String status = "mentor";

    public Mentor(){}

    public Mentor(String name, String surname, String password, String mail) {
        super(name, surname, password, mail, status);
    }
}
