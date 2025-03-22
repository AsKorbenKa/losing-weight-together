package ru.example.losingweighttogether.meal.service;

import ru.example.losingweighttogether.meal.dto.MealDto;
import ru.example.losingweighttogether.meal.dto.MealShortDto;

public interface MealService {
    MealShortDto create(MealDto mealDto);
}
