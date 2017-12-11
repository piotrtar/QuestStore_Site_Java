package com.school.onlineshop.part1;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private static final AtomicInteger counter = new AtomicInteger();


    public static Integer generateId(){

        Integer id = counter.getAndIncrement();
        return id;
    }
}
