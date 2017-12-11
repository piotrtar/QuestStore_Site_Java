package com.school.controllers.WebControllers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;

public class SignUpWebController implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String response = "";

        Headers requestHeaders = httpExchange.getRequestHeaders();

        if (method.equals("GET")) {

            JtwigTemplate template = JtwigTemplate.classpathTemplate("signUpForm.twig");
            JtwigModel model = JtwigModel.newModel();

            response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

        }

        if (method.equals("POST")) {

        }



        }
    }

