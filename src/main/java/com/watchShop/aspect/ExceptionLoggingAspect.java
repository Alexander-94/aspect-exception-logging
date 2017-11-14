package com.watchShop.aspect;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.watchShop.exception.GenericEngineException;
import com.watchShop.service.StatisticsService;
import com.watchShop.service.WatchService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.time.LocalDate;

/**
 * Created by Oleksandr Ryzhkov on 04.11.2017.
 */
@Aspect
@Component
public class ExceptionLoggingAspect {
    private int madeRequestsPerDay;
    private int successRequestsPerDay;

    //todo refactor counting: 1. should me Map date to count, 2. every 10 requests (sysproperty constant) persist in DB, 3. when now() is changed reset counters
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private WatchService watchService;

    //    @Pointcut("execution(* com.watchShop.controller..*(..))")
    @Pointcut("bean(*Controller)")
    public void controllerMethod() {
    }

    @Pointcut("bean(*Controller) && !bean(VisitorStatisticsController)")
    public void controllerMethodsWithoutStatistics() {
    }

    @Pointcut("execution(* com.watchShop.controller.WatchController.getWatchByTitle(..))")
    public void exactControllerMethod() {
    }

    @Pointcut("bean(*Controller) && !bean(WatchController)")
    public void controllerMethodsWatchController() {
    }

    @Pointcut("execution(* com.watchShop.controller.WatchController.addNewWatch(..))")
    public void exactControllerAddNewWatchMethod() {
    }

    @Around("exactControllerAddNewWatchMethod()")
    public Object logAndRepackExceptionsFromControllersWatch(ProceedingJoinPoint pjp) throws URISyntaxException {
        try {
            return pjp.proceed();
        } catch (GenericEngineException e) {
            System.out.println("ASP addWatch GenericEngineException was caught in controller layer. The message is " + e.getMessage());
            JSONObject response = new JSONObject();
            response.put("ASP addWatch errorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Error", "Server error occurred during the execution of request").body(response);
            //return HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (Throwable e) {
            System.out.println("addWatch Exception of type " + e.getClass() + " was caught in controller layer. The message is " + e.getMessage());
            JSONObject response = new JSONObject();
            response.put("ASP addWatch errorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Error", "Server error occured during the execution of request").body(response);
            //return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Around("controllerMethodsWithoutStatistics()")
    public Object logAndRepackExceptionsFromControllers(ProceedingJoinPoint pjp) throws URISyntaxException {
        try {
            return pjp.proceed();
        } catch (GenericEngineException e) {
            System.out.println("watchTitle ASP GenericEngineException was caught in controller layer. The message is " + e.getMessage());
            JSONObject response = new JSONObject();
            response.put("ASP watchTitle errorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Error", "Server error occurred during the execution of request").body(response);
        } catch (Throwable e) {
            System.out.println("watchTitle Exception of type " + e.getClass() + " was caught in controller layer. The message is " + e.getMessage());
            JSONObject response = new JSONObject();
            response.put("ASP watchTitle errorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Error", "Server error occured during the execution of request").body(response);
        }
    }

    @Before("controllerMethodsWithoutStatistics()")
    public void countMadeRequests(JoinPoint joinPoint) {
        madeRequestsPerDay++;
        statisticsService.updateMadeRequestsStatisticsByDate(LocalDate.now(), madeRequestsPerDay);
    }

    @After("controllerMethodsWithoutStatistics()")
    public void countSuccessRequests() {
        successRequestsPerDay++;
        statisticsService.updateSuccessRequestsStatisticsByDate(LocalDate.now(), successRequestsPerDay);
    }
}
