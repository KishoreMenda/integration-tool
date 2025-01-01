package com.km.fileservice.model;

import lombok.*;
import com.azure.spring.data.cosmos.core.mapping.Container;
import org.springframework.data.annotation.Id;



@Container(containerName = "Configurations") // Maps to the Configurations container
@Getter
@Setter
@RequiredArgsConstructor
public class Configuration {

    @Id
    private String id;
    private String sourceType;
    private String sourceDetails;
    private String trigger;
    private String triggerDetails;
    private String action;
    private String destination;
    private String destinationDetails;
}
