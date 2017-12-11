package com.school.onlineshop.part1;
import java.util.ArrayList;


public class ProductCategory {

    protected String name;
    protected static Integer id;
    protected static ArrayList<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();

    public ProductCategory(String name){
        this.name = name;
        this.id = IdGenerator.generateId();
        productCategoryList.add(this);

    }


    public static ArrayList getCategoryList(){

        return productCategoryList;
    }

    public String getName(){
        return this.name;
    }

    public static ProductCategory createCategory(String name){
        return new ProductCategory(name);
    }

}
