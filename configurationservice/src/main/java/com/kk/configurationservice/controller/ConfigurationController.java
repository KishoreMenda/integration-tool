package com.kk.configurationservice.controller;

import com.kk.configurationservice.dto.ConfigurationRequestDTO;
import com.kk.configurationservice.model.Configuration;
import com.kk.configurationservice.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configurations")
public class ConfigurationController {

    private final ConfigurationService service;

    @Autowired
    public ConfigurationController(ConfigurationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> saveConfiguration(@RequestBody ConfigurationRequestDTO dto) {
        Configuration configuration = service.saveConfiguration(dto);
        return ResponseEntity.ok("Configuration saved with ID: ");
    }
}
