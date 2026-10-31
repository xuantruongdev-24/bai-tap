package core;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import tool.ConsoleInputter;

public class OrderList extends ArrayList<Order> {

    public static final String DATE_PAT = "dd/MM/yyyy";
    public static final String ORDER_CODE_PAT = "^[cCgGkK][\\d]{4}$";
    private int newOrderCode = 1;
    SetMenuList setMnuList;
    CustList cusList;

    public OrderList(SetMenuList setMnuList, CustList cusList) {
        this.setMnuList = setMnuList;
        this.cusList = cusList;
    }

    public void readFile(String fName) {
        File f = new File(fName);
        if (!f.exists()) {
            System.out.println("The file" + fName + "does not exits!");
        } else {
            try {
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Order anOrder;
                while ((anOrder = (Order) ois.readObject()) != null) {
                    this.add(anOrder);
                    if (newOrderCode <= anOrder.getOrderCode()) {
                        newOrderCode = anOrder.getOrderCode() + 1;
                    }
                }
            } catch (EOFException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void writeFile(String fName) {
        if (this.isEmpty()) {
            System.out.println("Empty list");
        } else {
            try {
                FileOutputStream fo = new FileOutputStream(fName);
                ObjectOutputStream os = new ObjectOutputStream(fo);
                for (Order anOrder : this) {
                    os.writeObject(anOrder);
                }
                os.close();
                fo.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    private void printOrder(int orderCode, Customer cust, SetMenu setMnu,
            int numTable, Date preferedDate) {
        int total = numTable * setMnu.getPrice();
        String frame = "------------------------------------------------\n"
                + "Customer order Information [Order ID: " + orderCode + "]\n"
                + "------------------------------------------------";
        System.out.println(frame);
        String custInfo;
        custInfo ="code          : " + cust.getCode() + "\n"
                + "Customer name : " + cust.getName() + "\n"
                + "Phone number  : " + cust.getPhone() + "\n"
                + "Email         : " + cust.getEmail() + "\n"
                + "------------------------------------------------------";
        System.out.println(custInfo);
        DecimalFormat dfCustom;
        dfCustom = new DecimalFormat("#,##0");
        String menuInfo
                = "Code of set menu :" + setMnu.getCode() + "\n"
                + "Set menu name    :" + setMnu.getName() + "\n"
                + "Event date       :" + ConsoleInputter.dateStr(preferedDate, DATE_PAT) + "\n"
                + "Number of tables :" + numTable + "\n"
                + "Price            :" + dfCustom.format(setMnu.getPrice()) + "Vnd";
        System.out.println(menuInfo);
        String ingredientsInfo = "Ingredients:\n";
        String[] items = setMnu.getIngredients().split("#");
        ingredientsInfo += (items[0] + "\n" + items[1] + "\n" + items[2]);
        System.out.println(ingredientsInfo);
        System.out.println("--------------------------------------------------");
        System.out.println("Total cost       " + dfCustom.format(total) + "vnd");
        System.out.println("--------------------------------------------------");
        
    }
    public void addOrder(){
        // khai báo các biến để chứa dữ liệu nhập
        String custCode; // ma KH dat tiec
        String setMenuCode;
        int numTable; // so ban duoc dat
        Date preferedDate; // ngay dat tiec
        int pos; // vi tri tim kiem trong list
        Customer cust = null; // khach hang dat tiec
        SetMenu setMnu; // set menu duoc dat
        do{
            custCode = ConsoleInputter.getStr("Cust. code", ORDER_CODE_PAT, 
                                            "Code: [(C/G/K)+4 digits].").toUpperCase();
            pos = cusList.indexOf(new Customer(custCode));
            if(pos < 0) System.out.println("The customer does not exist!");
            else cust = cusList.get(pos);
        }
        while (pos < 0);
        
        setMnu = (SetMenu) ConsoleInputter.objMenu(this.setMnuList);
        setMenuCode = setMnu.getCode();
        numTable = ConsoleInputter.getInt("Number of table:", 1, 100);
        boolean before = true;
        do {
            preferedDate = ConsoleInputter.getDate("Prefered date-d/m/y", DATE_PAT);
            before = preferedDate.before(new Date());
            if (before == true) System.out.println("Prefered date must be after today");
        } while (before == true);
        // Xuat thong tin order truoc khi luu
        printOrder(newOrderCode, cust, setMnu, numTable, preferedDate);
        // Hỏi user có muốn lưu hay không
        boolean response = ConsoleInputter.getBoolean("Save order? Y/N");
        if (response == true) {
            Order newOrder = new Order(newOrderCode, custCode, setMenuCode, 
                                     numTable, preferedDate);
            this.add(newOrder);
            newOrderCode++;
            System.out.println("New order was added.");
        }
    }
    public void updateOrder() {
        int orderCode;
        String custCode; // ma KH dat tiec
        String setMenuCode;
        int numTable; // so ban duoc dat
        Date preferedDate; // ngay dat tiec
        int pos; // vi tri tim kiem trong list
        Customer cust; // khach hang dat tiec
        SetMenu setMnu; // set menu duoc dat
        Order order; // order
        
        orderCode = Integer.parseInt(ConsoleInputter.getStr("Updated order code"));
        pos = this.indexOf(new Order(orderCode));
        if (pos < 0) System.out.println("This order does not exist!");
        else{
            order = this.get(pos); // order cần update
            custCode = order.getCustCode();
            pos = cusList.indexOf(new Customer(custCode));
            cust = cusList.get(pos);
            
            // Chọn lại setMemu, nhap lại numTable, preferedDate
            setMnu = (SetMenu) ConsoleInputter.objMenu(this.setMnuList);
            setMenuCode = setMnu.getCode();
            numTable = ConsoleInputter.getInt("Number of table:", 1, 100);
            
            boolean before = true;
            do {
                preferedDate = ConsoleInputter.getDate("Prefered date", DATE_PAT);
                before = preferedDate.before(new Date());
                if (before == true) System.out.println("prefered date must be after today");
            } while (before == true);
            
            // Xuat thong tin order truoc khi luu
            printOrder(orderCode, cust, setMnu, numTable, preferedDate);
            
            // Hỏi user có muốn lưu hay không
            boolean response = ConsoleInputter.getBoolean("Save order? Y/N");
            if (response == true) {
                order.setSetMenuCode(setMenuCode);
                order.setNumTable(numTable);
                order.setPreferedDate(preferedDate);
                System.out.println("The order " + orderCode + " was updated.");
            }
        }
    }
    public void print() {
        // Print order list
        DecimalFormat dfCustom;
        dfCustom = new DecimalFormat("#,##0");
        if (this.isEmpty()) System.out.println("The order list is emppty!");
        else{
            // xuat khung
            String header;
            header = "-----------------------------------------------------------------------------------------\n" +
                     "ID      | Event Date  | Customer ID | Set Menu  |      Price | Tables |       Cost\n" +
                     "-----------------------------------------------------------------------------------------";
            System.out.println(header);
            String footer;
            footer= "-----------------------------------------------------------------------------------------";
            // Xuat cac order
            String setMenuCode;
            int pos;
            SetMenu setMnu;
            int total;
            
            for (Order order:this) {
                setMenuCode = order.getSetMenuCode(); // lay ra setMenu
                pos = setMnuList.indexOf(new SetMenu(setMenuCode));
                setMnu = setMnuList.get(pos);
                total = order.getNumTable() * setMnu.getPrice();
                Date preferedDate = order.getPreferedDate();
                String line = String.format("%-8s| %-11s| %-12s| %-10s| %10s| %-7s| %10s",
                    order.getOrderCode(),
                    ConsoleInputter.dateStr(preferedDate, DATE_PAT),
                    order.getCustCode(),
                    order.getSetMenuCode(),
                    dfCustom.format(setMnu.getPrice()),
                    order.getNumTable(),
                    dfCustom.format(total)
                );
                System.out.println(line);
            }
            // Xuat day khung
            System.out.println(footer);
        }
    }
}
