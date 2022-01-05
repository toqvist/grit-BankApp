package bank.app;

import java.util.ArrayList;

public class Customer  {
    private String name;
    private long personalID;
    private ArrayList<Account> userAccountList; //List of account numbers belonging to the customer.
    
    public Customer (String name, long personalID) {
        this.name = name;
        this.personalID = personalID;
        this.userAccountList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPersonalID() {
        return personalID;
    }

    public void setPersonalID(long personalID) {
        this.personalID = personalID;
    }

    public ArrayList<Account> getUserAccountList() {
        return userAccountList;
    }

    //Avoid using setUserAccountList, modify list using addAccount and removeAccount
    public void setUserAccountList(ArrayList<Account> userAccountList) {
        this.userAccountList = userAccountList;
    }

    public void showAccounts () {
        for (int i=0;i<userAccountList.size();i++) {
            userAccountList.get(i).showInfo();
            
        }
    }

    //Check list of accounts and return answer to if a duplicate account exists.
    boolean checkDuplicate(Account account) { 
        for(int i=0;i<userAccountList.size();i++) {
            if (account == userAccountList.get(i)) {
                return true;
            }
        }
        return false;
       }

    public void closeAccount (int accountNumber) {
        for (int i=0;i<userAccountList.size();i++) {
            if (accountNumber == userAccountList.get(i).getAccountNumber()) {
                System.out.println("Closing account \"" + accountNumber + "\" ");
                System.out.println("Balance: " + userAccountList.get(i).getBalance());
                userAccountList.remove(i);
                break;
            }
        }       
    }
    //Adds object reference to customer's accountList
    //Should be called by method that first checks if there are duplicate accounts in bank and customer.
    public void addAccount (Account account) {
        userAccountList.add(account);
    }
    
    public void removeAllAccounts () {
        userAccountList.clear();
    }
}

