package com.school.models;


import com.school.onlineshop.part1.Basket;

import java.util.ArrayList;

public class Student extends User {

    private Wallet wallet;
    private Course course;
    private static String status = "student";
    private ArrayList<Artifact> artifacts;
    private ArrayList<Quest> quests;
    private Basket basket;

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public void setQuests(ArrayList<Quest> quests) {
        this.quests = quests;
    }

    public ArrayList<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(ArrayList<Artifact> artifacts) {
        this.artifacts = artifacts;
    }


    public Student(){
    }


    public Student(String name, String surname, String password, String mail){

        super(name, surname, password, mail, status);
        this.wallet = new Wallet();
        this.basket = new Basket();

    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}