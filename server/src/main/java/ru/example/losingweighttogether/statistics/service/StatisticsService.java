package ru.example.losingweighttogether.statistics.service;

import ru.example.losingweighttogether.statistics.dto.Statistics;

import java.time.LocalDate;

public interface StatisticsService {
    Statistics getUserStatistics(Long userId, LocalDate date);
}
