package ru.example.losingweighttogether.statistics;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import ru.example.losingweighttogether.statistics.dto.Statistics;
import ru.example.losingweighttogether.statistics.service.StatisticsService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/statistics/{userId}")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatisticsController {
    StatisticsService statisticsService;

    @GetMapping
    public Statistics getUserStatistics(@PathVariable("userId") Long userId,
                                        @RequestParam(name = "date") LocalDate date) {
        return statisticsService.getUserStatistics(userId, date);
    }
}
