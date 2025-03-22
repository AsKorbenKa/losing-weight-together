package ru.example.losingweighttogether.meal.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.example.losingweighttogether.meal.MealController;
import ru.example.losingweighttogether.user.UserController;

@SpringBootTest
@AutoConfigureTestDatabase
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class MealRecordsServiceImplTest {
    MealController mealController;
    UserController userController;

    @Autowired
    public MealRecordsServiceImplTest(MealController mealController, UserController userController) {
        this.mealController = mealController;
        this.userController = userController;
    }

    @Test
    void createMealRecords() {
    }
}