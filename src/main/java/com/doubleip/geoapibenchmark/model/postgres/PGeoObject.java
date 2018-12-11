package com.doubleip.geoapibenchmark.model.postgres;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name="geo_object")
@NamedStoredProcedureQuery(name = "getFoo",
        procedureName = "getFoo",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "f1"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "f2")
        }
        ,resultSetMappings = "mapping")
@SqlResultSetMapping(name = "mapping", classes = {
        @ConstructorResult(targetClass = CompFoo.class, columns = {
                @ColumnResult(name = "v1", type = Long.class),
                @ColumnResult(name = "v2", type = Long.class)
        })
})
public class PGeoObject {

    @Id
    @SequenceGenerator(name = "idgeo_seq", sequenceName = "idgeo_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgeo_seq")
    private Long id;

    private Double longitude;

    private Double latitude;

    public PGeoObject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
