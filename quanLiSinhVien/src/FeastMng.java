import core.SetMenuList;
import core.CustList;
import core.OrderList;
import tool.ConsoleInputter;

public class FeastMng {
    public static void main(String[] args){
        // Ten các file cua chuong trinh
        String setMnuFile = "FeastMenu.CSV";
        String custFile = "customers.dat";
        String orderFile = "feast_order_sercice.dat";
        
        // Chuan bi 3 list co ban cua chuong trinh duoc doc tu file ra
        SetMenuList setMnuList = new SetMenuList();
        setMnuList.readFile(setMnuFile);
        if (setMnuList.isEmpty()){
            System.out.println("Set menus are not ready. The program can not run.");
            ConsoleInputter.getStr("Enter for quit.");
            System.exit(0);
        }
        
        CustList custList = new CustList();
        custList.readFile(custFile);
        
        OrderList orderList = new OrderList(setMnuList, custList);
        orderList.readFile(orderFile);
        
        // Bien menu
        Object[] mnuOptions = { 
            /*1*/ "Register customers", 
            /*2*/ "Update customer information",
            /*3*/ "Search for customer information by name",
            /*4*/ "Display feast menus",
            /*5*/ "Place a feast order",
            /*6*/ "Update order information", 
            /*7*/ "Save data to file", 
            /*8*/ "Display Customer list", 
            /*9*/ "Display Order lists",
            /*10*/ "Quit" 
        };
        
        int choice;
        // Bien quan ly su thay doi trong hai danh sach custList va orderList
        boolean custChanged = false;
        boolean orderChanged = false;
        String title = "\nFEAST ORDER MANAGEMENT\n-----------------------------------------";
        
        // Chay chuong trinh
        do {
            System.out.println(title);
            for (int i = 0; i < mnuOptions.length; i++) {
                System.out.println((i + 1) + ". " + mnuOptions[i]);
            }
            choice = ConsoleInputter.getInt("Select an option", 1, mnuOptions.length);
            
            switch (choice) {
                case 1:
                    custList.addCustomer();
                    custChanged = true;
                    break;
                case 2:
                    custList.updapteCust();
                    custChanged = true;
                    break;
                case 3:
                    custList.searchName();
                    break;
                case 4:
                    setMnuList.print();
                    break;
                case 5:
                    orderList.addOrder();
                    orderChanged = true;
                    break;
                case 6:
                    orderList.updateOrder();
                    orderChanged = true;
                    break;
                case 7:
                    if (custChanged) {
                        custList.writeFile(custFile);
                        custChanged = false;
                    }
                    if (orderChanged) {
                        orderList.writeFile(orderFile);
                        orderChanged = false;
                    }
                    System.out.println("All data saved successfully.");
                    break;
                case 8:
                    custList.print();
                    break;
                case 9:
                    orderList.print();
                    break;
                case 10:
                    if (custChanged || orderChanged) {
                        boolean save = ConsoleInputter.getBoolean("Data changed. Do you want to save?");
                        if (save) {
                            if (custChanged) custList.writeFile(custFile);
                            if (orderChanged) orderList.writeFile(orderFile);
                        }
                    }
                    System.out.println("Good bye!");
                    break;
            }
        } while (choice != 10);
    }
}