package bank.app;

import java.util.ArrayList;

public class Bank {
    
    ArrayList<Customer> customerList;
    
    /*
    App uses two lists two keep track of accounts, one assigned to bank and one assigned to customer. 
    But they should reference the same account objects.
    */
    ArrayList<Customer> accountList;
    
    public Bank() {
        this.customerList = new ArrayList<>();
    }

    public void createMockData() {
        
        addCustomer("Anders Andersson", 11111111);
        addAccount();
        new Account(1234567L, "Debit", 1000000);
    }

    //Check list of all bank accounts and return answer to if a duplicate account exists.
    public boolean checkDuplicate(long accountNumber) { 
        for(int i=0;i<customerList.size();i++) {
            if (customerList.get(i).checkDuplicate(accountNumber)) {
                return true;
            }
        }
        return false;
    }

    public void addCustomer(String name, long socialSecNumber) {
        this.customerList.add(new Customer(name,socialSecNumber));
    }

    //Loops through customer list to find list index of user, then uses index to remove list entry of user.
    public void removeCustomer(String name, long socialSecNumber) {
        for (int i=0;i<customerList.size();i++ ) {
            if(customerList.get(i).name.equals(name) &&
            customerList.get(i).socialSecNumber == socialSecNumber) {
                customerList.remove(i);//Is this correct?
            }
        }
    }

    public void addAccount() {

    }
}




