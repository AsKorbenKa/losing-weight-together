package ru.example.losingweighttogether.meal.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.example.losingweighttogether.exception.DuplicatedDataException;
import ru.example.losingweighttogether.meal.MealController;
import ru.example.losingweighttogether.meal.dto.MealDto;
import ru.example.losingweighttogether.meal.dto.MealShortDto;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class MealServiceImplTest {
   MealController mealController;

    @Autowired
    public MealServiceImplTest(MealController mealController) {
        this.mealController = mealController;
    }

    @Test
    @DisplayName("Test create new meal.")
    void createMealWhenAccess() {
        MealDto mealDto = new MealDto(
                null,
                "Пирожное Павлова",
                123.10,
                12.12,
                23.23,
                34.34
        );
        MealShortDto createdMeal = mealController.create(mealDto);

        assertEquals(mealDto.getName(), createdMeal.getName());
    }

    @Test
    @DisplayName("Test create new meal when name exists.")
    void createMealWhenNameExistsThenThrowException() {
        MealDto mealDto = new MealDto(
                null,
                "Другое пирожное",
                123.10,
                12.12,
                23.23,
                34.34
        );
        mealController.create(mealDto);
        DuplicatedDataException e = assertThrows(DuplicatedDataException.class, () -> mealController.create(mealDto));

        assertEquals(e.getMessage(), "Блюдо с названием " + mealDto.getName() + " уже существует.");
    }
}