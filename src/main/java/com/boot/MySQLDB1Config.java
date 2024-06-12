package com.boot;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.boot.mysql", entityManagerFactoryRef = "mySqlEntityManager", transactionManagerRef = "mySqlTransactionManager")
public class MySQLDB1Config {

	@Bean
	@ConfigurationProperties(prefix = "mysql.datasource")
	public DataSource createMySQLDS() {
		DataSourceBuilder.create().build();
		return new DriverManagerDataSource();
	}

	@Bean(name = "mySqlEntityManager")
	public LocalContainerEntityManagerFactoryBean createEMFactory(EntityManagerFactoryBuilder builder) {
		Map<String, Object> props = new HashMap<>();
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.put("hibernate.hbm2ddl.auto", "update");
		return builder.dataSource(createMySQLDS()).properties(props).packages("com.boot.mysql").build();
	}

	@Bean(name = "mySqlTransactionManager")
	public JpaTransactionManager createTxMgmr(@Qualifier("mySqlEntityManager") EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
	}
}
