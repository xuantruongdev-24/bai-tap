package core;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {

    String code;
    String name;
    String phone;
    String email;

    public Customer() {
    }

    public Customer(String code, String name, String phone, String email) {
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    Customer(String code) {
        this.code = code; // Gán mã vào đối tượng tạm để phục vụ tìm kiếm/kiểm tra trùng
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        return Objects.equals(this.code, other.code);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

public void setname(String name){
    name = name.trim();
    int L = name.length();
    if(L >= 2 && L <=25) this.name = name;
}
public String getPhone(){
    return phone;
}
public void setPhone (String phone){
    phone = phone.trim();
    if(phone.matches("^0[2356789][\\d]{8}$")) this.phone = phone;
}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email.trim();
        if(email.matches("^[\\w -_ \\.+]*[\\w -_ \\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) 
            this.email = email; 
    }

    @Override
    public String toString() {
        return String.format("%-8s| %-20s| %-11s| %-18s", code, name, phone, email) ;
    }
    



}
