package com.watchShop.service;

import com.watchShop.DAO.WatchBeltRepository;
import com.watchShop.DAO.WatchRepository;
import com.watchShop.entity.Combination;
import com.watchShop.entity.Watch;
import com.watchShop.entity.WatchBelt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Oleksandr Ryzhkov on 31.10.2017.
 */
@Service
public class CombinationServiceImpl implements CombinationService {
    @Autowired
    private WatchBeltRepository watchBeltRepository;

    @Autowired
    private WatchRepository watchRepository;

    @Override
    public Combination createCombination(Watch watch, WatchBelt watchBelt) {
        return new Combination(watchBelt, watch);
    }

    @Override
    public Combination addNewWatchToCombination(Combination combination, Watch watch) {
        combination.setWatch(watch);
        double priceOfWatchBeltOnly = combination.getPrice() - combination.getWatch().getPrice();
        combination.setPrice(priceOfWatchBeltOnly + watch.getPrice());
        return combination;
    }

}
