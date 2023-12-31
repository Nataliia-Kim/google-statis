package org.example.service;

import com.google.inject.Singleton;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Singleton
public class GoogleSearchService implements SearchService {
    public SearchService searchService;
    @Override
    public int getSearchResultsCount(String query) throws IOException {
        String searchUrl = "https://www.google.com/search?q=" + query;
        String htmlContent = fetchHtmlContent(searchUrl);
        return extractSearchResultsCount(htmlContent);
    }

    private String fetchHtmlContent(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(httpGet);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8))) {

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            return result.toString();
        }
    }

    private int extractSearchResultsCount(String htmlContent) {
        Document doc = Jsoup.parse(htmlContent);
        //TODO Fix select count
        Elements resultStats = doc.select("#result-stats");

        if (resultStats.isEmpty()) {
            return 0;
        }

        // Get the text of the element containing the statistics
        String statsText = resultStats.first().text();

        // Use regular expression to extract the count from the text
        Pattern countPattern = Pattern.compile("([0-9,]+)\\s+results");
        Matcher matcher = countPattern.matcher(statsText);

        if (matcher.find()) {
            String countString = matcher.group(1).replace(",", "");
            return Integer.parseInt(countString);
        } else {
            return 0;
        }
    }
}
