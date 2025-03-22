package ru.example.losingweighttogether.meal.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MealDto {
    Long id;
    String name;
    Double kkal;
    Double protein;
    Double fats;
    Double carb;
}
