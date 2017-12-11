//package com.school.onlineshop.part3;
//
//import com.school.onlineshop.part1.Basket;
//import com.school.onlineshop.part1.FeaturedProductCategory;
//import com.school.onlineshop.part1.Product;
//import com.school.onlineshop.part1.ProductCategory;
//import com.school.onlineshop.part2.CheckoutProcess;
//import com.school.onlineshop.part2.Order;
//import com.school.onlineshop.part2.PaymentProcess;
//
//import java.util.ArrayList;
//
//public class Application {
//
//    public static void main(String[] args) {
//
////        Basket basket = new Basket();
//
//
////            showMenu();
////            String answer = View.getInput();
//
//
////            } else if (answer.equals("2")) {
////                createNewCategory();
////
////            } else if (answer.equals("3")) {
////                addToBasket(basket);
////
////            } else if (answer.equals("4")) {
////                seeAllProducts(basket);
////
////            } else if (answer.equals("5")) {
////                removeFromBasket(basket);
////
////            } else if (answer.equals("6")) {
////                displayAllAvailableProducts();
////
////            } else if (answer.equals("7")) {
////                showProductsByCat();
////
////            } else if (answer.equals("8")) {
////                productAvailability();
////
////            } else if (answer.equals("9")) {
////                acceptOrder(basket);
////            }
////        }
////    }
//
//
////
////    public static String nameProviding(){
////        View.provideProductName();
////        String name = View.getInput();
////        return name;
////    }
//    }
//    public static Float priceProviding() {
//
//        try {
//            View.provideProductPrice();
//            String strPrice = View.getInput();
//            Float price = Float.valueOf(strPrice);
//            return price;
//
//        }catch(NumberFormatException e){
//            View.showWrongPriceMsg();
//            return null;
//        }
//    }
//    public static ProductCategory categoryProviding() {
//
//        ArrayList<ProductCategory> categoryList = ProductCategory.getCategoryList();
//
//        if (!categoryList.isEmpty()) {
//            displayCategories(categoryList);
//
//            View.chooseCategoryName();
//            String catName = View.getInput();
//
//            ProductCategory category = checkIfCategoryExists(categoryList, catName);
//            return category;
//
//        }return new ProductCategory("OTHER");
//    }
//
//
//    public static void displayCategories(ArrayList<ProductCategory> array) {
//
//        for (ProductCategory item : array) {
//            System.out.println(item.getName());
//        }
//    }
//
//    public static ProductCategory checkIfCategoryExists(ArrayList<ProductCategory> categoryList, String catName) {
//
//        for (ProductCategory category : categoryList)
//            if (category.getName().equals(catName)) {
//                View.productAddedMsg();
//                return category;
//            }return null;
//    }
//
//    public static void createNewCategory() {
//
//        View.provideCategoryName();
//        String catName = View.getInput();
//        View.provideCategoryType();
//        String catType = View.getInput();
//
//        if (catType.equals("YES")) {
//            View.provideDate();
//            String catDate = View.getDate();
//
//            FeaturedProductCategory.createCategory(catName, catDate);
//        } else {
//            ProductCategory.createCategory(catName);
//        }
//    }
//
//
//    public static ProductCategory findCategory(){
//        String choice = View.getInput();
//        ArrayList <ProductCategory> categoryList = ProductCategory.getCategoryList();
//        for (ProductCategory category : categoryList) {
//            if(category.getName().equals(choice)){
//                return category;
//            }
//        }
//        return null;
//    }
//
////    public static void showProductsByCat() {
////        ProductCategory category = findCategory();
////        ArrayList productsByCat = Product.getProductsByCategory(category);
////        displayProd(productsByCat);
////    }
//
//
//    public static void addToBasket(Basket basket) {
//        ArrayList<Product> ProductList;
////        ProductList = getAllAvailableProducts();
//
//
//
//        View.provideProductNameToAdd();
//        String name = View.getInput();
//
//        for (Product item : ProductList) {
//            if (item.getName().equals(name)) {
//                basket.addProduct(item);
//            }
//        }
//    }
//
//    public static void seeAllProducts(Basket basket) {
//        basket.iterateThroughList();
//        }
//
//    public static void removeFromBasket(Basket basket) {
//        seeAllProducts(basket);
//        View.provideProductNameToDelete();
//        String choice = View.getInput();
//
//        ArrayList<Product> itemsToDelete = new ArrayList<>();
//
//        for (Product product : basket.getProductList()) {
//            if (choice.equals(product.getName())) {
//                itemsToDelete.add(product);
//            }
//        }
//        if (!itemsToDelete.isEmpty()) {
//            for (Product product : itemsToDelete) {
//                basket.removeProduct(product);
//            }
//        }else{
//            View.showNoAvailableItems();
//        }
//    }
////
////    public static ArrayList getAllAvailableProducts() {
////            return Product.getAllProducts();
////    }
//
////    public static void displayAllAvailableProducts() {
////        ArrayList products = getAllAvailableProducts();
////        displayProd(products);
////    }
//
////
////    public static void productAvailability() {
////        String getProduct = View.getInput();
////        ArrayList<Product> allAvailableProducts = new ArrayList<>();
////        ArrayList<Product> allProducts = getAllAvailableProducts();
////
////        for (Product product : allProducts) {
////            if(product.getName().equals(getProduct)) {
////                allAvailableProducts.add(product);
////            }
////        }
////        displayProd(allAvailableProducts);
////
////    }
//
//    public static void displayProd(ArrayList array) {
//
//        if (!array.isEmpty()) {
//            for (Object item : array) {
//                System.out.println(item);
//            }
//        } else {
//            View.showListIsEmpty();
//        }
//    }
//
//
//    public static Order createOrder(Basket basket) {
//        displayProd(basket.getProductList());
//        View.showTotalPriceInfo(basket);
//
//        Order order = new Order();
//
//        return order;
//    }
//
//    public static void acceptOrder(Basket basket){
//        Order order = createOrder(basket);
//        confirmOrderDetails(order);
//        confirmToPayForOrder(order, basket);
//    }
//
//
//    public static void confirmOrderDetails(Order order) {
//        View.acceptOrderMsg();
//        String answer = View.getInput();
//        if (answer.equals("YES")) {
//            startCheckingOrderProcess(order);
//        }
//    }
//
//    public static void confirmToPayForOrder(Order order, Basket basket){
//        View.confirmPaymentMsg();
//        String answer2 = View.getInput();
//
//        if (answer2.equals("YES")) {
//            startPaymentProcess(order);
//            View.printOrderInfo(order);
//            basket.clearBasket();
//        }else{
//            View.orderCanceledMsg();
//        }
//    }
//
//    public static void startCheckingOrderProcess(Order order) {
//        CheckoutProcess checkProcess = new CheckoutProcess();
//        checkProcess.process(order);
//    }
//
//    public static void startPaymentProcess(Order order){
//        PaymentProcess payProcess = new PaymentProcess();
//        payProcess.process(order);
//    }
//
//    public static void showMenu() {
//        View.printMenu();
//    }
//}
