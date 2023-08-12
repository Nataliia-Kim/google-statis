package org.example.service;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

class GoogleSearchServiceTest {

    @Test
    void getSearchResultsCount_ShouldReturnExpectedCount_WhenQueryIsProvided() throws Exception {
        // Given
        String query = "test query";

        SearchService mockSearchService = mock(SearchService.class);
        when(mockSearchService.getSearchResultsCount(anyString())).thenReturn(123456);

        GoogleSearchService googleSearchService = new GoogleSearchService();
        googleSearchService.searchService = mockSearchService;

        // When
        try {
            int result = googleSearchService.getSearchResultsCount(query);

            // Then
            assertEquals(123456, result, "Expected search results count does not match");
        } catch (IOException e) {
            fail("IOException thrown: " + e.getMessage());
        }
    }
}

