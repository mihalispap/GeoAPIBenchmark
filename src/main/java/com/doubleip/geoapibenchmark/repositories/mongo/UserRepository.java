package com.doubleip.geoapibenchmark.repositories.mongo;

import com.doubleip.geoapibenchmark.model.mongo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
}
