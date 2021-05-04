package kz.home.yerassyl.tacocloudboot.controllers;

import kz.home.yerassyl.tacocloudboot.data.IngredientRepository;
import kz.home.yerassyl.tacocloudboot.models.Ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(String id){
        return ingredientRepo.findOne(id);
    }
}
