package com.watchShop.service;

import com.watchShop.entity.Combination;
import com.watchShop.entity.Watch;
import com.watchShop.entity.WatchBelt;

/**
 * Created by Oleksandr Ryzhkov on 31.10.2017.
 */
public interface CombinationService {
    Combination createCombination(Watch watch, WatchBelt watchBelt);

    Combination addNewWatchToCombination(Combination combination, Watch watch);
}
