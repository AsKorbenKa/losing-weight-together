package ru.example.losingweighttogether.meal.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.losingweighttogether.exception.NotFoundException;
import ru.example.losingweighttogether.meal.dto.MealDto;
import ru.example.losingweighttogether.meal.dto.MealRecordsDto;
import ru.example.losingweighttogether.meal.mapper.MealRecordsMapper;
import ru.example.losingweighttogether.meal.model.MealRecords;
import ru.example.losingweighttogether.meal.repository.MealRecordsRepository;
import ru.example.losingweighttogether.user.model.User;
import ru.example.losingweighttogether.user.repository.UserRepository;

import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MealRecordsServiceImpl implements MealRecordsService {
    MealRecordsRepository mealRecordsRepository;
    UserRepository userRepository;

    @Autowired
    public MealRecordsServiceImpl(MealRecordsRepository mealRecordsRepository,
                                  UserRepository userRepository) {
        this.mealRecordsRepository = mealRecordsRepository;
        this.userRepository = userRepository;
    }

    /*
    Проверяем существует ли пользователь, прежде чем добавлять в бд данные о его съеденных калориях.
    Считаем сколько kkal было съедено за этот прием пищи. После мапим в mealRecords и сохраняем в бд
     */
    @Override
    @Transactional
    public MealRecordsDto createMealRecords(Long userId, List<MealDto> eatenMeal) {
        User user = findUserById(userId);
        Double eatenKkal = eatenMeal.stream()
                .map(MealDto::getKkal).reduce(Double::sum).get();
        MealRecords mealRecords = mealRecordsRepository.save(
                MealRecordsMapper.mapMealRecordsDtoToMealRecords(user, eatenKkal));
        return MealRecordsMapper.mapMealRecordsToMealRecordsDto(mealRecords);
    }

    private User findUserById(Long userId) {
        log.debug("Проверяем существует ли пользователь по id {}.", userId);
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("Ошибка при добавлении съеденных калорий пользователя. Пользователь с id "
                        + userId + " не найден."));
    }
}
