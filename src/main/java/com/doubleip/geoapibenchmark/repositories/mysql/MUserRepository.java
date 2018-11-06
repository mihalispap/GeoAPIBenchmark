package com.doubleip.geoapibenchmark.repositories.mysql;

import com.doubleip.geoapibenchmark.model.foodie.mysql.MUser;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MUserRepository extends CrudRepository<MUser, Long> {
}
