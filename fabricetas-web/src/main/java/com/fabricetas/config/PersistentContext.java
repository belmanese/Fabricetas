package com.fabricetas.config;

import java.util.Properties;

import javax.sql.DataSource;

import com.fabricetas.enums.ContextProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.fabricetas")
@EnableJpaRepositories("com.fabricetas.repos")
@PropertySource("classpath:hibernate.properties")
public class PersistentContext {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(
			environment.getRequiredProperty(ContextProperty.DB_DRIVER_CLASS_NAME.getPropertie()));
		dataSource.setUrl(
			environment.getRequiredProperty(ContextProperty.DB_URL.getPropertie()));
		dataSource.setUsername(
			environment.getRequiredProperty(ContextProperty.DB_USERNAME.getPropertie()));
		dataSource.setPassword(
			environment.getRequiredProperty(ContextProperty.DB_PASSWORD.getPropertie()));
		return dataSource;
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan(ContextProperty.PATH_ENTITY_SCAN.getPropertie());

		Properties jpaProperties = new Properties();
		jpaProperties.put(
			ContextProperty.HIBERNATE_DIALECT.getPropertie(),
			environment.getRequiredProperty(ContextProperty.HIBERNATE_DIALECT.getPropertie()));
		jpaProperties.put(
			ContextProperty.HIBERNATE_SHOW_SQL.getPropertie(),
			environment.getRequiredProperty(ContextProperty.HIBERNATE_SHOW_SQL.getPropertie()));
		jpaProperties.put(
			ContextProperty.HIBERNATE_FORMAT_SQL.getPropertie(),
			environment.getRequiredProperty(ContextProperty.HIBERNATE_FORMAT_SQL.getPropertie()));

		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;

	}

	@Bean public PlatformTransactionManager transactionManager() {

		JpaTransactionManager txManager= new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory(dataSource()).getObject());
		return txManager;

	}

/* Configuration for Hibernate

    @Autowired
    private Environment environment;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.fabricetas.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl("jdbc:mysql://localhost:3306/FABRICETAS");
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		return dataSource;
	}

	private ContextProperty hibernateProperties() {
		ContextProperty properties = new ContextProperty();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}*/
	/*
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/FABRICETAS");
		dataSource.setUsername("root");
		dataSource.setPassword("admin");
//		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
//		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
//		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
//		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));

		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.format_sql", "false");
//		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
//		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
//		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClasses(User.class);
		sessionBuilder.addAnnotatedClasses(Address.class);
		sessionBuilder.addAnnotatedClasses(Role.class);
		sessionBuilder.addAnnotatedClasses(Text.class);
		sessionBuilder.addAnnotatedClasses(Theme.class);
		sessionBuilder.addAnnotatedClasses(Tshirt.class);
		sessionBuilder.addAnnotatedClasses(Stamp.class);
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				sessionFactory);

		return transactionManager;
	}

	@Autowired
	@Bean(name = "userDaoBackup")
	public UserDaoBackup getUserDao(SessionFactory sessionFactory) {
		return new UserDaoImplBackup(sessionFactory);
	}
*/
}
