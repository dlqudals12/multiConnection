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
        basePackages = "com.multiConnection.data.repository.connection2",
        entityManagerFactoryRef = "connection2EntityManagerFactory",
        transactionManagerRef = "connection2TransactionManager"
)
public class Connection2DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.connection2-datasource")
    public DataSourceProperties connection2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.connection2-datasource.configuration")
    public DataSource connection2Datasource() {
        return connection2DataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "connection2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean connection2EntityManagerFactory(EntityManagerFactoryBuilder builder) {
        DataSource dataSource = connection2Datasource();
        return builder
                .dataSource(dataSource)
                .packages("com.multiConnection.data.model.entity.connection2")
                .persistenceUnit("connection2EntityManager")
                .properties(
                        Map.of("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy"
                        )
                )
                .build();
    }

    @Bean(name = "connection2TransactionManager")
    public PlatformTransactionManager connection2TransactionManager(
            final @Qualifier("connection2EntityManagerFactory") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean
    ) {
        return new JpaTransactionManager(Objects.requireNonNull(localContainerEntityManagerFactoryBean.getObject()));
    }
}
