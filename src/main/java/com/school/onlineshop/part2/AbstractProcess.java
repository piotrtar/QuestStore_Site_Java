package com.school.onlineshop.part2;

public abstract class AbstractProcess {

    public void process(Orderable order){
        stepBefore();
        action(order);
        stepAfter();
    }
    public void stepBefore(){
        System.out.println("Started process...");
    }

    public void stepAfter(){
        System.out.println("Finished process...");
    }

    protected abstract void action(Orderable order);
}
