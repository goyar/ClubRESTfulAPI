package com.goyarce.api.repositories.mongo;

import com.goyarce.api.beans.mongo.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepo extends MongoRepository<Player, String> {

}