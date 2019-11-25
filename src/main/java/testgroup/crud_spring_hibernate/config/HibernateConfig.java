package testgroup.crud_spring_hibernate.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "testgroup.crud_spring_hibernate")
@EnableTransactionManagement
@PropertySource(value = "classpath:DBProperties.properties")
@EnableWebMvc
public class HibernateConfig {
    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {

        this.environment = environment;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("show_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hbm2ddl.auto"));
        return properties;
    }

//        @Bean
    private DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
// Здесь у меня было BasicDataSource
        dataSource.setDriverClassName(environment.getRequiredProperty("driver.class"));
        dataSource.setUrl(environment.getRequiredProperty("connection.url"));
        dataSource.setUsername(environment.getRequiredProperty("username"));
        dataSource.setPassword(environment.getRequiredProperty("password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("testgroup.crud_spring_hibernate.model");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaProperties(hibernateProperties());
        return emf;
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }
}
