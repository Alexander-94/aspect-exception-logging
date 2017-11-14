package com.watchShop.DAO;

import com.watchShop.entity.Status;
import com.watchShop.entity.Watch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Oleksandr Ryzhkov on 08.11.2017.
 */
@RepositoryRestResource(collectionResourceRel = "watches", path = "watches")
public interface WatchPaginatingRepository extends PagingAndSortingRepository <Watch, Long>{

    Page<Watch> findByStatus(Pageable pageable, Status status);

}
