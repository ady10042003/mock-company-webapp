package com.mockcompany.webapp.controller;

import com.mockcompany.webapp.api.SearchReportResponse;
import com.mockcompany.webapp.model.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockcompany.webapp.services.SearchService;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Management decided it is super important that we have lots of products that match the following terms.
 * So much so, that they would like a daily report of the number of products for each term along with the total
 * product count.
 */
@RestController
public class ReportController {

    /**
     * The people that wrote this code didn't know about JPA Spring Repository interfaces!
     */
    private final EntityManager entityManager;
    private final SearchService searchService;

    @Autowired
    public ReportController(EntityManager entityManager, SearchService searchService) {
        this.entityManager = entityManager;
        this.searchService = searchService;
    }


    @GetMapping("/api/products/report")
    public SearchReportResponse runReport() {
        Map<String, Integer> hits = new HashMap<>();
        SearchReportResponse response = new SearchReportResponse();
        response.setSearchTermHits(hits);

        // Total product count
        int count = ((Number) entityManager.createQuery("SELECT COUNT(item) FROM ProductItem item").getSingleResult()).intValue();
        response.setProductCount(count);

        // Search term reports using SearchService
        hits.put("Cool", searchService.countMatchingItems("cool"));
        hits.put("Kids", searchService.countMatchingItems("kids"));
        hits.put("Amazing", searchService.countMatchingItems("amazing"));
        hits.put("Perfect", searchService.countMatchingItems("perfect"));

        return response;
    }
}
