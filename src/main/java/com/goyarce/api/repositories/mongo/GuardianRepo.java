package com.goyarce.api.repositories.mongo;

import com.goyarce.api.beans.mongo.Guardian;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GuardianRepo extends MongoRepository<Guardian, String> {

}