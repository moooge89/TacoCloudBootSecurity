package kz.home.yerassyl.tacocloudboot.data;

import kz.home.yerassyl.tacocloudboot.models.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {}
