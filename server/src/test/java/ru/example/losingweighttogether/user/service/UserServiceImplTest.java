package ru.example.losingweighttogether.user.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.example.losingweighttogether.exception.DuplicatedDataException;
import ru.example.losingweighttogether.user.UserController;
import ru.example.losingweighttogether.user.dto.UserDto;
import ru.example.losingweighttogether.user.dto.UserShortDto;
import ru.example.losingweighttogether.user.enums.UserGender;
import ru.example.losingweighttogether.user.enums.UserGoals;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserServiceImplTest {
    UserController userController;

    @Autowired
    public UserServiceImplTest(UserController userController) {
        this.userController = userController;
    }

    // Создаем нового пользователя, получаем его из бд по id и сравниваем поля
    @Test
    @DisplayName("Test create new user.")
    void createUserWhenAccess() {
        UserDto userDto = new UserDto(
                null,
                "Элизабет",
                UserGender.FEMALE,
                "eliz@gmail.com",
                34L,
                73L,
                177L,
                UserGoals.WEIGHT_LOSS
        );

        UserShortDto createdUser = userController.create(userDto);

        assertEquals(userDto.getEmail(), createdUser.getEmail());
    }

    @Test
    @DisplayName("Test create new user when email exists.")
    void createUserWhenExistsThenThrowException() {
        UserDto userDto = new UserDto(
                null,
                "Ноэль",
                UserGender.MALE,
                "noel@gmail.com",
                34L,
                73L,
                177L,
                UserGoals.WEIGHT_LOSS
        );
        userController.create(userDto);
        DuplicatedDataException e = assertThrows(DuplicatedDataException.class, () -> userController.create(userDto));

        assertEquals(e.getMessage(), "Пользователь с email " + userDto.getEmail() + " уже существует.");
    }
}