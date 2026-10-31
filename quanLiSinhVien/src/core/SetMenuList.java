
package core;
import java. util. ArrayList;
import java. io. File; // cac lop truy xuat file van ban
import java.io. FileReader;
import java.io.BufferedReader;

import java.util.ArrayList;

import java.util.ArrayList;


public class SetMenuList extends ArrayList<SetMenu>{
    public void readFile(String fname){
        File f = new File(fname);
        if(!f.exists()){
            System.out.println("the file" + fname + "does not exist!");
            return;
        }
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while((line = br.readLine()) != null){
                String [] parts = line.split(",");
                if(parts.length == 4){
                    int price = Integer.parseInt(parts[2]);
                    parts[3] = parts[3].substring(1,parts[3].length()-2);
                    SetMenu m = new SetMenu(parts[0], parts[1],price,parts[3]);
                    this.add(m);
                }
            }
            br.close();
            fr.close();
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
    public void print(){
        if( this.isEmpty()){
            System.out.println("Set menu list is emppty!");
            return;
        }
        String header = "----------------------------------------------------\n"+
                        " List of set menus for ordering party: \n" +
                        "----------------------------------------------------\n";
        System.out.println(header);
        for (SetMenu m: this)  System.out.println(m.toStringScreen());
            
    }
    
}
