package com.bankapp.DAO;

import lombok.Builder;

import java.util.Objects;

@Builder
public class Customer {

    private int customerId;
    private String customerName;
    private int contactNumber;
    private int balance;

    public Customer() {
    }

    public Customer(String customerName, int contactNumber, int balance) {
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.balance = balance;
    }

    public Customer(int customerId, String customerName, int contactNumber, int balance) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.balance = balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId && contactNumber == customer.contactNumber && Objects.equals(customerName, customer.customerName) && Objects.equals(balance, customer.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerName, contactNumber, balance);
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", customerName='" + customerName + '\'' + ", contactNumber=" + contactNumber + ", balance='" + balance + '\'' + '}';
    }
}
