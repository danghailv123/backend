package com.huce.doantotnghiep.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "processEntityManagerFactory",
        transactionManagerRef = "processTransactionManager",
        basePackages = {"com.huce.doantotnghiep.layer.application.domain.dao.process"}
)
public class SQLServerProcess {
    @Bean
    @ConfigurationProperties(prefix = "sqlserver.datasource.process")
    public DataSourceProperties dataSourceProcessProperties() {
        return new DataSourceProperties();
    }

    public DataSource getDataSourceProcess() {
        return dataSourceProcessProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "processEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(getDataSourceProcess()).packages("com.huce.doantotnghiep.layer.application.domain.entity.process")
                .build();
    }

    @Bean(name = "processTransactionManager")
    public PlatformTransactionManager transactionManager(
            final @Qualifier("processEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryBean.getObject()));
    }

}
