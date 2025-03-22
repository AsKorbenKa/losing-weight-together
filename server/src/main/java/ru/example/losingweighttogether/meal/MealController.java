package ru.example.losingweighttogether.meal;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.losingweighttogether.meal.dto.MealDto;
import ru.example.losingweighttogether.meal.dto.MealRecordsDto;
import ru.example.losingweighttogether.meal.dto.MealShortDto;
import ru.example.losingweighttogether.meal.service.MealRecordsService;
import ru.example.losingweighttogether.meal.service.MealService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/meal")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MealController {
    MealService mealService;
    MealRecordsService mealRecordsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MealShortDto create(@RequestBody MealDto mealDto) {
        return mealService.create(mealDto);
    }

    @PostMapping("/{userId}")
    public MealRecordsDto createMealRecords(@RequestBody List<MealDto> eatenMeal,
                                            @PathVariable("userId") Long userId) {
        return mealRecordsService.createMealRecords(userId, eatenMeal);
    }
}
