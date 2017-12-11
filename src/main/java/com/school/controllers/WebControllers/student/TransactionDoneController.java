package com.school.controllers.WebControllers.student;

import com.school.controllers.WebControllers.UserSessionController;
import com.school.dao.BasketDAO;
import com.school.dao.WalletDAO;
import com.school.models.Student;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;


public class TransactionDoneController extends UserSessionController implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String response = "";

        Headers requestHeaders = httpExchange.getRequestHeaders();

        Integer userID = getIdFromExistingCookies(requestHeaders);
        Student student = loadStudent(userID);
        setupStudentBalance(student);

        if (userID == null) {

            httpExchange.getResponseHeaders().set("Location", "/loginForm");
            httpExchange.sendResponseHeaders(302, response.length());

        } else if (method.equals("POST")) {

            if (student != null) {
                String cookie = setupCookies(student);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie);
            }

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);
            Integer totalAmount = Integer.parseInt(inputs.get("total").toString());

            JtwigTemplate template = null;

            if(ifUserHasBalance(student, totalAmount)){
                template = JtwigTemplate.classpathTemplate("static/StudentTemplates/transactionSuccessful.html");
            } else {
                template = JtwigTemplate.classpathTemplate("static/StudentTemplates/transactionUnsuccessful.html");
            }

            JtwigModel model = JtwigModel.newModel();
            model.with("student", student);
            response = template.render(model);

        }

        final byte[] finalResponseBytes = response.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, finalResponseBytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private Boolean ifUserHasBalance(Student student, Integer totalAmount) {

        if (student.getWallet().getBalance() >= totalAmount && totalAmount!= 0) {

            student.getWallet().setBalance(student.getWallet().getBalance() - totalAmount);
            student.getBasket().clearBasket();

            WalletDAO walletDAO = new WalletDAO();
            walletDAO.editWallet(student.getWallet());

            BasketDAO basketDAO = new BasketDAO();
            basketDAO.deleteBasket(student);
            return true;

        } else {
            return false;
        }
    }
}


