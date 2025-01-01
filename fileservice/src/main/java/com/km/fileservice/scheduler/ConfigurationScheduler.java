package com.km.fileservice.scheduler;

import com.km.fileservice.camel.DynamicRouteManager;
import com.km.fileservice.model.Configuration;
import com.km.fileservice.service.ConfigurationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfigurationScheduler {
    private final ConfigurationService configurationService;
    private final DynamicRouteManager dynamicRouteManager;

    public ConfigurationScheduler(ConfigurationService configurationService, DynamicRouteManager dynamicRouteManager) {
        this.configurationService = configurationService;
        this.dynamicRouteManager = dynamicRouteManager;
    }

    @Scheduled(fixedRate = 60000) // Every 60 seconds
    public void refreshRoutes() {
        System.out.println("Refreshing routes...");
        try {
            List<Configuration> configurations = configurationService.fetchConfigurations();
            System.out.println("size is " + configurations.size());
            dynamicRouteManager.createOrUpdateRoutes(configurations);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
