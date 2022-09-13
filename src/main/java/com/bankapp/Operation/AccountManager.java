package com.bankapp.Operation;

import com.bankapp.DAO.Customer;
import com.bankapp.DBManager.DbConnection;
import com.bankapp.Exceptions.WithdrawMoneyException;

import java.sql.*;

public class AccountManager {
	static Connection connection = DbConnection.getConnection();

	public static void createTable(String name) throws SQLException {
		Statement statement = connection.createStatement();
		boolean isCreated = statement.execute("CREATE TABLE " + name + "(Cid NUMBER(10) NOT NULL , Cname varchar2(45) DEFAULT NULL, CNum NUMBER(10) NOT NULL, " +
				"balance varchar2(45) DEFAULT NULL)");

		statement.executeQuery("ALTER TABLE customer ADD CONSTRAINT cust_pk PRIMARY KEY (Cid)");
		statement.executeQuery("CREATE SEQUENCE cust_seq START WITH 1;");
		statement.executeQuery("CREATE OR REPLACE TRIGGER cust_bir\n" +
				"    BEFORE INSERT ON customer\n" +
				"    FOR EACH ROW\n" +
				"\n" +
				"BEGIN\n" +
				"    SELECT cust_seq.NEXTVAL\n" +
				"    INTO   :new.Cid\n" +
				"    FROM   dual;\n" +
				"END;");

		DbConnection.closeConnection();
	}



	public static void AccountOpen(Customer customer) throws ClassNotFoundException, SQLException {
		try {
			Statement stmt = connection.createStatement();
			PreparedStatement preparedStatement = connection.prepareStatement("Insert INTO Customer(Cid ,Cname,Cnum,Balance) VALUES( null , ?,?,?)");
			preparedStatement.setString(1, customer.getCustomerName());
			preparedStatement.setInt(2, customer.getContactNumber());
			preparedStatement.setInt(3, customer.getBalance());
			int rows_affected = preparedStatement.executeUpdate();
			if(rows_affected>0) {
				System.out.print("Your Account created successfully! your customer id is : " );
			} else {
				System.out.println("There is some error while creating your account");
			}
			int num = customer.getContactNumber();
			ResultSet rset = stmt.executeQuery("SELECT * from Customer" + " where CNum= "+ num +"");
			int custId = 0;
			while (rset.next()) {
				custId = rset.getInt("Cid");
			}
			System.out.println(custId);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void checkBalance(int id) throws ClassNotFoundException, SQLException {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT Cname,CNum ,balance FROM Customer" + " where Cid= "+id+"");
			Customer customer = new Customer();
			customer.setCustomerId(id);
			while (rset.next()) {
				customer.setCustomerName(rset.getString("Cname"));
				customer.setContactNumber(rset.getInt("Cnum"));
			    customer.setBalance(rset.getInt("balance"));
			}
				System.out.println("Customer name : " +customer.getCustomerName()+ " With Customer id : "+ customer.getCustomerId()+ " has a current balance : Rs."+customer.getBalance());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void withdrawal(int id,int amount) throws ClassNotFoundException, SQLException {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT balance FROM Customer" + " where Cid= " + id + "");
			int amt = 0;
			while (rset.next())
				amt = rset.getInt(1);
			try {
				if (amt < amount) {
					throw new WithdrawMoneyException();
				} else {
					amt -= amount;
					@SuppressWarnings("unused")
					ResultSet rs = stmt.executeQuery("UPDATE Customer " + "SET balance=" + amt + " where Cid=" + id + "");
					ResultSet rsett = stmt.executeQuery("SELECT Cname ,balance FROM Customer" + " where Cid= " + id + "");

					Customer customer = new Customer();
					customer.setCustomerId(id);
					while (rsett.next()) {
						customer.setCustomerName(rsett.getString("Cname"));
						customer.setBalance(rsett.getInt("balance"));
					}
					System.out.println("Customer name : " + customer.getCustomerName() + " With Customer id : " + id + " withdrawal amount Rs." + amount + " has a current balance : Rs." + customer.getBalance());
				}
			}
			catch (WithdrawMoneyException e){
				e.getMessage();
		}
			} catch(Exception e) {
				e.printStackTrace();
		}
	}		
	public static void deposit(int id,int amount) throws SQLException, ClassNotFoundException {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT balance FROM Customer" + " where Cid= "+id+"");
			int amt=0;
			while (rset.next())
			    amt=rset.getInt(1);
			amt+=amount;
			@SuppressWarnings("unused")
			ResultSet rs = stmt.executeQuery("UPDATE Customer " + "SET balance="+amt+" where Cid="+id+"");
			ResultSet rsett = stmt.executeQuery("SELECT Cname,balance FROM Customer" + " where Cid="+id+"");
			Customer customer = new Customer();
			customer.setCustomerId(id);
			while(rsett.next()) {
				customer.setCustomerName(rsett.getString("Cname"));
				customer.setBalance(rsett.getInt("balance"));
			}
			System.out.println("Customer name : " +customer.getCustomerName()+ " With Customer id : "+ customer.getCustomerId() + " Deposited amount Rs."+amount+" has a current balance : Rs."+ customer.getBalance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
