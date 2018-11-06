package com.doubleip.geoapibenchmark.repositories.mongo;

import com.doubleip.geoapibenchmark.model.mongo.MGeoObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MGeoObjectRepository extends MongoRepository<MGeoObject, String> {
}
