package org.example.service;

import java.io.IOException;

public interface SearchService {
    int getSearchResultsCount(String query) throws IOException;
}
