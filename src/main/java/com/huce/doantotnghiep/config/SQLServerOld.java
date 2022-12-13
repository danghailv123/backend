package com.huce.doantotnghiep.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "oldEntityManagerFactory",
        transactionManagerRef = "oldTransactionManager",
        basePackages = {"com.huce.doantotnghiep.layer.application.domain.dao.old"}
)
public class SQLServerOld {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "sqlserver.datasource.old")
    public DataSourceProperties dataSourceOldProperties() {
        return new DataSourceProperties();
    }

    @Primary
    public DataSource getDataSourceOld() {
        return dataSourceOldProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "oldEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(getDataSourceOld()).packages("com.huce.doantotnghiep.layer.application.domain.entity.old")
                .build();
    }

    @Bean(name = "oldTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(
            final @Qualifier("oldEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryBean.getObject()));
    }

}
