package ru.example.losingweighttogether.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.example.losingweighttogether.user.enums.UserGender;
import ru.example.losingweighttogether.user.enums.UserGoals;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;

    @NotBlank(message = "Имя пользователя должно быть указано.")
    String name;

    UserGender gender;

    @NotBlank(message = "Email пользователя должен быть указан.")
    @Email(message = "Email должен быть в формате user@yandex.ru.")
    String email;

    @Positive(message = "Возраст не может быть меньше либо равен нулю.")
    Long age;

    @Positive(message = "Вес не может быть меньше либо равен нулю.")
    Long weightInKg;

    @Positive(message = "Рост не может быть меньше либо равен нулю.")
    Long heightInCm;

    UserGoals goal;
}
