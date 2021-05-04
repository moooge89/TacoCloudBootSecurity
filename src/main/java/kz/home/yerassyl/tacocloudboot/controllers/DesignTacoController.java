package kz.home.yerassyl.tacocloudboot.controllers;

import kz.home.yerassyl.tacocloudboot.data.IngredientRepository;
import kz.home.yerassyl.tacocloudboot.data.TacoRepository;
import kz.home.yerassyl.tacocloudboot.models.Ingredient;
import kz.home.yerassyl.tacocloudboot.models.Order;
import kz.home.yerassyl.tacocloudboot.models.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import kz.home.yerassyl.tacocloudboot.models.Ingredient.Type;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    private final TacoRepository designRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm (Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type: types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {

        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());

    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors error, @ModelAttribute Order order){

        if(error.hasErrors()) {
            return "design";
        }

        Taco saved = designRepo.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }
}
