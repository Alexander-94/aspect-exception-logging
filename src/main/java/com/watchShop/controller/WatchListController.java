package com.watchShop.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.watchShop.client.WatchClient;
import com.watchShop.entity.Status;
import com.watchShop.exception.GenericEngineException;
import com.watchShop.service.WatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by Oleksandr Ryzhkov on 01.11.2017.
 */
@RestController
@RequestMapping("/v1/watches")
public class WatchListController {
    private final Logger log = LoggerFactory.getLogger(WatchListController.class);
    private ObjectMapper mapper = new ObjectMapper();
    private WatchService watchService;
    private WatchClient watchClient;

    @Autowired
    public WatchListController(WatchService watchService, WatchClient watchClient) {
        this.watchService = watchService;
        this.watchClient = watchClient;
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonNode getAllWatches(@RequestParam("status") Optional<String> status) {
        Status status1 = Status.valueOf(status.orElse("AVAILABLE"));
        return mapper.valueToTree(watchService.getAllWatchesByStatus(status1));
    }

    @RequestMapping(value = "/thirdPartyWatches", method = RequestMethod.POST)
    public ResponseEntity loadWatchesFromThirdPartyServer() throws GenericEngineException {
        if (watchClient.fetchDataFromThirdPartyServer()) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            log.error("Could not load watches from the third party server and store them");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
