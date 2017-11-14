package com.watchShop.service;

import com.watchShop.entity.Statistics;
import com.watchShop.exception.GenericEngineException;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * Created by Oleksandr Ryzhkov on 05.11.2017.
 */
public interface StatisticsService {
    Statistics getStatisticsByDate(LocalDate date) throws GenericEngineException;

    List<Statistics> getStatisticsForPeriod(LocalDate start, LocalDate end) throws GenericEngineException;

    List<Statistics> getStatisticsForPeriod(Period period) throws GenericEngineException;

    void updateMadeRequestsStatisticsByDate(LocalDate date, int madeRequests);

    void updateSuccessRequestsStatisticsByDate(LocalDate date, int successRequests);

}
