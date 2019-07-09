package com.goyarce.api.controller;

import com.goyarce.api.beans.mysql.Charge;
import com.goyarce.api.beans.validators.Validator;
import com.goyarce.api.exceptions.InconsistentArgumentsException;
import com.goyarce.api.exceptions.ResourceNotFoundException;
import com.goyarce.api.repositories.mysql.ChargeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ChargeMysqlController {

    @Autowired
    ChargeRepo chargeRepo;

    @Autowired
    Validator validator;

    @RequestMapping(method = RequestMethod.GET, value = "/charge/allcharge")

    @ResponseBody
    public List<Charge> getAllCharges(){
        Iterable<Charge> charges = chargeRepo.findAll();
        List<Charge> chargeList = new ArrayList<>();
        for(Charge charge:charges){
            chargeList.add(charge);
        }
        return chargeList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/charge/{id}")

    @ResponseBody
    public Charge getChargeById(@PathVariable Integer id){
        Optional<Charge> charge = chargeRepo.findById(id);
        if (charge.isPresent()) return charge.get();
        throw new ResourceNotFoundException("Charge with ID: " + id + " not found.");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/charge")

    @ResponseBody
    public Charge addCharge(@RequestBody Charge charge)
            throws ResourceNotFoundException, InconsistentArgumentsException {
        charge.setDate(new Date());
        charge.setState("P");
        return chargeRepo.save(validator.checkCharge(charge, true));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/charge")

    @ResponseBody
    public Charge updateCharge(@RequestBody Charge charge)
            throws ResourceNotFoundException, InconsistentArgumentsException {
        return chargeRepo.save(validator.checkCharge(charge, false));
    }

}
