package org.example.controller;

import com.google.inject.Inject;
import org.example.service.SearchService;

import java.io.IOException;
import java.net.URLEncoder;

//TODO Refactor with Apache Commins CLI / Picocli
public class SearchController {
    private final SearchService searchService;

    @Inject
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    public void getSearchStats(String query) {

        try {
            String encodedQuery = URLEncoder.encode(query, "UTF-8");
            int searchResultsCount = searchService.getSearchResultsCount(encodedQuery);

            System.out.println("Search query: " + query);
            System.out.println("Search results count: " + searchResultsCount);
        } catch (IOException e) {
            System.out.println("Error occurred while fetching data: " + e.getMessage());
        }
    }
}
