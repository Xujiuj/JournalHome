package com.kstt.admin.config;

import com.kstt.admin.config.datasource.DynamicDataSourceContextHolder;
import com.kstt.admin.config.datasource.DynamicDataSourceType;
import com.kstt.admin.config.datasource.DynamicRoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置，支持主从动态切换
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "masterDataSource")
    @ConfigurationProperties("spring.datasource.master.hikari")
    public HikariDataSource masterDataSource(@Qualifier("masterDataSourceProperties") DataSourceProperties masterProperties) {
        return masterProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.datasource.slave", name = "url")
    @ConfigurationProperties("spring.datasource.slave")
    public DataSourceProperties slaveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "slaveDataSource")
    @ConditionalOnBean(name = "slaveDataSourceProperties")
    @ConfigurationProperties("spring.datasource.slave.hikari")
    public HikariDataSource slaveDataSource(@Qualifier("slaveDataSourceProperties") DataSourceProperties slaveProperties) {
        return slaveProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    public DataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                 @Autowired(required = false) @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicDataSourceType.MASTER, masterDataSource);

        boolean hasSlave = slaveDataSource != null;
        if (hasSlave) {
            targetDataSources.put(DynamicDataSourceType.SLAVE, slaveDataSource);
        }

        dynamicRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        dynamicRoutingDataSource.setTargetDataSources(targetDataSources);
        dynamicRoutingDataSource.afterPropertiesSet();

        DynamicDataSourceContextHolder.setHasSlave(hasSlave);
        return dynamicRoutingDataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
