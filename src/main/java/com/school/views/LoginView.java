package com.school.views;

import java.util.Scanner;

public class LoginView {

    private static Scanner scan = new Scanner(System.in);
    private static String input;

    public static String getInput() {
        do {
            input = scan.nextLine();
        } while (input.length() == 0);
        return input;
    }

    public static String getUserChoiceInput() {

        System.out.println("Please type an option: ");
        int input = -1;

        while (input < 0 || input > 3) {
            while (!scan.hasNextInt()) {
                System.out.println("Please type only options from 1 to 3: ");
                scan.next();
            }
            input = scan.nextInt();
        }
        return String.valueOf(input);
        }


    public static void setUserLoginMsg(){

        String loginMenu = "\n 1. Admin\n"
                            + " 2. Mentor\n"
                            + " 3. Student\n";

        System.out.println(loginMenu);
    }

    public static void setUserId(){
        System.out.println("Please provide your id: ");
    }
    
    public static void setUserPassword(){
        System.out.println("Please provide your password: ");
    }

    public static void failedLoginMsg(){
        System.out.println("Wrong id or password");
    }


}



