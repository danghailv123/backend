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
        entityManagerFactoryRef = "newEntityManagerFactory",
        transactionManagerRef = "newTransactionManager",
        basePackages = {"com.huce.doantotnghiep.layer.application.domain.dao.last"}
)
public class SQLServerNew {
    @Bean
    @ConfigurationProperties(prefix = "sqlserver.datasource.new")
    public DataSourceProperties dataSourceNewProperties() {
        return new DataSourceProperties();
    }

    public DataSource getDataSourceNew() {
        return dataSourceNewProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "newEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(getDataSourceNew()).packages("com.huce.doantotnghiep.layer.application.domain.entity.last")
                .build();
    }

    @Bean(name = "newTransactionManager")
    public PlatformTransactionManager transactionManager(
            final @Qualifier("newEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryBean.getObject()));
    }

}
