package com.kk.configurationservice.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Container(containerName = "Configurations") // Cosmos DB container name
@Getter
@Setter
@RequiredArgsConstructor
public class Configuration {

    @Id
    private String id; // Unique identifier

    private String sourceSystem;
    private String targetSystem;
    private String scenarioName;
    private String inputFolder;
    private String outputFolder;
    private String inputQueue;
    private String outputQueue;
}
