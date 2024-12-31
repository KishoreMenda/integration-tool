package com.kk.configurationservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class ConfigurationRequestDTO {
    private String sourceSystem;
    private String targetSystem;
    private String scenarioName;
    private String inputFolder;
    private String outputFolder;
    private String inputQueue;
    private String outputQueue;
}
