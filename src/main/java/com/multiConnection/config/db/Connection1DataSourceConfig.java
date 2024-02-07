package com.multiConnection.config.db;

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
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.multiConnection.data.repository.connection1",
        entityManagerFactoryRef = "connection1EntityManagerFactory",
        transactionManagerRef = "connection1TransactionManager"
)
public class Connection1DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties connection1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.configuration")
    public DataSource commonDatasource() {
        return connection1DataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "connection1EntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean connection1EntityManagerFactory(EntityManagerFactoryBuilder builder) {
        DataSource dataSource = commonDatasource();
        return builder
                .dataSource(dataSource)
                .packages("com.multiConnection.data.model.entity.connection1")
                .persistenceUnit("connection1EntityManager")
                .properties(
                        Map.of("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy"
                        )
                )
                .build();
    }

    @Bean(name = "connection1TransactionManager")
    @Primary
    public PlatformTransactionManager connection1TransactionManager(
            final @Qualifier("connection1EntityManagerFactory") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean
    ) {
        return new JpaTransactionManager(Objects.requireNonNull(localContainerEntityManagerFactoryBean.getObject()));
    }
}
