package ru.example.losingweighttogether.meal.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.losingweighttogether.exception.DuplicatedDataException;
import ru.example.losingweighttogether.meal.dto.MealDto;
import ru.example.losingweighttogether.meal.dto.MealShortDto;
import ru.example.losingweighttogether.meal.mapper.MealMapper;
import ru.example.losingweighttogether.meal.model.Meal;
import ru.example.losingweighttogether.meal.repository.MealRepository;

import java.util.Optional;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MealServiceImpl implements MealService {
    MealRepository mealRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    // Добавляем в базу данных новое блюдо
    @Override
    @Transactional
    public MealShortDto create(MealDto mealDto) {
        log.debug("Добавляем новое блюдо в базу данных.");

        // Проверяем есть ли уже такое блюдо в базе данных
        if (getMealByName(mealDto.getName()).isPresent()) {
            throw new DuplicatedDataException("Блюдо с названием '" + mealDto.getName() + "' уже существует.");
        }

        Meal savedMeal = mealRepository.save(MealMapper.mapMealDtoToMeal(mealDto));

        return MealMapper.mapMealToMealShortDto(savedMeal);
    }

    private Optional<Meal> getMealByName(String name) {
        log.debug("Проверяем есть ли в базе данных блюдо с названием {}.", name);
        return mealRepository.findByName(name);
    }
}
