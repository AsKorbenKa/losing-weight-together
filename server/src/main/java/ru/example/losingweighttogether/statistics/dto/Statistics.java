package ru.example.losingweighttogether.statistics.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Statistics {
    String dailyCalorieIntake;
    String eatenKkalAndNumberOfMeals;
    String hasTheGoalBeenAchieved;
}
