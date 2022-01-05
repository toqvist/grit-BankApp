package bank.app;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        //Initialize bank object. Bank object initializes it's own filehandler and customer list.
        Bank bank = new Bank();
        
        boolean running = true;
        while(running) {

            System.out.println("Welcome to bank!");
            System.out.println("[1] - Exit App");
            System.out.println("------CUSTOMER ACTIONS-------");
            System.out.println("[2] - Create new Customer"); //Will first check if customer exists, then create.
            System.out.println("[3] - Delete Customer, close all their accounts");
            System.out.println("------ACCOUNT ACTIONS-------");
            System.out.println("[4] - Create new Account, add to customer"); //Will first check if account exists, then if customer already has account.
            System.out.println("[5] - Close Account, print balance");
            System.out.println("[6] - Remove account from user");
            System.out.println("[7] - Deposit into account");
            System.out.println("[8] - Withdraw from account");
            System.out.println("------DATABASE ACTIONS-------");
            System.out.println("[9] - Write customer list to database"); //Working as intended
            System.out.println("[10] - Get customer list from database"); //Working as intended.
            System.out.println("[11] - Print all customers from database");  //Working as intended
            System.out.println("[12] - Create mock customers with accounts, put them into customer list"); //Working as intended.
            System.out.println("------DIAGNOSTIC ACTIONS-------");
            System.out.println("[13] - Verify filehandler can find files."); //Working as intended.
            System.out.println("[14] - Print customer list."); //Working as intended.
            System.out.println("Enter input: ");
            
            Scanner scanner = new Scanner(System.in);
            int userInput=0;
            
            try {
                
                userInput = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Input must be a valid integer from printed choices!");
            } 

            //Declare variables for the switch-case.
            String customerID;
            
            switch(userInput) {
                
                case 0: //Do nothing
                    break;
                case 1: //Exit
                    running = false;
                    break;
                case 2: //Create new customer
                    System.out.println("Enter customer personal ID:");
                    customerID = scanner.next(); 

                    System.out.println("Enter customer forename :");
                    String customerName = scanner.next() + ",";

                    System.out.println("Enter customer surname :");
                    customerName += scanner.next();

                    bank.createCustomer(customerName, customerID);

                    break;
                case 3://Delete Customer, close all their accounts
                    System.out.println("Enter customer personal ID:");
                    customerID = scanner.next();
                    bank.deleteCustomerAndTheirAccounts(customerID);
                    
                    break;
                case 4: //Create new Account, add to customer
                    System.out.println("Enter customer personal ID: ");    
                    customerID = scanner.next();
                    bank.createAccount(customerID);
                    break;
                case 5: //Close Account, print balance
                    System.out.println("Enter account holder personal ID: ");
                    customerID = scanner.next();

                    System.out.println("Enter account number: ");
                    int accountNumber = scanner.nextInt();

                    //Delete both references to account, closeAccount prints balance.
                    bank.getCustomer(customerID).closeAccount(accountNumber);
                    bank.deleteAccount(accountNumber);
                    break;
                case 6: //Remove account from user
                    break;
                case 7: //Deposit into account
                    break;
                case 8: //Withdraw from account
                    break;
                case 9: //Write customer list to database
                    bank.writeCustomersToDB();
                    System.out.println("Current list of users written to database");
                    break;
                case 10: //Get customer list from database
                    bank.addCustomersFromDB();
                    System.out.println("Current list of users appended with non-duplicate customers from database");
                    break;
                case 11: //Print customers from database
                    bank.printCustomerData();
                    break;
                case 12: //Create mock customers with accounts, populate database with customers.
                    bank.addCustomersFromMockData();
                    break;
                case 13: //Verify filehandler can find files.
                    bank.fh.verifyFiles();
                    break;
                case 14: //Print customer list
                    bank.printCustomerList();
                    break;
            }

            //Wait for user input, so that the console does not become crowded with menu choices.
            System.out.println("Press any key to continue");
            System.in.read();
        }
        
    }

}