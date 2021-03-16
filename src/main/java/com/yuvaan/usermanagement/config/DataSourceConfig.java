package com.yuvaan.usermanagement.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * {@link DataSourceConfig} will read the application configuration from application.yml file.
 * It uses {@link org.springframework.boot.autoconfigure.domain.EntityScan} to identify
 * the domain objects.
 * 
 * @author saurav mehta
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.yuvaan.usermanagement.repository")
@EntityScan(basePackages = "com.yuvaan.usermanagement.entity")
@EnableTransactionManagement
public class DataSourceConfig {
}
