package com.goyarce.api.controller;

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

    @RequestMapping(method = RequestMethod.GET, value = "/bill/allbill")

    @ResponseBody
    public List<Bill> getAllBills(){
        Iterable<Bill> bills = billRepo.findAll();
        List<Bill> billList = new ArrayList<Bill>();
        for(Bill bill:bills){
            billList.add(bill);
        }
        return billList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bill/{id}")

    @ResponseBody
    public Bill getBillsById(@PathVariable Integer id){
        Optional<Bill> bill = billRepo.findById(id);
        if (!bill.isPresent())
            throw new ResourceNotFoundException("Bill with ID: " + id + " not found.");
        return bill.get();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bill/{id}/details")

    @ResponseBody
    public BillDetail getBillsDetails(@PathVariable Integer id){

        Optional<Bill> bill = billRepo.findById(id);
        if (!bill.isPresent())
            throw new ResourceNotFoundException("Bill with ID: " + id + " not found.");

        Iterable<Charge> charges = chargeRepo.getChargeByBill_id(id);

        List<Charge> chargeList = new ArrayList<Charge>();
        for(Charge charge:charges){
            chargeList.add(charge);
        }

        BillDetail detail = new BillDetail(bill.get(),chargeList);
        return detail;
    }

    @Autowired
    Validator validator;

    @RequestMapping(method = RequestMethod.POST, value = "/bill")

    @ResponseBody
    public Bill getBillsById(@RequestBody Bill bill)
            throws ResourceNotFoundException {

        bill.setIssue_date(new Date());

        Iterable<Charge> charges = validator.checkBill(bill);

        Bill savedBill = billRepo.save(bill);

        charges.forEach(charge -> {
            charge.setBill_id(savedBill.getId());
            charge.setState("B");
        });
        chargeRepo.saveAll(charges);

        return savedBill;
    }
}