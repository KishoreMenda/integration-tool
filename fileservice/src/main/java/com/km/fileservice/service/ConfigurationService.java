package com.km.fileservice.service;

import com.km.fileservice.model.Configuration;
import com.km.fileservice.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


import java.util.Optional;
import java.util.List;

@Service
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;

    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public List<Configuration> fetchConfigurations() {
        return configurationRepository.findAll()
                .collectList()
                .block();
    }


}