package com.school.onlineshop.part1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class FeaturedProductCategory extends ProductCategory{

    LocalDate date;

    public FeaturedProductCategory(String name, LocalDate date){

        super(name);
        this.date = date;
    }

    public static FeaturedProductCategory createCategory(String name, String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
            LocalDate date1 = LocalDate.parse(dateStr, formatter);
            return new FeaturedProductCategory(name, date1);
        } catch (Exception e) {
            System.out.println("Please provide appropriate date!");
        }
        return null;
    }
}
