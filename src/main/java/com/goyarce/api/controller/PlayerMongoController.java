package com.goyarce.api.controller;

import com.goyarce.api.beans.mongo.Player;
import com.goyarce.api.repositories.mongo.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

@Controller
public class PlayerMongoController {

    public static final Boolean DEBUG = false;

    @Autowired
    PlayerRepo playerRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/player/allplayer")

    @ResponseBody
    public List<Player> getAllPlayers(HttpServletRequest request){
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
    public Player getPlayerById(@PathVariable String id){
        Optional<Player> result = playerRepo.findById(id);
        if(DEBUG){
            System.out.println("/player/" + id + ": " + result.get().toString());
        }
        return result.get();
    }

    /*public String getReqHeadersToString(HttpServletRequest request){
        String head = "";
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            head = head + headerNames.nextElement() + ": " + request.getHeader(headerNames.nextElement()) + '\n';
        }
        return head;
    }*/
}
