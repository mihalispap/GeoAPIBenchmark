package com.doubleip.geoapibenchmark.repositories.postgres;

import com.doubleip.geoapibenchmark.model.postgres.CompFoo;
import com.doubleip.geoapibenchmark.model.postgres.PGeoObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PGeoObjectRepository extends CrudRepository<PGeoObject, Long> {

    Page<PGeoObject> findAll(Pageable pageable);

    @Procedure(name = "find_all_geo_objects")
    Object[] funcCall();

    @Procedure
    PGeoObject[] find_all_geo_objects();

    @Procedure
    String echoText(String text);

    @Procedure
    PGeoObject getGetGeoObject(Integer id);

    @Procedure
    PGeoObject getGeoObject(Integer id);

    @Procedure
    Integer addGeoObject(Double lat, Double lon);

    @Procedure
    List<CompFoo> getfoo();

    @Procedure(procedureName = "getFoo")
    CompFoo getFoo(Long v1, Long v2);

}
