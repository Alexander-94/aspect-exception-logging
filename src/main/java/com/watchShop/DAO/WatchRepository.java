package com.watchShop.DAO;

import com.watchShop.entity.Status;
import com.watchShop.entity.Watch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Oleksandr Ryzhkov on 28.10.2017.
 */
public interface WatchRepository extends CrudRepository<Watch, Long> {

    List<Watch> getWatchesByStatus(Status status);

    Watch getById(long id);

    List<Watch> findAll();

    Watch save(Watch watch);

    List<Watch> save(List<Watch> list);

    @Query("select w from Watch w where w.title = :title")
    Watch getWatchByTitle(@Param("title") String title);

    void delete(Watch watch);
}
