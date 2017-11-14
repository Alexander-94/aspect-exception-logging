package com.watchShop.client;

import com.watchShop.entity.Watch;
import com.watchShop.exception.GenericEngineException;
import com.watchShop.service.WatchService;
import com.watchShop.utility.HtmlParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Oleksandr Ryzhkov on 06.11.2017.
 */
@Component
public class WatchClient {
    private final Logger log = LoggerFactory.getLogger(WatchClient.class);

    @Value("${systemProperty.dataServer}")
    private String URL_SERVER_TO_GET_DATA_FROM;

    private WatchService watchService;

    @Autowired
    public WatchClient(WatchService watchService) {
        this.watchService = watchService;
    }

    public boolean fetchDataFromThirdPartyServer() throws GenericEngineException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String rawRequestResult = restTemplate.getForObject(URL_SERVER_TO_GET_DATA_FROM, String.class);

            List<Watch> watches = HtmlParser.parseRawDateToWatches(rawRequestResult);

            watchService.addNewWatches(watches);
            return true;
        } catch (Exception e) {
            log.error("Could not load watches from the third party server and store them", e);
            throw new GenericEngineException("Could not fetch and store data from the third party server");
        }
    }
}
