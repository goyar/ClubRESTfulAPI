package com.goyarce.api.controller.dbMapping.mongo;

import com.goyarce.api.beans.mongo.Player;
import com.goyarce.api.exceptions.ResourceNotFoundException;
import com.goyarce.api.repositories.mongo.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class PlayerMongoController {

    private static final Boolean DEBUG = false;

    @Autowired
    PlayerRepo playerRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/player/allplayer")

    @ResponseBody
    public List<Player> getAllPlayers(HttpServletRequest request){
        System.out.println("[GET]: /player/allplayer");
        List<Player> result = playerRepo.findAll();
        /*System.out.println(getReqHeadersToString(request));*/
        if(DEBUG){
            for(Player player:result){
                System.out.println("/player/allplayer: " + player.toString());
            }
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/player/{id}")

    @ResponseBody
    public Player getPlayerById(@PathVariable String id) throws ResourceNotFoundException{
        System.out.println("[GET]: /player/{id}");
        Optional<Player> result = playerRepo.findById(id);
        if(!result.isPresent())
            throw new ResourceNotFoundException("Player ID: " + id + " not found.");
        if(DEBUG){
            System.out.println("/player/" + id + ": " + result.get().toString());
        }
        return result.get();
    }

}
