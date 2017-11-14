package com.watchShop.DAO;

import com.watchShop.entity.Statistics;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Oleksandr Ryzhkov on 05.11.2017.
 */
public interface StatisticsRepository extends CrudRepository<Statistics, Long> {
    Statistics save(Statistics statistics);

    Statistics getStatisticsByDateOfStatistics(LocalDate date);

    List<Statistics> findByDateOfStatisticsBetween(LocalDate start, LocalDate end);

//    @Query("select v from Statistics v " +
//            "where v.dateOfStatistics between ?1 and ?2")
//    List<Statistics> getStatisticsByPeriod(LocalDate start, LocalDate end);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Statistics v set v.numberOfMadeRequestsPerDay = :count where v.dateOfStatistics = :date")
    void updateMadeRequestsByDate(@Param("date") LocalDate date, @Param("count") int madeRequests);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Statistics v set v.numberOfSuccessRequestsPerDay = :count where v.dateOfStatistics = :date")
    void updateSuccessRequestsByDate(@Param("date") LocalDate date, @Param("count") int successRequests);
}
