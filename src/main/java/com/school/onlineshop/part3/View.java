package com.school.onlineshop.part3;

import com.school.onlineshop.part1.Basket;
import com.school.onlineshop.part2.Order;
import java.util.Scanner;

public class View {

    public static void printMenu() {
        String menu = "\n 1. Create new product\n"
                + " 2. Create new product category or new featured product category\n"
                + " 3. Add some products to the basket\n"
                + " 4. See all products in my basket\n"
                + " 5. Remove product from basket\n"
                + " 6. Get list of all available products\n"
                + " 7. Get list of products from specific product category\n"
                + " 8. Check availability of specific product\n"
                + " 9. Pay for my order\n";
        System.out.println(menu);

    }

    public static String getInput() {
        Scanner scan = new Scanner(System.in);
        String input;
        do {
            input = scan.nextLine();
        } while(input.length() == 0);

        return input.toUpperCase();
    }

    public static String getDate(){
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        return input;
    }

    public static void provideProductPrice() {
        System.out.println("Please set the price: ");
    }

    public static void provideProductName() {
        System.out.println("Please choose the name of product: ");
    }

    public static void provideCategoryName() {
        System.out.println("Please create name for the category: ");
    }

    public static void provideCategoryType() {
        System.out.println("Would you like to add date to your category? ");
    }

    public static void provideProductNameToAdd() {
        System.out.println("Please provide name of product you want to add: ");
    }

    public static void provideProductNameToDelete() {
        System.out.println("Please type the name of product you want to delete: ");
    }

    public static void acceptOrderMsg() {
        System.out.println("Do you want to confirm your order? [yes/no]: ");
    }

    public static void confirmPaymentMsg() {
        System.out.println("Please type 'yes' to confirm your payment process: [yes/no]: ");
    }

    public static void printOrderInfo(Order order) {
        String info = String.format("Your order id: %s", order.getId());
        System.out.println(info);
    }

    public static void showTotalPriceInfo(Basket basket) {
        String info = String.format("The total price is: $%s", basket.calculateOrderPrice());
        System.out.println(info);
    }

    public static void orderCanceledMsg() {
        System.out.println("Your order has been canceled.");

    }

    public static void showNoAvailableItems() {
        System.out.println("No such items in basket");
    }

    public static void showListIsEmpty() {
        System.out.println("Is empty...");
    }

    public static void chooseCategoryName() {
        System.out.println("Please choose category: ");
    }

    public static void showWrongPriceMsg() {
        System.out.println("Wrong price was provided");
    }

    public static void noChosenCategory() {
        System.out.println("Please choose only available category");
    }

    public static void productAddedMsg() {
        System.out.println("Your product added");
    }

    public static void provideDate() {
        System.out.println("Please provide date in such format: \nExample: January 2, 2010");

    }


}


