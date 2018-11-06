package com.doubleip.geoapibenchmark.repositories.elasticsearch;

import com.doubleip.geoapibenchmark.model.elasticsearch.EGeoObject;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EGeoObjectRepository extends ElasticsearchRepository<EGeoObject, String> {
}
