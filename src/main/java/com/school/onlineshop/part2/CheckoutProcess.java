package com.school.onlineshop.part2;

public class CheckoutProcess extends AbstractProcess {

    protected void action(Orderable order){
        order.checkout();
    }
}
