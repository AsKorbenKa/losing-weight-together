package ru.example.losingweighttogether.statistics;

import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/statistics/{userId}")
@RequiredArgsConstructor
@Slf4j
public class StatisticsController {
    private final StatisticsClient statisticsClient;

    @GetMapping
    public ResponseEntity<Object> getUserStatistics(@PathVariable("userId") Long userId,
                                                    @RequestParam(name = "date") @PastOrPresent LocalDate date) {
        return statisticsClient.getUserStatistics(userId, date);
    }
}
