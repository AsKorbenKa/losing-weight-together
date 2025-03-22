package ru.example.losingweighttogether.meal.mapper;

import ru.example.losingweighttogether.meal.dto.MealDto;
import ru.example.losingweighttogether.meal.dto.MealShortDto;
import ru.example.losingweighttogether.meal.model.Meal;

public class MealMapper {
    public static Meal mapMealDtoToMeal(MealDto mealDto) {
        return new Meal(
                mealDto.getId(),
                mealDto.getName(),
                mealDto.getKkal(),
                mealDto.getProtein(),
                mealDto.getFats(),
                mealDto.getCarb()
        );
    }

    public static MealShortDto mapMealToMealShortDto(Meal meal) {
        return new MealShortDto(
                meal.getName(),
                meal.getKkal(),
                meal.getProtein(),
                meal.getFats(),
                meal.getCarb()
        );
    }
}
