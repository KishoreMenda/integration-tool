package com.kk.configurationservice.service;

import com.kk.configurationservice.dto.ConfigurationRequestDTO;
import com.kk.configurationservice.model.Configuration;
import com.kk.configurationservice.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConfigurationService {

    private final ConfigurationRepository repository;

    @Autowired
    public ConfigurationService(ConfigurationRepository repository) {
        this.repository = repository;
    }

    public Configuration saveConfiguration(ConfigurationRequestDTO dto) {
        // Map DTO to entity
        Configuration configuration = new Configuration();
        configuration.setId(UUID.randomUUID().toString()); // Generate a unique ID
        configuration.setSourceSystem(dto.getSourceSystem());
        configuration.setTargetSystem(dto.getTargetSystem());
        configuration.setScenarioName(dto.getScenarioName());
        configuration.setInputFolder(dto.getInputFolder());
        configuration.setOutputFolder(dto.getOutputFolder());
        configuration.setInputQueue(dto.getInputQueue());
        configuration.setOutputQueue(dto.getOutputQueue());

        return repository.save(configuration);
    }
}
