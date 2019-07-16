package com.goyarce.api.beans.validators;

import com.goyarce.api.beans.mongo.Guardian;
import com.goyarce.api.beans.mongo.Player;
import com.goyarce.api.beans.mysql.Bill;
import com.goyarce.api.beans.mysql.Charge;
import com.goyarce.api.exceptions.InconsistentArgumentsException;
import com.goyarce.api.exceptions.ResourceNotFoundException;
import com.goyarce.api.repositories.mongo.GuardianRepo;
import com.goyarce.api.repositories.mongo.PlayerRepo;
import com.goyarce.api.repositories.mysql.BillRepo;
import com.goyarce.api.repositories.mysql.ChargeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Validator {

    @Autowired
    PlayerRepo playerRepo;

    @Autowired
    GuardianRepo guardianRepo;

    @Autowired
    ChargeRepo chargeRepo;

    @Autowired
    BillRepo billRepo;

    public Charge checkCharge(Charge charge, Boolean isNew) throws ResourceNotFoundException, InconsistentArgumentsException {

        String player_id = charge.getPlayer_id();
        Optional <Player> player = playerRepo.findById(charge.getPlayer_id());

        if(!player.isPresent())
            throw new ResourceNotFoundException("Player with ID: " + player_id + " not found.");

        if (player.get().getGuardian().equals(charge.getGuardian_id())){

            if(!isNew){

                if(charge.stateIs("B") || charge.billNotNull())
                    throw new InconsistentArgumentsException("Charge state has to be in pending and not already billed.");

                Optional<Charge> oldCharge = chargeRepo.findById(charge.getId());

                if(!oldCharge.isPresent())
                    throw new ResourceNotFoundException("Charge with ID: " + charge.getId() + " not found.");

                if(oldCharge.get().stateIs("B"))
                    throw new InconsistentArgumentsException("Charge with ID: " + charge.getId() +
                            " is already billed. It can't be modified.");

            } else if(charge.idNotNull()){
                throw new InconsistentArgumentsException("Charge id has not to be set for new charges.");
            }

            return charge;
        } else {
            throw new InconsistentArgumentsException("Guardian with ID: " + charge.getGuardian_id() +
                    " doesn't match the Player with ID: " + player_id);
        }
    }

    public Iterable<Charge> checkBill(Bill bill) throws ResourceNotFoundException {

        String guardian_id = bill.getGuardian_id();
        Optional<Guardian> guardian = guardianRepo.findById(guardian_id);

        if(!guardian.isPresent())
            throw new ResourceNotFoundException("Guardian with ID: " + guardian_id + " not found.");

        Iterable<Charge> charges = chargeRepo.getChargeByGuardian_id(guardian_id);
        List<Charge> chargeList = new ArrayList<>();

        for(Charge charge:charges) {
            chargeList.add(charge);
        }
        if(chargeList.isEmpty())
            throw new ResourceNotFoundException("Guardian with ID: " + guardian_id + " doesn't show charges to bill.");

        bill.setTotal_amount(chargeList.stream().mapToDouble(Charge::getAmount).reduce((acc, next)-> acc + next).getAsDouble());

        return charges;
    }
}
