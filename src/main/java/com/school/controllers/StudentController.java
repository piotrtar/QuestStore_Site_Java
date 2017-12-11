package com.school.controllers;

import com.school.dao.StudentDAO;
import com.school.dao.WalletDAO;
import com.school.models.Student;
import com.school.models.Wallet;
import com.school.views.StudentView;

public class StudentController {

    private Student myStudent;

    public StudentController(Student student){

        myStudent = student;
        startController(student);
    }

    public static void startController(Student student) {

        StudentView.welcomeMsg(student.getName());
        StudentView.showMenu();

//        String choice = StudentView.getChoice();
//        startRequestProcess(choice, student);
    }

    public Student getStudent(){
        return myStudent;
    }


    public static void startRequestProcess(String choice, Student student) {

        switch (choice) {

            case "1":
                showWalletInfo(student);
                break;

            case "2":
                goToStore(); //start Shop controller
                break;

            case "3":
                seeMyLevel(student);
                break;

            case "0":
                System.exit(0);
        }
    }

    public static void showWalletInfo(Student student) {

        Integer balance = student.getWallet().getBalance();

//        StudentView.showWalletInfo(balance);
    }

    public static void seeMyLevel(Student student) {

        Integer experience = student.getWallet().getExperience();

//        StudentView.showMyLevel(experience);
    }

    public static void goToStore() {

        System.out.println("To be implemented.");
    }

    public static void setUpAttributes(Student student){

        StudentDAO studentDAO = new StudentDAO(student);
        WalletDAO walletDAO = new WalletDAO();

        Wallet myWallet = walletDAO.getWalletById(studentDAO.getStudentWalletId());
        student.setWallet(myWallet);
    }


}