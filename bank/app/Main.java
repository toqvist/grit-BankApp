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
            System.out.println("[4] - Print customer info, their accounts, and their balances.");
            System.out.println("[5] - Change customer name.");
            System.out.println("------ACCOUNT ACTIONS-------");
            System.out.println("[6] - Create new Account, add to customer"); //Will first check if account exists, then if customer already has account.
            System.out.println("[7] - Close Account, print balance");
            System.out.println("[8] - Deposit into account");
            System.out.println("[9] - Withdraw from account");
            System.out.println("------DATABASE ACTIONS-------");
            System.out.println("[10] - Write customer list to database"); //Working as intended
            System.out.println("[11] - Get customer list from database"); //Working as intended.
            System.out.println("[12] - Print all customers from database");  //Working as intended
            System.out.println("[13] - Create mock customers with accounts, put them into customer list"); //Working as intended.
            System.out.println("------DIAGNOSTIC ACTIONS-------");
            System.out.println("[14] - Verify filehandler can find files."); //Working as intended.
            System.out.println("[15] - Print customer list."); //Working as intended.
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
            int accountNumber;
            String customerName;

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
                    customerName = scanner.next() + " ";

                    System.out.println("Enter customer surname :");
                    customerName += scanner.next();

                    bank.createCustomer(customerName, customerID);

                    break;
                case 3://Delete Customer, close all their accounts
                    System.out.println("Enter customer personal ID:");
                    customerID = scanner.next();
                    bank.deleteCustomerAndTheirAccounts(customerID);
                    break;
                case 4: //Print customer info, their accounts, and their balances.
                    System.out.println("Enter customer personal ID:");
                    customerID = scanner.next();
                    bank.printCustomerInfo(customerID);
                    break;
                case 5: //Change customer name
                    System.out.println("Enter customer personal ID:");
                    customerID = scanner.next(); 
                    if (bank.customerExists(customerID)) {
                        System.out.println("Enter new customer forename :");
                        customerName = scanner.next() + ",";

                        System.out.println("Enter new customer surname :");
                        customerName += scanner.next();
                        Customer customer = bank.getCustomer(customerID);
                        customer.setName(customerName);
                        System.out.println("New name set: " + customerName);
                    } else {
                        System.out.println("Customer does not exist");
                    }
                    break;
                case 6: //Create new Account, add to customer
                    System.out.println("Enter customer personal ID: ");    
                    customerID = scanner.next();
                    bank.createAccount(customerID);
                    break;
                case 7: //Close Account, print balance
                    System.out.println("Enter account holder personal ID: ");
                    customerID = scanner.next();

                    System.out.println("Enter account number: ");
                    accountNumber = scanner.nextInt();

                    //Delete both references to account, closeAccount prints balance.
                    bank.getCustomer(customerID).closeAccount(accountNumber);
                    bank.deleteAccount(accountNumber);
                    break;
                case 8: //Deposit into account
                    System.out.println("Enter account number: ");
                    accountNumber = scanner.nextInt();
                    System.out.println("Enter amount to deposit: ");
                    double deposit = scanner.nextDouble();
                    bank.getAccount(accountNumber).deposit(deposit);
                    break;
                case 9: //Withdraw from account
                    System.out.println("Enter account number: ");
                    accountNumber = scanner.nextInt();
                    System.out.println("Enter amount to withdraw: ");
                    double withdrawal = scanner.nextDouble();
                    bank.getAccount(accountNumber).withdraw(withdrawal);
                    break;
                case 10: //Write customer list to database
                    bank.writeCustomersToDB();
                    System.out.println("Current list of users written to database");
                    break;
                case 11: //Get customer list from database
                    bank.addCustomersFromDB();
                    System.out.println("Current list of users appended with non-duplicate customers from database");
                    break;
                case 12: //Print customers from database
                    bank.printCustomerData();
                    break;
                case 13: //Create mock customers with accounts, populate database with customers.
                    bank.addCustomersFromMockData();
                    break;
                case 14: //Verify filehandler can find files.
                    bank.fh.verifyFiles();
                    break;
                case 15: //Print customer list
                    bank.printCustomerList();
                    break;
            }

            //Wait for user input, so that the console does not become crowded with menu choices.
            System.out.println("Press any key to continue");
            System.in.read();
        }
        
    }

}