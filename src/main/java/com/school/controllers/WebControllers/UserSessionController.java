package com.school.controllers.WebControllers;

import com.school.dao.StudentDAO;
import com.school.dao.UserDAO;
import com.school.dao.WalletDAO;
import com.school.models.*;
import com.school.onlineshop.part1.Basket;

public abstract class UserSessionController extends Cookie {

    public void setupStudentArtifacts(Student student) {

        StudentDAO studentDAO = new StudentDAO(student);
        student.setArtifacts(studentDAO.getStudentArtefacts());

    }

    public void setupStudentquests(Student student) {

        StudentDAO studentDAO = new StudentDAO(student);
        student.setQuests(studentDAO.getStudentQuests());


    }

    public void setupStudentBalance(Student student) {

        StudentDAO studentDAO = new StudentDAO(student);
        WalletDAO walletDAO = new WalletDAO();
        Wallet myWallet = walletDAO.getWalletById(studentDAO.getStudentWalletId());
        student.setWallet(myWallet);


    }
    public void setupStudentBasket(Student student) {

        StudentDAO studentDAO = new StudentDAO(student);
        Basket basket = studentDAO.getStudentBasket();
        student.setBasket(basket);

    }

    public Mentor loadMentor(Integer userID) {

        UserDAO userDao = new UserDAO();
        User user = userDao.getUserById(userID);

        try {
            Mentor mentor = (Mentor) user;
            return mentor;

        } catch (java.lang.ClassCastException e) {
            return null;
        }
    }

    public Student loadStudent(Integer userID) {

        UserDAO userDao = new UserDAO();
        User user = userDao.getUserById(userID);

        try {
            Student student = (Student) user;
            setupStudentBalance(student);
            setupStudentBasket(student);


            return student;

        } catch (java.lang.ClassCastException e) {
            return null;
        }
    }

    public Admin loadAdmin(Integer userID) {

        UserDAO userDao = new UserDAO();
        User user = userDao.getUserById(userID);

        try {
            Admin admin = (Admin) user;
            return admin;

        } catch (java.lang.ClassCastException e) {
            return null;
        }
    }

    public User loadUser(Integer userID) {

        UserDAO userDao = new UserDAO();
        User user = userDao.getUserById(userID);

        return user;

        }
    }



