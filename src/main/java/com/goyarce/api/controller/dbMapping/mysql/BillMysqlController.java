package com.goyarce.api.controller.dbMapping.mysql;

import com.goyarce.api.beans.local.BillDetail;
import com.goyarce.api.beans.mysql.Bill;
import com.goyarce.api.beans.mysql.Charge;
import com.goyarce.api.beans.validators.Validator;
import com.goyarce.api.exceptions.ResourceNotFoundException;
import com.goyarce.api.repositories.mysql.BillRepo;
import com.goyarce.api.repositories.mysql.ChargeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class BillMysqlController {

    @Autowired
    BillRepo billRepo;

    @Autowired
    ChargeRepo chargeRepo;

    @Autowired
    Validator validator;

    @RequestMapping(method = RequestMethod.GET, value = "/bill/allbill")

    @ResponseBody
    public List<Bill> getAllBills(){
        System.out.println("[GET]: /bill/allbill");
        Iterable<Bill> bills = billRepo.findAll();
        List<Bill> billList = new ArrayList<>();
        for(Bill bill:bills){
            billList.add(bill);
        }
        return billList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bill/{id}/overview")

    @ResponseBody
    public Bill getBillsById(@PathVariable Integer id){
        System.out.println("[GET]: /bill/{id}/overview");
        Optional<Bill> bill = billRepo.findById(id);
        if (!bill.isPresent())
            throw new ResourceNotFoundException("Bill with ID: " + id + " not found.");
        return bill.get();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/bill/{guardian_id}")

    @ResponseBody
    public Bill getBillsById(@PathVariable String guardian_id)
            throws ResourceNotFoundException {
        System.out.println("[POST]: /bill/{guardian_id}");
        Bill newBill = new Bill();
        newBill.setGuardian_id(guardian_id);
        newBill.setIssue_date(new Date());

        Iterable<Charge> charges = validator.checkBill(newBill);

        Bill savedBill = billRepo.save(newBill);

        charges.forEach(charge -> {
            charge.setBill_id(savedBill.getId());
            charge.setState("B");
        });
        chargeRepo.saveAll(charges);

        return savedBill;
    }
}
