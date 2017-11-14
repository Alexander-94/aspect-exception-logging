package com.watchShop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.watchShop.DAO.WatchRepository;
import com.watchShop.entity.Status;
import com.watchShop.entity.Watch;
import com.watchShop.exception.GenericEngineException;
import com.watchShop.service.WatchService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Oleksandr Ryzhkov on 29.10.2017.
 */
@RestController
@RequestMapping("/v1/watch")
public class WatchController {
    private final Logger log = LoggerFactory.getLogger("INFOLogger");
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WatchService watchService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getWatchByTitle(@RequestParam("title") String title) throws GenericEngineException {
        try {
            log.info("watchTitle GET request was made");
            Watch watchByTitle = watchService.getWatchByTitle(title);
            return new ResponseEntity<>(mapper.writeValueAsString(watchByTitle), HttpStatus.OK);
        } catch (Exception e) {
            log.error("ASP Could not fetch watch by title", e);
            throw new GenericEngineException(e);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> addNewWatch(@RequestBody JsonNode newWatch) throws GenericEngineException {
        try {
            log.info("addWatch POST request was made");
            Watch watch = mapper.treeToValue(newWatch, Watch.class);
            watchService.addNewWatch(watch);
            return new ResponseEntity<>(mapper.writeValueAsString(watch), HttpStatus.OK);
        } catch (Exception e) {
            log.error("ASP Could not add new watch", e);
            throw new GenericEngineException(e);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeWatch(@PathVariable("id") long id) throws GenericEngineException {
        watchService.removeWatch(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateWatch(@PathVariable("id") long id, @RequestBody JsonNode detailsToUpdate) throws GenericEngineException {
        try {
            watchService.updateWatch(id, mapper.treeToValue(detailsToUpdate, Map.class));
        } catch (Exception e) {
            log.error("Could update existing watch", e);
            throw new GenericEngineException(e);
        }
    }
}

