package ru.example.losingweighttogether.meal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MealDto {
    Long id;

    @NotBlank(message = "Название блюда должно быть указано.")
    String name;

    @PositiveOrZero(message = "Количество калорий в блюде должно быть указано и не может быть меньше 0.")
    Double kkal;

    @PositiveOrZero(message = "Количество белка в блюде должно быть указано и не может быть меньше 0.")
    Double protein;

    @PositiveOrZero(message = "Количество жиров в блюде должно быть указано и не может быть меньше 0.")
    Double fats;

    @PositiveOrZero(message = "Количество углеводов в блюде должно быть указано и не может быть меньше 0.")
    Double carb;
}
