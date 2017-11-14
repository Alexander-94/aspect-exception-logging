package com.watchShop.DAO;

import com.watchShop.entity.Status;
import com.watchShop.entity.WatchBelt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Oleksandr Ryzhkov on 31.10.2017.
 */
public interface WatchBeltRepository extends CrudRepository<WatchBelt, Long> {
    List<WatchBelt> findAll();

    List<WatchBelt> getWatchBeltsByStatus(Status status);

    WatchBelt getById(long id);

    WatchBelt getByTitle(String title);

    WatchBelt save(WatchBelt watchBelt);

    void delete(WatchBelt watchBelt);
}
