package com.bankapp.Menu;

import com.bankapp.DAO.Customer;
import com.bankapp.Operation.AccountManager;
import lombok.SneakyThrows;

import java.util.Scanner;

public class CustomerMenu {

    @SneakyThrows
    public static void displayMenu(){
        Scanner scn=new Scanner(System.in);
        while(true) {
            System.out.println("--------------Welcome Menu------------------");
            System.out.println("Please Select from the below options");
            System.out.println("1. Account Opening");
            System.out.println("2. Check Account Balance");
            System.out.println("3. Withdrawal Money");
            System.out.println("4. Deposit Money");
            System.out.println("5. Exit");
            System.out.println("Please Enter your option : ");
            int option=scn.nextInt();
            switch(option) {
                case 1:
                    System.out.println("Please Enter you name :");
                    String name=scn.next();
                    System.out.println("Enter your contact number");
                    int contactNumber = scn.nextInt();
                    scn.nextLine();
                    System.out.println("Enter the initial amount you want to deposit");
                    int balance=scn.nextInt();
                    Customer customer = Customer.
                            builder().
                            customerName(name).
                            contactNumber(contactNumber).
                            balance(balance).
                            build();
                    AccountManager.AccountOpen(customer);
                    break;
                case 2:
                    System.out.println("Enter you cust ID");
                    int id1=scn.nextInt();
                    AccountManager.checkBalance(id1);
                    break;
                case 3:
                    System.out.println("Enter you cust ID");
                    int id2=scn.nextInt();
                    System.out.println("Enter amount you want to withdrawal");
                    int amount=scn.nextInt();
                    AccountManager.withdrawal(id2,amount);
                    break;
                case 4:
                    System.out.println("Enter you cust ID");
                    int id3=scn.nextInt();
                    System.out.println("Enter amount you want to deposit");
                    int amount1=scn.nextInt();
                    AccountManager.deposit(id3,amount1);
                    break;
                case 5:
                    System.out.println("Exit successfully");
                    return;
                default:
                    System.out.println("There's something wrong! Please enter the details again");
                    break;
            }
        }
    }
}
