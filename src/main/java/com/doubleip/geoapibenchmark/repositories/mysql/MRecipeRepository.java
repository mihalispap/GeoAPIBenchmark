package com.doubleip.geoapibenchmark.repositories.mysql;

import com.doubleip.geoapibenchmark.model.foodie.mysql.MRecipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MRecipeRepository extends CrudRepository<MRecipe, Long> {
}
