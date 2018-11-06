package com.doubleip.geoapibenchmark.repositories.neo4j;

import com.doubleip.geoapibenchmark.model.foodie.NRecipe;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NRecipeRepository extends Neo4jRepository<NRecipe, Long> {
}
