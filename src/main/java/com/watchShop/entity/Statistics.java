package com.watchShop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by Oleksandr Ryzhkov on 05.11.2017.
 */
@Entity
public class Statistics {
    @Id
    @GeneratedValue
    private long id;
    private Integer numberOfMadeRequestsPerDay = 0;
    private Integer numberOfSuccessRequestsPerDay = 0;
    @NotNull
    private LocalDate dateOfStatistics;

    public Statistics() {
    }

    public Statistics(LocalDate dateOfStatistics) {
        this.dateOfStatistics = dateOfStatistics;
    }

    public Integer getNumberOfMadeRequestsPerDay() {
        return numberOfMadeRequestsPerDay;
    }

    public void setNumberOfMadeRequestsPerDay(Integer numberOfMadeRequestsPerDay) {
        this.numberOfMadeRequestsPerDay = numberOfMadeRequestsPerDay;
    }

    public LocalDate getDateOfStatistics() {
        return dateOfStatistics;
    }

    public void setDateOfStatistics(LocalDate dateOfStatistics) {
        this.dateOfStatistics = dateOfStatistics;
    }

    public Integer getNumberOfSuccessRequestsPerDay() {
        return numberOfSuccessRequestsPerDay;
    }

    public void setNumberOfSuccessRequestsPerDay(Integer numberOfSuccessRequestsPerDay) {
        this.numberOfSuccessRequestsPerDay = numberOfSuccessRequestsPerDay;
    }
}
