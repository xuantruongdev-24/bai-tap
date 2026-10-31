package core;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

    int orderCode;
    String custCode;
    String setMenuCode;
    int numTable;
    Date preferedDate;

    public Order(int orderCode) {

    }

    public Order(int orderCode, String custCode, String setMenuCode, int numTable, Date preferedDate) {
        this.orderCode = orderCode;
        this.custCode = custCode;
        this.setMenuCode = setMenuCode;
        this.numTable = numTable;
        this.preferedDate = preferedDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.orderCode;
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
        final Order other = (Order) obj;
        return this.orderCode == other.orderCode;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getSetMenuCode() {
        return setMenuCode;
    }

    public void setSetMenuCode(String setMenuCode) {
        this.setMenuCode = setMenuCode;
    }

    public int getNumTable() {
        return numTable;
    }

    public void setNumTable(int numTable) {
        this.numTable = numTable;
    }

    public Date getPreferedDate() {
        return preferedDate;
    }

    public void setPreferedDate(Date preferedDate) {
        this.preferedDate = preferedDate;
    }
}
