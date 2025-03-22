package ru.example.losingweighttogether.meal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.losingweighttogether.meal.dto.MealDto;

import java.util.List;

@RestController
@RequestMapping(path = "/meal")
@RequiredArgsConstructor
@Slf4j
public class MealController {
    private final MealClient mealClient;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody MealDto mealDto) {
        log.info("Добавляем новое блюдо {}.", mealDto);
        return mealClient.create(mealDto);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Object> createMealRecords(@RequestBody
                                                    @NotEmpty(message = "Список съеденных блюд не может быть пустым")
                                                    List<@Valid MealDto> eatenMeal, @PathVariable("userId") Long userId) {
        log.info("Добавляем новую запись о съеденных блюдах пользователя {}.", eatenMeal);
        return mealClient.createMealRecords(userId, eatenMeal);
    }
}
