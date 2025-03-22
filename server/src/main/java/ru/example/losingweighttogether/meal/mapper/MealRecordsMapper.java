package ru.example.losingweighttogether.meal.mapper;

import ru.example.losingweighttogether.meal.dto.MealRecordsDto;
import ru.example.losingweighttogether.meal.model.MealRecords;
import ru.example.losingweighttogether.user.mapper.UserMapper;
import ru.example.losingweighttogether.user.model.User;

import java.time.LocalDate;

public class MealRecordsMapper {
    public static MealRecords mapMealRecordsDtoToMealRecords(User user, Double eatenKkal) {
        return new MealRecords(
                null,
                user,
                eatenKkal,
                LocalDate.now()
        );
    }

    public static MealRecordsDto mapMealRecordsToMealRecordsDto(MealRecords mealRecords) {
        return new MealRecordsDto(
                UserMapper.mapUserToUserShortDto(mealRecords.getUser()),
                mealRecords.getEatenKkal(),
                mealRecords.getMealDate()
        );
    }
}
