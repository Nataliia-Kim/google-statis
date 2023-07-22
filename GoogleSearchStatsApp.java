
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.io.IOException;

    public class GoogleSearchStatsApp {
        private final SearchService searchService;

        @Inject
        public GoogleSearchStatsApp(SearchService searchService) {
            this.searchService = searchService;
        }

        public void getSearchStats(String query) {
            try {
                int searchResultsCount = searchService.getSearchResultsCount(query);

                System.out.println("Search query: " + query);
                System.out.println("Search results count: " + searchResultsCount);
            } catch (IOException e) {
                System.out.println("Error occurred while fetching data: " + e.getMessage());
            }
        }

        public static void main(String[] args) {
            if (args.length == 0) {
                System.out.println("Usage: java GoogleSearchStatsApp <search_query>");
                return;
            }

            String searchQuery = String.join(" ", args);

            Injector injector = Guice.createInjector();
            GoogleSearchStatsApp app = injector.getInstance(GoogleSearchStatsApp.class);
            app.getSearchStats(searchQuery);
        }
    }

