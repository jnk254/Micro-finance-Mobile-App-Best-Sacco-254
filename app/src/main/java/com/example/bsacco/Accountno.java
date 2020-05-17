package com.example.bsacco;


import java.util.concurrent.atomic.AtomicInteger;

class AccountnoGenerator {
    //generate account number
    private AtomicInteger count =new AtomicInteger(0001);
    private static AccountnoGenerator generator= new AccountnoGenerator();

    private  AccountnoGenerator(){ }
    public static AccountnoGenerator getInstance(){
        return generator;
    }

    public int generate(){
        return  count.getAndIncrement();
    }
}
