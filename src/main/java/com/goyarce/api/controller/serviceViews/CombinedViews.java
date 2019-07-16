package com.goyarce.api.controller.serviceViews;

import com.goyarce.api.beans.local.BillDetail;
import com.goyarce.api.beans.mysql.Bill;
import com.goyarce.api.beans.mysql.Charge;
import com.goyarce.api.exceptions.ResourceNotFoundException;
import com.goyarce.api.repositories.mysql.BillRepo;
import com.goyarce.api.repositories.mysql.ChargeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CombinedViews {
    @Autowired
    BillRepo billRepo;

    @Autowired
    ChargeRepo chargeRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/bill/{id}/details")

    @ResponseBody
    public BillDetail getBillsDetails(@PathVariable Integer id){
        System.out.println("[GET]: /bill/{id}/details");
        Optional<Bill> bill = billRepo.findById(id);
        if (!bill.isPresent())
            throw new ResourceNotFoundException("Bill with ID: " + id + " not found.");

        Iterable<Charge> charges = chargeRepo.getChargeByBill_id(id);

        List<Charge> chargeList = new ArrayList<>();
        for(Charge charge:charges){
            chargeList.add(charge);
        }

        return new BillDetail(bill.get(),chargeList);
    }

}
