package com.km.fileservice.camel;

import com.km.fileservice.model.Configuration;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DynamicRouteManager {
    private final CamelContext camelContext;

    public DynamicRouteManager(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    public void createOrUpdateRoutes(List<Configuration> configurations) {
        for (Configuration config : configurations) {
            String routeId = "route-" + config.getId();

            try {
                // Remove existing route if present
                if (camelContext.getRoute(routeId) != null) {
                    camelContext.removeRoute(routeId);
                    System.out.println("Removed existing route: " + routeId);
                }

                // Add new route
                camelContext.addRoutes(new RouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        System.out.println("Creating route: " + routeId);
                        System.out.println("Processing directory: " + config.getSourceDetails());

                        RouteDefinition route = from(getSourceUri(config))
                                .routeId(routeId)
                                .log("Processing file: ${header.CamelFileName} in directory: " + config.getSourceDetails());

                        // Add action
                        switch (config.getAction()) {
                            case "move":
                                route.to(getDestinationUri(config)).log("File moved to: " + config.getDestinationDetails());
                                break;
                            case "transform":
                                route.transform(body().convertToString())
                                        .transform(simple("<xml>${body}</xml>"))
                                        .setHeader("CamelFileName", simple("${file:name.noext}.xml"))
                                        .to(getDestinationUri(config))
                                        .log("File transformed and moved to: " + config.getDestinationDetails());
                                break;
                            case "copy":
                                route.to(getDestinationUri(config) + "?moveExisting=false").log("File copied to: " + config.getDestinationDetails());
                                break;
                        }
                    }
                });

                System.out.println("Route created successfully for configuration: " + config.getId());
            } catch (Exception e) {
                System.err.println("Error creating route for configuration: " + config.getId());
                e.printStackTrace();
                handleFailedConfiguration(config, e);
            }
        }
    }

    private void handleFailedConfiguration(Configuration config, Exception e) {
        System.err.println("Failed to create route for configuration: " + config.getId());
        System.err.println("Error: " + e.getMessage());
    }

    private String getSourceUri(Configuration config) {
        if ("folder".equals(config.getSourceType())) {
            return "file:" + config.getSourceDetails() + "?noop=true";
        } else if ("queue".equals(config.getSourceType())) {
            return "activemq:queue:" + config.getSourceDetails();
        }
        throw new IllegalArgumentException("Unsupported source type: " + config.getSourceType());
    }

    private String getDestinationUri(Configuration config) {
        if ("folder".equals(config.getDestinationType())) {
            return "file:" + config.getDestinationDetails();
        } else if ("queue".equals(config.getDestinationType())) {
            return "activemq:queue:" + config.getDestinationDetails();
        }
        throw new IllegalArgumentException("Unsupported destination type: " + config.getDestinationType());
    }


}
