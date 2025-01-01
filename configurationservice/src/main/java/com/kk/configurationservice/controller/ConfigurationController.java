package com.kk.configurationservice.controller;

import com.kk.configurationservice.dto.ConfigurationRequestDTO;
import com.kk.configurationservice.model.Configuration;
import com.kk.configurationservice.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;  // Import the UUID class


@RestController
@RequestMapping("/api/configurations")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @PostMapping
    public ResponseEntity<Configuration> saveConfiguration(@RequestBody ConfigurationRequestDTO configurationRequestDTO) {
        // Convert DTO to Entity
        Configuration configuration = new Configuration();
        configuration.setId(UUID.randomUUID().toString()); // Generate a unique ID
        configuration.setSourceType(configurationRequestDTO.getSourceType());
        configuration.setSourceDetails(configurationRequestDTO.getSourceDetails());
        configuration.setTrigger(configurationRequestDTO.getTrigger());
        configuration.setTriggerDetails(configurationRequestDTO.getTriggerDetails());
        configuration.setAction(configurationRequestDTO.getAction());
        configuration.setDestination(configurationRequestDTO.getDestination());
        configuration.setDestinationDetails(configurationRequestDTO.getDestinationDetails());

        // Save the Configuration object
        Configuration savedConfig = configurationService.saveConfiguration(configuration);

        // Return the saved configuration as a response
        return new ResponseEntity<>(savedConfig, HttpStatus.CREATED);
    }

}
