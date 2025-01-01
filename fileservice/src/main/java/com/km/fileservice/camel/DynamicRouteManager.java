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

    public void createOrUpdateRoutes(List<Configuration> configurations) throws Exception {
        for (Configuration config : configurations) {
            String routeId = "route-" + config.getId();

            System.out.print("--------");
            System.out.println(config.getId());
            System.out.println(config.getSourceType());
            System.out.print("--------");

            // Remove existing route if it exists
            if (camelContext.getRoute(routeId) != null) {
                camelContext.removeRoute(routeId);
            }



            // Add a new route based on the configuration
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    // Dynamic route logic based on configuration
                    if ("folder".equals(config.getSourceType())) {
                        String sourcePath = "file:" + config.getSourceDetails();
                        String destinationPath = "file:" + config.getDestinationDetails();

                        // Log to see if the route is actually being hit
                        from(sourcePath)
                                .routeId(routeId)
                                .log("Processing file: ${file:name}") // Add logging for file processing
                                .to(destinationPath)
                                .log("Moved file: ${file:name} to destination");
                    } else if ("queue".equals(config.getSourceType())) {
                        from("activemq:queue:" + config.getSourceDetails())
                                .routeId(routeId)
                                .to("file:" + config.getDestinationDetails());
                    }
                }
            });
        }
    }
}
