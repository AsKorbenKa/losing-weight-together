package ru.example.losingweighttogether.statistics.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.losingweighttogether.exception.NotFoundException;
import ru.example.losingweighttogether.meal.model.MealRecords;
import ru.example.losingweighttogether.meal.repository.MealRecordsRepository;
import ru.example.losingweighttogether.statistics.dto.Statistics;
import ru.example.losingweighttogether.statistics.mapper.StatisticsMapper;
import ru.example.losingweighttogether.user.enums.UserGender;
import ru.example.losingweighttogether.user.model.User;
import ru.example.losingweighttogether.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatisticsServiceImpl implements StatisticsService {
    MealRecordsRepository mealRecordsRepository;
    UserRepository userRepository;

    @Autowired
    public StatisticsServiceImpl(MealRecordsRepository mealRecordsRepository, UserRepository userRepository) {
        this.mealRecordsRepository = mealRecordsRepository;
        this.userRepository = userRepository;
    }

    /*
    Проверяем существует ли пользователь с таким id и получаем его из бд.
    Считаем дневную норму калорий пользователя.
    Получаем список съеденных калорий (каждая запись - 1 прием пищи) пользователя за определенный день и считаем
    количество съеденных колорий и количество приемов пищи.
    Передаем в маппер, который возвращает готовый отчет.
     */
    @Override
    @Transactional(readOnly = true)
    public Statistics getUserStatistics(Long userId, LocalDate date) {
        log.debug("Получаем статистику пользователя с id {} по дате {}.", userId, date);
        User user = findUserById(userId);
        double dailyCalorieIntake = countDailyCalorieIntake(user);
        List<MealRecords> eatenKkalRecords = mealRecordsRepository.findAllByUserIdAndMealDate(userId, date);

        if (!eatenKkalRecords.isEmpty()) {
            double eatenKkal = eatenKkalRecords.stream().map(MealRecords::getEatenKkal)
                    .reduce(Double::sum).get();
            int numberOfMeals = eatenKkalRecords.size();
            return StatisticsMapper.mapUserDataToStatistics(dailyCalorieIntake, eatenKkal, numberOfMeals, user.getGoal(),
                    date);
        }
        return StatisticsMapper.mapUserDataToStatistics(dailyCalorieIntake, 0, 0, user.getGoal(),
                date);
    }

    private User findUserById(Long userId) {
        log.debug("Проверяем существует ли пользователь по id {}.", userId);
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("Ошибка при получении статистики. Пользователь с id "
                        + userId + " не найден."));
    }

    private double countDailyCalorieIntake(User user) {
        log.debug("Высчитывает дневную норму калорий пользователя {}.", user);
        return switch (user.getGender()) {
            case UserGender.MALE -> 88.36 + (13.4 * user.getWeightInKg()) + (4.8 * user.getHeightInCm()) -
                    (5.7 * user.getAge());
            case UserGender.FEMALE -> 447.6 + (9.2 * user.getWeightInKg()) + (3.1 * user.getHeightInCm()) -
                    (4.3 * user.getAge());
        };
    }
}
