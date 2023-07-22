package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.controller.SearchController;
import org.example.inject.BaseModule;

public class SearchStatsApp {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java GoogleSearchStatsApp <search_query>");
            return;
        }
        String searchQuery = String.join(" ", args);

        Injector injector = Guice.createInjector(new BaseModule());
        SearchController searchController = injector.getInstance(SearchController.class);
        searchController.getSearchStats(searchQuery);
    }
}

