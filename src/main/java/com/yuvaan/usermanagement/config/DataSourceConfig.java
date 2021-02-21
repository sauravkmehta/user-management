package com.yuvaan.usermanagement.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.yuvaan.usermanagement.repository")
@EntityScan(basePackages = "com.yuvaan.usermanagement.entity")
@EnableTransactionManagement
public class DataSourceConfig {
}
