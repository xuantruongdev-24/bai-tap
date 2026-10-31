/* Lớp cho ds khách hàng */
package core;

import java.util.ArrayList;
import tool.ConsoleInputter; // lop giup nhap data
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException; // Cac lop mo ta loi khi truy xuat file
import java.io.IOException;

public class CustList extends ArrayList<Customer> {

    static String CODE_PAT = "^[cCgGkK][\\d]{4}$";
    static String PHONE_PAT = "^0[235789][\\d]{8}$";
    static String EMAIL_PAT = "^[\\w -_ \\.+]*[\\w  -_ \\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    static String NAME_PAT = "^[\\w][\\w ]{1,24}$"; // TEN CO IT NHAT 2 KY TU

// cac lop giup thao tac object file
    public void addCustomer() {
        String code, name, phone, email;
        int pos;
        do {
            code = ConsoleInputter.getStr("Cust.code (CGK 4 digits)", CODE_PAT,
                    "Format:[CGK]0000").toUpperCase();
            pos = this.indexOf(new Customer(code));
            if (pos >= 0) {
                System.out.println("the code is duplicated!");
            }
        } while (pos >= 0);
        name = ConsoleInputter.getStr("Cust.name", NAME_PAT,
                "Name contains at least 2 characters");
        phone = ConsoleInputter.getStr("Phone no.", PHONE_PAT,
                "VN phone: 10 digits");
        email = ConsoleInputter.getStr("Email", EMAIL_PAT, "Email: any@any.any");
        Customer newCust = new Customer(code, name, phone, email);
        this.add(newCust);
        System.out.println("New customer was added.");
    }    
    public void print() {
        if (this.isEmpty()) {
            System.out.println("Empty List.");
        } else {
            String header
                    = "---------------------------------------------------------------\n"
                    + "Code    | Customer name       | Phone      | Email      \n"
                    + "---------------------------------------------------------------";
            String footer
                    = "---------------------------------------------------------------";
            System.out.println(header);
            for (Customer cust:this) System.out.println(cust);
            System.out.println(footer);
        }
    }
    public void searchName(){
        if(this.isEmpty()) System.out.println("Emptylist ");
        else{
            String searchName = ConsoleInputter.getStr("Searched name").trim();
            searchName = searchName.toUpperCase();
            CustList resultList = new CustList();
            for(Customer cust:this)
                if (cust.getName().toUpperCase().contains(searchName))
                    resultList.add(cust); 
            resultList.print();
        } 
    }
    public void updapteCust (){
         if(this.isEmpty())System.out.println("Empty list");
         else{
             String custCode,newName,phone,email;
             custCode = ConsoleInputter.getStr("Searched code").trim().toUpperCase();
             int pos = this.indexOf(new Customer(custCode));
             if(pos < 0)System.out.println("This code does not exist");
             else{
                 Customer cust = this.get(pos);
                 newName = ConsoleInputter.getStr("New name ",NAME_PAT,
                                                  "Name has at least 2 chars.");
                 cust.setName(newName);
                 phone = ConsoleInputter.getStr("Phone no.",PHONE_PAT,
                                                   "VN phone: 10 digits");
                 cust.setPhone(phone);
                 email = ConsoleInputter.getStr("Email",EMAIL_PAT, "Email:any@any.any");
                 cust.setEmail(email);
                 System.out.println("Updated.");
                 
             }
         }
     }
    public void readFile (String fName){
        File f = new File(fName);
        if(!f.exists()){
            System.out.println("The file"+ fName + "does not exist. Ignored.");
        }
        else{
            try {
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Customer cust;
                while(true){
                    cust = (Customer)ois.readObject();
                    this.add(cust);
                }
            }
            catch (EOFException e) {
                System.out.println("All data in the file were read.");
            }
            catch(IOException e){
                System.err.println(e.getMessage());
            }
            catch(ClassNotFoundException e){
                System.err.println(e.getMessage());
            }
        }
    }
    public void writeFile(String fName){
        if(this.isEmpty())System.out.println("Empty list");
        else{
            try {
                FileOutputStream fo = new FileOutputStream(fName);
                ObjectOutputStream os = new ObjectOutputStream(fo);
                for (Customer cust:this) os.writeObject(cust);
                os.close();
                fo.close();
                System.out.println("Data are saved to file.");
            } 
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

