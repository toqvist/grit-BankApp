package bank.app;


//Accounts should be created and managed in Bank with createAccount and deleteaccount.
//Add the object references to the account lists using addAccount and removeAccount.
public class Account {
    private double balance;
    private int accountNumber; 
    private String accountType; 
    
    //Constructor
    public Account (int accountNumber) {
        //Hardcode account type to debit. If more types are needed, parameterize accountType.
        this.accountType = "debit";
        this.balance = 0;
        this.accountNumber = accountNumber;
    }
   
    public int getAccountNumber() {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }
    public String accountType() {
        return accountType;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    //Avoid usage of setBalance, use deposit and withdraw instead
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getAccountType() {
        return accountType;
    }

    void deposit(double deposit) {
        if (deposit > 0) { //Deposits of negative value should not be possible. 
            this.balance += deposit;
            System.out.println("Deposited " + deposit);
            System.out.println("New balance: " + this.balance);
        } else {
            System.out.println("Deposit can not have a negative value!");
        }
    }

    void withdraw(double withdrawal) {
         if (withdrawal > 0) { //Withdrawals of negative value should not be possible. 
            if(this.balance - withdrawal >= 0) {
                this.balance -= withdrawal;
                System.out.println("Withdrew " + withdrawal);
                System.out.println("New balance: " + this.balance);
            } else {
                System.out.println("Withdrawal cannot exceed account balance.");
            }
        } else {
            System.out.println("Withdrawal can not have a negative value!");
        }
    }

    void showInfo () { 
        System.out.println(this.accountType + " account: " + this.accountNumber + "--Balance: " + this.balance);
    }
   

}
