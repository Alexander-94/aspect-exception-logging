package com.watchShop.service;

import com.watchShop.DAO.WatchBeltRepository;
import com.watchShop.entity.Status;
import com.watchShop.entity.WatchBelt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Oleksandr Ryzhkov on 31.10.2017.
 */
@Service
public class WatchBeltServiceImpl implements WatchBeltService {
    @Autowired
    private WatchBeltRepository watchBeltRepository;

    @Override
    public List<WatchBelt> getAllAvailableWatchBelts() {
        return watchBeltRepository.getWatchBeltsByStatus(Status.AVAILABLE);
    }

    @Override
    public List<WatchBelt> getAllWatchBelts() {
        return watchBeltRepository.findAll();
    }

    @Override
    public WatchBelt getWatchBeltById(long id) {
        return watchBeltRepository.getById(id);
    }

    @Override
    public WatchBelt getWatchBeltByTitle(String title) {
        return watchBeltRepository.getByTitle(title);
    }

    @Override
    public WatchBelt createNew(WatchBelt watchBelt) {
        return watchBeltRepository.save(watchBelt);
    }

    @Override
    public boolean removeWatchBelt(long id) {
        try {
            WatchBelt watchBelt = watchBeltRepository.getById(id);
            watchBeltRepository.delete(watchBelt);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
