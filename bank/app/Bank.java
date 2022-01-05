package bank.app;

import java.io.IOException;
import java.util.ArrayList;

public class Bank {
    
    ArrayList<Customer> customerList;
    FileHandler fh = new FileHandler();

    /*
    App uses two ArrayLists two keep track of accounts, one assigned to bank and one assigned to customer. 
    They should reference the same account objects.
    */
    ArrayList<Account> accountList;
    
    public Bank() {
        this.customerList = new ArrayList<>();
        this.accountList = new ArrayList<>();
        this.fh = new FileHandler();
    }


    //Check list of all bank accounts and return answer to if a duplicate account exists.
    public boolean accountExists(int accountNumber) { 
        for(int i=0;i<accountList.size();i++) {
            if (accountNumber == accountList.get(i).getAccountNumber()){
                return true;
            }
        }
        return false;
    }
    
    public boolean customerExists(long personalID) { 
        for(int i=0;i<customerList.size();i++) {
            if (personalID == customerList.get(i).getPersonalID()) {
                return true;
            }
        }
        return false;
    }

    public void createCustomer(String name, long socialSecNumber) {
        this.customerList.add(new Customer(name,socialSecNumber));
        System.out.println("Customer " + name +" added");
    }


    //Loops through customer list to find list index of user, then uses index to remove list entry of user. 
    //Customer object remains but is unreachable.
    public void deleteCustomer(long personalID) {
        for (int i=0;i<customerList.size();i++ ) {
            if(customerList.get(i).getPersonalID()== personalID) {
                customerList.remove(i);
                break;
            }
        }
    }

    public void deleteCustomerAndTheirAccounts (long personalID) {
        for (int i=0;i<customerList.size();i++ ) {
            if(customerList.get(i).getPersonalID()== personalID) {
                customerList.get(i).removeAllAccounts();
                customerList.remove(i);
                break;
            }
        }
    }

    public void createAccount() {
        accountList.add(new Account(generateAccountNumber()));
    }

    public void deleteAccount(int accountNumber) {
        for (int i=0;i<accountList.size();i++ ) {
            if(accountList.get(i).getAccountNumber() == accountNumber) {
                accountList.remove(i);
                break;
            }
        }
    }

    public int generateAccountNumber () {
        int returnNumber;
        returnNumber = accountList.get(accountList.size()-1).getAccountNumber()+1;
        //Repeat until account capacity (which i assume is 9999 here)
        while(returnNumber < 10000) {
            if(!accountExists(returnNumber)) {
                return returnNumber;
            } else {
                returnNumber++;
            }
            
        }
        System.out.println("Account capacity reached.");
        return 0;
    }


    //Rather than using the text file as an active db, I instead write the entire customer list at set times. This is likely not an optimal solution.
    public void writeCustomersToDB () throws IOException {
        fh.writeCustomersToDB(customerList);
    }
    //Appends customers from database to customerList.  
    public void addCustomersFromDB () throws IOException{
        
        ArrayList<String> dbData = fh.getCustomerData();
        
        for(int i=0;i<dbData.size();i++) {

            //Following method returns something like: 0102031122%Adam,Adamsson%
            //Unpack string
            String[] dataToWrite = dbData.get(i).split("%");
            
            long personalID = Long.parseLong(dataToWrite[0]);
            
            String nameArray[] = dataToWrite[1].split(",");
            
            String forename = nameArray[0];
            String surname = nameArray[1];
            String name = forename + " " + surname;

            
            //createCustomer writes the data into a list entry in the bank customerList.
            createCustomer(name, personalID);
            
        }
    }
    //This is the same method as addCustomersFromDB, but writes data from mockData instead.
    //Should be rewritten to avoid repetition.
    public void addCustomersFromMockData () throws IOException{
        
        ArrayList<String> mockData = fh.getMockCustomers();
        
        for(int i=0;i<mockData.size();i++) {

            //Following method returns something like: 0102031122%Adam,Adamsson%
            //Unpack string
            String[] dataToWrite = mockData.get(i).split("%");
            
            long personalID = Long.parseLong(dataToWrite[0]);
            
            String nameArray[] = dataToWrite[1].split(",");
            
            String forename = nameArray[0];
            String surname = nameArray[1];
            String name = forename + " " + surname;
            
            
            //createCustomer writes the data into a list entry in the bank customerList.
            createCustomer(name, personalID);
            
        }
    }
    public void printCustomerData () throws IOException{
        
        ArrayList<String> dbData = fh.getCustomerData();
        
        for(int i=0;i<dbData.size();i++) {

            //Following method returns something like: 0102031122%Adam,Adamsson%
            //Unpack string
            String[] dataToWrite = dbData.get(i).split("%");
            
            long personalID = Long.parseLong(dataToWrite[0]);
            
            String nameArray[] = dataToWrite[1].split(",");
            
            String forename = nameArray[0];
            String surname = nameArray[1];
            String name = forename + " " + surname;
            
            System.out.println("Customer " + (i+1) + ":" + name);
        }
    }

    public void printCustomerList() {
        System.out.println(customerList.size());
        for (int i=0;i<customerList.size();i++) {
            System.out.println(customerList.get(i).getName());
        }
    }
    
}




