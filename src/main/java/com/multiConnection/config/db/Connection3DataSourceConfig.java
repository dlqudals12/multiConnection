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
        basePackages = "com.multiConnection.data.repository.connection3",
        entityManagerFactoryRef = "connection3EntityManagerFactory",
        transactionManagerRef = "connection3TransactionManager"
)
public class Connection3DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.connection3-datasource")
    public DataSourceProperties connection3DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.connection3-datasource.configuration")
    public DataSource connection3Datasource() {
        return connection3DataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "connection3EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean connection3EntityManagerFactory(EntityManagerFactoryBuilder builder) {
        DataSource dataSource = connection3Datasource();
        return builder
                .dataSource(dataSource)
                .packages("com.multiConnection.data.model.entity.connection3")
                .persistenceUnit("connection3EntityManager")
                .properties(
                        Map.of("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy"
                        )
                )
                .build();
    }

    @Bean(name = "connection3TransactionManager")
    public PlatformTransactionManager connection3TransactionManager(
            final @Qualifier("connection3EntityManagerFactory") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean
    ) {
        return new JpaTransactionManager(Objects.requireNonNull(localContainerEntityManagerFactoryBean.getObject()));
    }
}
