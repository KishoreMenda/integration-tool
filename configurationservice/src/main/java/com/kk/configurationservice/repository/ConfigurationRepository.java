package com.kk.configurationservice.repository;

import com.kk.configurationservice.model.Configuration;
import org.springframework.stereotype.Repository;
import com.azure.spring.data.cosmos.repository.CosmosRepository;

@Repository
public interface ConfigurationRepository extends CosmosRepository<Configuration, String> {
}
