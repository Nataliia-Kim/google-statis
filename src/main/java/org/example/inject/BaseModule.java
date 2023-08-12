package org.example.inject;

import com.google.inject.AbstractModule;
import org.example.service.GoogleSearchService;
import org.example.service.SearchService;

public class BaseModule extends AbstractModule {
    @Override
    public void configure(){
        bind(SearchService.class).to(GoogleSearchService.class);
    }
}
