package com.boot;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.boot.oracle", entityManagerFactoryRef = "oracleEntityManagerFactory", transactionManagerRef = "oracleTransactionManager")
public class OracleDB2Config {

	@Bean
	@ConfigurationProperties(prefix = "oracle.datasource")
	@Primary
	DataSource createOraDs() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "oracleEntityManagerFactory")
	@Primary
	LocalContainerEntityManagerFactoryBean createOraEntityFactory(EntityManagerFactoryBuilder builder) {
		Map<String, String> props = new HashMap<>();
		props.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		props.put("hibernate.hbm2ddl.auto", "update");
		return builder.dataSource(createOraDs()).packages("com.boot.oracle").properties(props).build();

	}

	@Bean(name = "oracleTransactionManager")
	@Primary
	JpaTransactionManager createOraTxMgmr(EntityManagerFactory emFactory) {
		return new JpaTransactionManager(emFactory);
	}

}
