package ru.example.losingweighttogether.statistics.mapper;

import ru.example.losingweighttogether.statistics.dto.Statistics;
import ru.example.losingweighttogether.user.enums.UserGoals;

import java.time.LocalDate;

public class StatisticsMapper {
    public static Statistics mapUserDataToStatistics(double dailyCalorieIntake, double eatenKkal, int numberOfMeals,
                                                     UserGoals goal, LocalDate date) {
        String goalAchieved = "Вы достигли поставленной цели.";
        String goalNotAchieved = "Вы не достигли поставленной цели.";
        Statistics statistics = new Statistics();
        statistics.setDailyCalorieIntake("Ваша дневная норма калорий составляет " + dailyCalorieIntake + " ккал.");

        // Проверяем были ли записи о съеденных калориях за определенный день и выставляем соответствующее значение
        if (numberOfMeals == 0) {
            statistics.setEatenKkalAndNumberOfMeals(date + " вы не вносили данные о ваших приемах пищи.");
        } else {
            statistics.setEatenKkalAndNumberOfMeals(String.format(date + " вы съели %.2f ккал. " +
                    "Количество приемов пищи: " + numberOfMeals + ".", eatenKkal));
        }

        // Проверяем выполнена ли поставленная цель и выставляем соответствующее значение
        if (eatenKkal > dailyCalorieIntake) {
            if (goal.equals(UserGoals.GAIN_WEIGHT)) {
                statistics.setHasTheGoalBeenAchieved(goalAchieved);
            } else {
                statistics.setHasTheGoalBeenAchieved(goalNotAchieved);
            }
        } else if (eatenKkal == dailyCalorieIntake) {
            if (goal.equals(UserGoals.KEEP_WEIGHT)) {
                statistics.setHasTheGoalBeenAchieved(goalAchieved);
            } else {
                statistics.setHasTheGoalBeenAchieved(goalNotAchieved);
            }
        } else {
            if (goal.equals(UserGoals.WEIGHT_LOSS)) {
                statistics.setHasTheGoalBeenAchieved(goalAchieved);
            } else {
                statistics.setHasTheGoalBeenAchieved(goalNotAchieved);
            }
        }

        return statistics;
    }
}
