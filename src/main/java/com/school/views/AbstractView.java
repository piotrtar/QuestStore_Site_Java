package com.school.views;

import java.util.Scanner;

public abstract class AbstractView {

    private static Scanner scan = new Scanner(System.in);

    public static void welcomeMsg(String name){
        String message = String.format(("Welcome %s!"),name);
        System.out.println(message);
    }

    public static Integer typeUserId(){
        System.out.println("Please provide the admin's id: ");
        Integer mail = scan.nextInt();
        return mail;
    }
}
