package com.goyarce.api.controller;

import com.goyarce.api.beans.mongo.Guardian;
import com.goyarce.api.repositories.mongo.GuardianRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class GuardianMongoController {
    public static final Boolean DEBUG = false;

    @Autowired
    GuardianRepo guardianRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/guardian/allguardian")

    @ResponseBody
    public List<Guardian> getAllGuardians(){
        List<Guardian> result = guardianRepo.findAll();
        if(DEBUG){
            for(Guardian guardian:result){
                System.out.println("/getit/now2: " + guardian.toString());
            }
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/guardian/{id}")

    @ResponseBody
    public Guardian getGuardianById(@PathVariable String id){
        Optional<Guardian> result = guardianRepo.findById(id);

        if(DEBUG){
            System.out.println("/guardian/" + id + ": " + result.get().toString());
        }
        return result.get();
    }


}
