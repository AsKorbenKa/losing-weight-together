package ru.example.losingweighttogether.meal.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.example.losingweighttogether.exception.NotFoundException;
import ru.example.losingweighttogether.meal.MealController;
import ru.example.losingweighttogether.meal.dto.MealDto;
import ru.example.losingweighttogether.meal.dto.MealRecordsDto;
import ru.example.losingweighttogether.user.enums.UserGender;
import ru.example.losingweighttogether.user.enums.UserGoals;
import ru.example.losingweighttogether.user.model.User;
import ru.example.losingweighttogether.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class MealRecordsServiceImplTest {
    MealController mealController;
    UserRepository userRepository;

    @Autowired
    public MealRecordsServiceImplTest(MealController mealController, UserRepository userRepository) {
        this.mealController = mealController;
        this.userRepository = userRepository;
    }

    @Test
    @DisplayName("Test create new meal records when user exists.")
    void createMealRecordsWhenUserExists() {
        User user = new User(
                null,
                "Элизабет",
                UserGender.FEMALE,
                "eliza@gmail.com",
                34L,
                73L,
                177L,
                UserGoals.WEIGHT_LOSS
        );
        User savedUser = userRepository.save(user);

        MealDto mealDto = new MealDto(
                null,
                "Другое пирожное",
                123.10,
                12.12,
                23.23,
                34.34
        );

        MealRecordsDto mealRecordsDto = mealController.createMealRecords(List.of(mealDto, mealDto), savedUser.getId());

        assertEquals(246.20, mealRecordsDto.getEatenKkal());
        assertEquals(mealRecordsDto.getMealDate(), LocalDate.now());
    }

    @Test
    @DisplayName("Test create new meal records when user does not exist.")
    void createMealRecordsWhenUserDoesNotExistThenThrowException() {
        MealDto mealDto = new MealDto(
                null,
                "Другое пирожное",
                123.10,
                12.12,
                23.23,
                34.34
        );

        NotFoundException e = assertThrows(NotFoundException.class, () ->
                mealController.createMealRecords(List.of(mealDto, mealDto), 9999L));

        assertEquals("Ошибка при добавлении съеденных калорий пользователя. Пользователь с " +
                "id 9999 не найден.", e.getMessage());
    }
}