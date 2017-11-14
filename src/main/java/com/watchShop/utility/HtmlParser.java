package com.watchShop.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.watchShop.entity.Watch;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Oleksandr Ryzhkov on 06.11.2017.
 */
public class HtmlParser {
    private static ObjectMapper mapper = new ObjectMapper();

    public static List<Watch> parseRawDateToWatches(String rawData) throws IOException {
        String preparedString = prepareStringForParsing(rawData);

        Watch[] array = mapper.readValue(preparedString, Watch[].class);
        List<Watch> watches = new ArrayList<>();
        Collections.addAll(watches, array);
        return watches;
    }

    private static String prepareStringForParsing(String rawData) {
        Document document = Jsoup.parse(rawData);
        List<Element> resultingElements = document.select("script").stream()
                .filter(item -> item.html().contains("GOOGLE"))
                .collect(Collectors.toList());

        int start = resultingElements.get(0).html().indexOf("\"impressions\":");
        int end = resultingElements.get(0).html().lastIndexOf(",\n" +
                "    \"currentStore\"");
        String arrayOfWatches = resultingElements.get(0).html().substring(start,end);
        String substring = arrayOfWatches.replaceFirst("\"impressions\":", "").trim();
        return substring.substring(0, substring.lastIndexOf("}"));
    }



}
