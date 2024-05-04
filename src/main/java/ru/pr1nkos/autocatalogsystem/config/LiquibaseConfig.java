package ru.pr1nkos.autocatalogsystem.config;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class LiquibaseConfig {

	@SneakyThrows
	public void runLiquibaseUpdate() {
		Properties properties = loadProperties();
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		String changeLogFile = properties.getProperty("changeLogFile");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			Liquibase liquibase = new Liquibase(changeLogFile, new ClassLoaderResourceAccessor(), new JdbcConnection(connection));
			liquibase.update(new Contexts(), new LabelExpression());
		} catch (LiquibaseException e) {
			e.printStackTrace();
		}
	}

	@SneakyThrows
	private Properties loadProperties() {
		Properties properties = new Properties();
		properties.load(getClass().getClassLoader().getResourceAsStream("liquibase.properties"));
		return properties;
	}
}

