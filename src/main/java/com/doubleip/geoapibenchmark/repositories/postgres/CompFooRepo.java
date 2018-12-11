package com.doubleip.geoapibenchmark.repositories.postgres;

import com.doubleip.geoapibenchmark.model.postgres.CompFoo;
import com.doubleip.geoapibenchmark.model.postgres.PGeoObject;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompFooRepo extends CrudRepository<CompFoo, Long> {

    @Procedure
    CompFoo getFoo(Long v1, Long v2);

}
