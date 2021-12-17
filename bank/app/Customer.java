package bank.app;

import java.util.ArrayList;

public class Customer  {
    String name;
    long socialSecNumber;
    ArrayList<Account> userAccountList; //List of account numbers belonging to the customer.
    
    public Customer (String name, long socialSecNumber) {
        this.name = name;
        this.socialSecNumber = socialSecNumber;
        this.userAccountList = new ArrayList<>();
    }

    public void addAccount (long accountNumber) {
        
        //Is the account already added to the customer?
        if (!this.checkDuplicate(accountNumber)) {
            //Does the account already exist for another customer?
            if(!Bank.checkDuplicate(accountNumber)) {
                userAccountList.add();
            } else {
                System.out.println("Account already assinged to customer: " + name);
            }
        } else {
            System.out.println("Account with the same account number already exists.");
        }
    }

    public void showAccounts () {
        for (int i=0;i<userAccountList.size();i++) {
            userAccountList.get(i).showInfo();
            
        }
    }

    //Check list of accounts and return answer to if a duplicate account exists.
    boolean checkDuplicate(long accountNumber) { 
        for(int i=0;i<userAccountList.size();i++) {
            if (accountNumber == userAccountList.get(i).accountNumber) {
                return true;
            }
        }
        return false;
       }
}
