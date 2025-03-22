package ru.example.losingweighttogether.statistics.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.example.losingweighttogether.exception.NotFoundException;
import ru.example.losingweighttogether.statistics.StatisticsController;
import ru.example.losingweighttogether.statistics.dto.Statistics;
import ru.example.losingweighttogether.user.enums.UserGender;
import ru.example.losingweighttogether.user.enums.UserGoals;
import ru.example.losingweighttogether.user.model.User;
import ru.example.losingweighttogether.user.repository.UserRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class StatisticsServiceImplTest {
    StatisticsController statisticsController;
    UserRepository userRepository;

    @Autowired
    public StatisticsServiceImplTest(StatisticsController statisticsController, UserRepository userRepository) {
        this.statisticsController = statisticsController;
        this.userRepository = userRepository;
    }

    @Test
    @DisplayName("Test get statistics when user exists.")
    void getUserStatisticsWhenUserExists() {
        User user = new User(
                null,
                "Дмитрий",
                UserGender.MALE,
                "dmitriy@gmail.com",
                26L,
                67L,
                183L,
                UserGoals.WEIGHT_LOSS
        );
        User createdUser = userRepository.save(user);

        Statistics statistics = statisticsController.getUserStatistics(createdUser.getId(), LocalDate.now());

        assertEquals("Ваша дневная норма калорий составляет 1716.36 ккал.", statistics.getDailyCalorieIntake());
        assertEquals(LocalDate.now() + " вы не вносили данные о ваших приемах пищи.",
                statistics.getEatenKkalAndNumberOfMeals());
    }

    @Test
    @DisplayName("Test get statistics when user does not exist.")
    void getUserStatisticsWhenUserDoesNotExistThenThrowException() {
        NotFoundException e = assertThrows(NotFoundException.class, () ->
                statisticsController.getUserStatistics(9999L, LocalDate.now()));

        assertEquals("Ошибка при получении статистики. Пользователь с id "
                + 9999 + " не найден.", e.getMessage());
    }
}