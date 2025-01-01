package com.km.fileservice.repository;

import com.km.fileservice.model.Configuration;
import org.springframework.stereotype.Repository;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;

@Repository
public interface ConfigurationRepository extends ReactiveCosmosRepository<Configuration, String> {
}
