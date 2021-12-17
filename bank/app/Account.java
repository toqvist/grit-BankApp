package bank.app;

public class Account {
    double balance;
    long accountNumber; 
    String accountType; 
    
    //Constructor
    public Account (long accountNumber, String accountType, double balance) {
        if (accountType.equals("Debit")) {//Probably not an optimal way of making sure it's a debit. 
            this.balance = balance;
            this.accountNumber = accountNumber;
            this.accountType = accountType;
        } else {
            System.out.println("ERROR: Invalid account parameters for account " + accountNumber);
        }
    }
   
    void deposit(double deposit) {
        if (deposit > 0) { //Deposits of negative value should not be possible. 
            this.balance += deposit;
        } else {
            System.out.println("ERROR: Deposit can not have a negative value.");
        }
    }

    void withdraw(double withdrawal) {
         if (withdrawal > 0) { //Withdrawals of negative value should not be possible. 
            this.balance -= withdrawal;
        } else {
            System.out.println("ERROR: Withdrawal can not have a negative value.");
        }
    }

    void showInfo () { 
        System.out.println(this.accountType + " " + this.accountNumber + " has balance: " + this.balance);
    }
   

}
