package com.watchShop.controller;

import com.watchShop.DAO.StatisticsRepository;
import com.watchShop.entity.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Oleksandr Ryzhkov on 05.11.2017.
 */
@RestController
@RequestMapping("/v1/statistics")
public class VisitorStatisticsController {
    @Autowired
    private StatisticsRepository StatisticsRepository;

    @RequestMapping(value="/today", method = RequestMethod.GET)
    public Statistics getStatisticsForToday(){
        return StatisticsRepository.getStatisticsByDateOfStatistics(LocalDate.now());
    }

    @RequestMapping(value="/period/{start}/{end}", method = RequestMethod.GET)
    public List<Statistics> getStatisticsForPeriod(@PathVariable("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                   @PathVariable("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate end){
        return StatisticsRepository.findByDateOfStatisticsBetween(start, end);
    }
}
