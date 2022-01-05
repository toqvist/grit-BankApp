package bank.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public File mockData;
    public File customers_db;

    public FileHandler () {
        this.mockData = new File("./_files/mock_customers.txt");
        this.customers_db = new File("./_files/customers.txt");
    }

    public void verifyFiles() {
        System.out.println("Mock data exists: " + mockData.exists());
        System.out.println("Customer datababase exists: " + customers_db.exists());
    }

    //Returns an arraylist where each item is a line from db
    public ArrayList<String> getCustomerData () throws IOException {
        
        BufferedReader br = new BufferedReader(new FileReader(customers_db));
        ArrayList<String> customerList = new ArrayList<String>();
        
        int i=0;
        for (String line; (line = br.readLine()) != null;i++) {
            customerList.add(line);
            
        }
        br.close();
        return customerList;
        
    }
    //This is the same method as getCustomerlist, but returns data from mockData instead.
    //Should be rewritten to avoid repetition.
    public ArrayList<String> getMockCustomers () throws IOException {
        
        BufferedReader br = new BufferedReader(new FileReader(mockData));
        ArrayList<String> customerList = new ArrayList<String>();
        
        int i=0;
        for (String line; (line = br.readLine()) != null;i++) {
            customerList.add(line);
        
            
        }
        br.close();
        return customerList;
    }

    public void writeCustomersToDB (ArrayList<Customer> customerList) throws IOException {
        FileWriter filewriter = new FileWriter(customers_db);
        
        for (int i =0; i< customerList.size(); i++) {
            String[] name = customerList.get(i).getName().split(" ");
            String forename = name[0];
            String surname = name[1];

            filewriter.write(Long.toString(customerList.get(i).getPersonalID()) +
            "%" +
            forename +
            "," +
            surname +
            "%");
            filewriter.write(String.format("%n"));
        }
        filewriter.close();
    }

    
    

    
}