package com.watchShop.service;

import com.watchShop.entity.Status;
import com.watchShop.entity.Watch;
import com.watchShop.exception.GenericEngineException;

import java.util.List;
import java.util.Map;

/**
 * Created by Oleksandr Ryzhkov on 31.10.2017.
 */
public interface WatchService {
    Watch addNewWatch(Watch watch) throws GenericEngineException;

    List<Watch> addNewWatches(List<Watch> watches) throws GenericEngineException;

    boolean removeWatch(long id) throws GenericEngineException;

    List<Watch> getAllAvailableWatches();

    List<Watch> getAllWatchesByStatus(Status status);

    List<Watch> getAllWatches();

    Watch getWatchByTitle(String title) throws GenericEngineException;

    Watch getWatchById(long id) throws GenericEngineException;

    void updateWatch(long id, Map<String, String> mapWithProps) throws GenericEngineException;
}
