package ru.example.losingweighttogether.meal.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.example.losingweighttogether.user.dto.UserShortDto;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MealRecordsDto {
    UserShortDto user;
    Double eatenKkal;
    LocalDate mealDate;
}
