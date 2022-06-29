package com.alura.hotel.factories;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import javax.swing.JOptionPane;

import com.alura.hotel.config.Config;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	private DataSource dataSource;

	public ConnectionFactory() {
		String jdbcUrl = String.format("jdbc:postgresql://localhost:%s/%s?useTimeZone=true&serverTimeZone=UTC",
				Config.getValue("dbPort"), Config.getValue("dbName"));

		ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl(jdbcUrl);
		pooledDataSource.setUser(Config.getValue("dbUsername"));
		pooledDataSource.setPassword(Config.getValue("dbPassword"));
		pooledDataSource.setMaxPoolSize(10);

		this.dataSource = pooledDataSource;
	}

	public Connection getConnection() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se pudo inicializar el programa ya que no se encontró la base de datos o el nombre de usuario y contraseña son incorrectos.", "Error crítico", JOptionPane.ERROR_MESSAGE);
			
			System.exit(0);
			
			return null;
		}
	}
}
