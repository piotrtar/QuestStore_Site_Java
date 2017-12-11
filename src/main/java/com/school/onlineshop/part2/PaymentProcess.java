package com.school.onlineshop.part2;

public class PaymentProcess extends AbstractProcess {

        protected void action(Orderable order){
            order.pay();
        }
    }
