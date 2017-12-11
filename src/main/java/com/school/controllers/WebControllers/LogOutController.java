package com.school.controllers.WebControllers;

import com.school.models.User;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class LogOutController extends UserSessionController implements HttpHandler {


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {


        String response = "";
        String method = httpExchange.getRequestMethod();

        Headers requestHeaders = httpExchange.getRequestHeaders();
        Integer userID = getIdFromExistingCookies(requestHeaders);

        if (method.equals("GET")) {

            System.out.println("here");

            User user = loadUser(userID);
            System.out.println(user.getId());
            String cookie = removeCookies(user);
            httpExchange.getResponseHeaders().add("Set-Cookie", cookie);

            httpExchange.getResponseHeaders().set("Location", "/loginForm");
            httpExchange.sendResponseHeaders(302, -1);

        }
    }
}
