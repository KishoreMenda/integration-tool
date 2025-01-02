package com.km.fileservice.camel;

import com.km.fileservice.model.Configuration;
import com.km.fileservice.service.ConfigurationService;
import org.apache.camel.CamelContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfigurationScheduler {

    private final ConfigurationService configurationService;
    private final DynamicRouteManager dynamicRouteManager;

    public ConfigurationScheduler(ConfigurationService configurationService,
                                  DynamicRouteManager dynamicRouteManager) {
        this.configurationService = configurationService;
        this.dynamicRouteManager = dynamicRouteManager;
    }

    @Scheduled(fixedRate = 60000) // Every 60 seconds
    public void refreshRoutes() {
        System.out.println("Refreshing routes...");
        try {
            // Fetch configurations
            List<Configuration> configurations = configurationService.fetchConfigurations();

            // Update routes dynamically
            dynamicRouteManager.createOrUpdateRoutes(configurations);

            System.out.println("Routes refreshed successfully.");
        } catch (Exception e) {
            System.err.println("Error refreshing routes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
