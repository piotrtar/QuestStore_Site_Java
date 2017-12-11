package com.school.views;

import java.util.Scanner;

public class StudentView extends AbstractView{

    private static Scanner scan = new Scanner(System.in);
    private static int inputNum;

    public static String getChoice() {
        Scanner scanForNumbers = new Scanner(System.in);
        do {
            System.out.println("Please choose an option: ");
            inputNum = scanForNumbers.nextInt();
        } while (inputNum <0 | inputNum >3);

        return Integer.toString(inputNum);
    }

    public static void showMenu() {
        String menu = "\n1. Show wallet."
                + "\n2. Go to store."
                + "\n3. See my level."
                + "\n0. Exit";

        System.out.println(menu);
    }

    public static void showWalletInfo(Integer balance) {

        String walletInfo = "\nYour actual experience is: " + balance.toString();

        System.out.println(walletInfo);
    }

    public static void showMyLevel(Integer level) {

        String levelInfo = "\nYour actural level is : " + level.toString();

        System.out.println(levelInfo);
    }

}