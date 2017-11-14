package com.watchShop.service;

import com.watchShop.entity.WatchBelt;

import java.util.List;

/**
 * Created by Oleksandr Ryzhkov on 31.10.2017.
 */
public interface WatchBeltService {
    List<WatchBelt> getAllAvailableWatchBelts();

    List<WatchBelt> getAllWatchBelts();

    WatchBelt getWatchBeltById(long id);

    WatchBelt getWatchBeltByTitle(String title);

    WatchBelt createNew(WatchBelt watchBelt);

    boolean removeWatchBelt(long id);
}
