package kz.home.yerassyl.tacocloudboot.data;

import kz.home.yerassyl.tacocloudboot.models.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}
