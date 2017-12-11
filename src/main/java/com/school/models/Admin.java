package com.school.models;


public class Admin extends User {

    private static String status = "admin";

    public Admin(){}

    public Admin(String name, String surname, String password, String mail) {
        super(name, surname, password, mail, status);
    }
}

