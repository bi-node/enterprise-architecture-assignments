package com.binode.partbmongodemo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface PersonRepository extends MongoRepository<Person,Long> {
}
