package com.watchShop.service;

import com.watchShop.DAO.StatisticsRepository;
import com.watchShop.entity.Statistics;
import com.watchShop.exception.GenericEngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * Created by Oleksandr Ryzhkov on 05.11.2017.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsRepository statisticsRepository;

    @Override
    public Statistics getStatisticsByDate(LocalDate date) throws GenericEngineException {
        Statistics statisticsByDateOfStatistics = statisticsRepository.getStatisticsByDateOfStatistics(date);
        if (statisticsByDateOfStatistics != null) {
            return statisticsByDateOfStatistics;
        } else {
            throw new GenericEngineException("Entity was not found");
        }
    }

    @Override
    public List<Statistics> getStatisticsForPeriod(LocalDate start, LocalDate end) throws GenericEngineException {
        List<Statistics> result = statisticsRepository.findByDateOfStatisticsBetween(start, end);
        if (result != null) {
            return result;
        } else {
            throw new GenericEngineException("Entity was not found");
        }
    }

    @Override
    public List<Statistics> getStatisticsForPeriod(Period period) throws GenericEngineException {
        List<Statistics> result = statisticsRepository.findByDateOfStatisticsBetween(LocalDate.now().minusDays(period.getDays()), LocalDate.now());
        if (result != null) {
            return result;
        } else {
            throw new GenericEngineException("Entity was not found");
        }
    }

    @Transactional
    @Override
    public void updateMadeRequestsStatisticsByDate(LocalDate date, int madeRequests) {
        Statistics result = statisticsRepository.getStatisticsByDateOfStatistics(date);
        if (result == null) {
            statisticsRepository.save(new Statistics(date));
        }
        statisticsRepository.updateMadeRequestsByDate(date, madeRequests);
    }

    @Transactional
    @Override
    public void updateSuccessRequestsStatisticsByDate(LocalDate date, int successRequests) {
        Statistics result = statisticsRepository.getStatisticsByDateOfStatistics(date);
        if (result == null) {
            statisticsRepository.save(new Statistics(date));
        }
        statisticsRepository.updateSuccessRequestsByDate(date, successRequests);
    }
}
