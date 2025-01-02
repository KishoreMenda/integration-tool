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
    private String sourceType;
    private String sourceDetails;
    private String trigger;
    private String triggerDetails;
    private String action;
    private String destinationType;
    private String destinationDetails;
}
