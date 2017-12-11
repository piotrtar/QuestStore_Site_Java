package com.school.onlineshop.part2;

import com.school.onlineshop.part1.IdGenerator;

public class Order implements Orderable{

    private int id = IdGenerator.generateId();
    private String status = "new";


    public String getStatus(){
        return this.status;
    }

    public boolean checkout(){
        this.status = "checked";
        return true;
    }

    public boolean pay(){
        if(this.status.equals("checked")) {
            this.status = "paid";
            return true;
        }
        return false;
    }

    public int getId(){
        return this.id;

    }

}
