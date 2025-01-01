package com.kk.configurationservice.service;

import com.kk.configurationservice.model.Configuration;
import com.kk.configurationservice.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    public Configuration saveConfiguration(Configuration configuration) {
        return configurationRepository.save(configuration);
    }
}
