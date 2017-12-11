package com.school.controllers;

import com.school.dao.UserDAO;
import com.school.models.User;
import com.school.models.UserLogger;
import com.school.views.LoginView;


public class LoginController {

    public static void roleSetting() {

        LoginView.setUserId();
        String id = LoginView.getInput();
        LoginView.setUserPassword();
        String password = LoginView.getInput();
        startLoginProcess(id, password);

    }

    public static User startLoginProcess(String id, String password) {

        UserDAO dao = new UserDAO();
        User user = dao.load(id, password);
        if (user != null) {
            return user;
        } else {
//                UserLogger.logIn(user);
//         else
//                LoginView.failedLoginMsg();
            return null;
        }
    }
}
