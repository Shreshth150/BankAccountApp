package com.bankapp.Exceptions;

public class WithdrawMoneyException extends Exception{

    public WithdrawMoneyException() {
        super();
        System.out.println("Opps there isn't enough money in your account, Please try again with a sufficient balance");
    }
}
