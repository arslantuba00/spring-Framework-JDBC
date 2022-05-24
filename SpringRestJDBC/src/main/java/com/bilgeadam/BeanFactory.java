package com.bilgeadam;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class BeanFactory {
	@Bean(name = "datasource")
	public DataSource dataSource() {
		// datasource = connection bilgileri
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/OBS");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres");
		return dataSource;
	}

	@Bean(name = "txManager")
	@DependsOn(value = "datasource")
	public DataSourceTransactionManager getManager(@Autowired @Qualifier(value = "datasource") DataSource ds) {
		return new DataSourceTransactionManager(ds);
	}

	@Bean(name = "jdbcTemplate")
	@DependsOn(value = "datasource")
	public JdbcTemplate jdbcTemplate(@Autowired @Qualifier(value = "datasource") DataSource ds) {
		return new JdbcTemplate(ds);
	}

	@Bean(name = "namedParameterJdbcTemplate")
	@DependsOn(value = "datasource")
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(
			@Autowired @Qualifier(value = "datasource") DataSource ds) {
		return new NamedParameterJdbcTemplate(ds);
	}
}