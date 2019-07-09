package com.goyarce.api.beans.local;

import com.goyarce.api.beans.mysql.Bill;
import com.goyarce.api.beans.mysql.Charge;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Entity;
import java.util.List;


public class BillDetail {

    @Field("Bill")
    private Bill bill;

    @Field("Charges")
    private List<Charge> chargeList;

    public BillDetail(){}

    public BillDetail(Bill bill, List<Charge> chargeList) {
        this.bill = bill;
        this.chargeList = chargeList;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public List<Charge> getChargeList() {
        return chargeList;
    }

    public void setChargeList(List<Charge> chargeList) {
        this.chargeList = chargeList;
    }
}
