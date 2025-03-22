package ru.example.losingweighttogether.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.example.losingweighttogether.user.enums.UserGender;
import ru.example.losingweighttogether.user.enums.UserGoals;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserShortDto {
    String name;
    UserGender gender;
    String email;
    Long age;
    Long weightInKg;
    Long heightInCm;
    UserGoals goal;
}
