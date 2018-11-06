package com.doubleip.geoapibenchmark.repositories.postgres;

import com.doubleip.geoapibenchmark.model.postgres.PGeoObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PGeoObjectRepository extends CrudRepository<PGeoObject, Long> {

    Page<PGeoObject> findAll(Pageable pageable);

}
