package com.atm.operations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.atm.dbconnect.CustomerService;
import com.atm.dbconnect.DBConnection;
import com.atm.dbconnect.UserLogin;
import com.atm.utility.UserInputValidation;

public class AtmOperations {

	public static void main(String[] args) throws NumberFormatException, IOException, SQLException {
		
		Connection conn=DBConnection.connect();
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in)); 
		
		
		
		System.out.println("***********************welcome to SKAK Bank******************************");
		
		System.out.println();
		
		boolean status = false;
		try {
			System.out.print("\t\t Enter login id:");
			String loginId=bufferedReader.readLine();
			
			System.out.println();
			
			System.out.print("\t\t Enter Password:");
			String password=bufferedReader.readLine();
			
		    status=UserLogin.login(loginId, password);
		    //System.out.println(status);
			
		} 
		catch (Exception e) 
		{
			
			System.out.println("Enter correct ID/PASSWORD ");
		} 
		
	if(status==true)
	{	
		do
		{
			System.out.println("--------------------------------------------------------------------------");
			System.out.println();
			System.out.println("********************************SKAK BANK*********************************");
			System.out.println();
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("\t\t 1 -> Deposit");  
			System.out.println("\t\t 2 -> Withdraw");
			System.out.println("\t\t 3 -> Balance Check");
			System.out.println("\t\t 4 -> Check customer Info.");
			System.out.println("\t\t 5 -> Exit");
			System.out.println();
			System.out.println("--------------------------------------------------------------------------");
			System.out.println();
			System.out.print("Enter your option :");
			int choice=Integer.parseInt(bufferedReader.readLine());
			
			switch(choice)
			{
			 case 1:System.out.print("Enter the account id:");
			
		 			long accountId=Integer.parseInt(bufferedReader.readLine());
				    System.out.print("Enter the deposit amount:");
				  
			 		double depositAmount=Double.parseDouble(bufferedReader.readLine());
			 		double result=CustomerService.deposit(depositAmount, accountId);
			 		
			 		if(result>0.0)
			 		{
			 			System.out.println();
			 			System.out.println("Your cash is deposited successfully!!");
			 			System.out.println();
			 			System.out.println("Current Balance is :"+result);
			 		}
			 		else
			 		{
			 			System.out.println();
			 			System.out.println("Transaction failed!!");
			 			System.out.println();
			 			System.out.println("Enter valid deposit amount!!");
			 		}
			 		break;
			 case 2:
					 System.out.print("Enter the account id:");
					
		 			 accountId=Integer.parseInt(bufferedReader.readLine());
				     System.out.print("Enter the withdraw amount:");
				    
				     double withdrawalAmount=Double.parseDouble(bufferedReader.readLine());		
			 		 result=CustomerService.withdraw(withdrawalAmount, accountId);
			 		 
			 		 if(result>0.0)
			 		 {
			 			System.out.println();
			 			System.out.println("PLEASE collect your cash!!");
			 			System.out.println();
			 			System.out.println("Current Balance is :"+result);
			 		 }
			 		 else
			 		 {
			 			System.out.println();
			 			System.out.println("Transaction failed!!");
			 			System.out.println();
			 			System.out.println("Insufficient balance!!");
			 		 }
			 		 break;
			 case 3: 
				 System.out.print("Enter the account id:");
				 
				 accountId=Integer.parseInt(bufferedReader.readLine());
				 double availableBalance=CustomerService.balanceCheck(accountId);
				 
				 if(availableBalance>0)
				 {
					 System.out.println();
					 System.out.println("Available Balance is :"+availableBalance);
				 }
				 else
				 {
					 System.out.println();
					 System.out.println("Enter valid account Id!!");
				 }
			 	 break;
			 	 
			 	 
			 case 4: 	 
				 System.out.print("Enter the account id:");
				 accountId=Integer.parseInt(bufferedReader.readLine());
				 ResultSet row=CustomerService.checkAccountInfo(accountId);
				 
				 if(row.next())
				 {
					 System.out.println("-------------------------------------------------------------------------");
					 System.out.println("Customer Information Details:");
					 System.out.println("-------------------------------------------------------------------------");
					 System.out.println();
					 System.out.println("Account Id: " +row.getLong(1));
					 System.out.println("Account Opening date: " +row.getTimestamp(2));
					 System.out.println("Account Type: " +row.getString(3));
					 System.out.println("Bank Name: " +row.getString(5));
					 System.out.println("IFSC code: " +row.getLong(6));
					 System.out.println("Branch name: " +row.getString(7));
					 System.out.println();
					 System.out.println("-----------------------------END--------------------------------------------");

				 }
				 else
				 {
					 System.out.println();
					 System.out.println("Invalid account Id!!");
				 }
				 break;
				 
			 case 5: 
				 
				 System.out.println("Are you sure want to exit?");	
			 	 System.out.println("Y -> Yes / N -> No");
			 	 String response=bufferedReader.readLine();
			 		 
				 		 if(response.equals("y") || response.equals("Y"))
				 		 {
				 			 status=false;
				 		 }
				 	
				 break;
			 default:System.out.println("Enter correctly!"); 		
			
			
			}
		
			
		}
		while(status);
		 System.out.println("**************************************************************************");
		 System.out.println();
		System.out.println("Exit successfully!!");
		System.out.println();
		System.out.println("Thanks for visiting our ATM!!");
		System.out.println();
		 System.out.println("**************************************************************************");

	}
	
	}}


