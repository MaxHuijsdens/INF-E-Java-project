/*
 * (C) 2013 42 bv (www.42.nl). All rights reserved.
 */
package nl.koekoek;

import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.sql.DataSource;
import javax.validation.Validator;

import nl.koekoek.n2.DataStorer;
import nl.koekoek.n2.api.AccountConfig;
import nl.koekoek.support.JndiBeanLocator;

import org.hibernate.ejb.HibernatePersistence;
import org.jarbframework.constraint.EnableDatabaseConstraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan(
        basePackageClasses = ApplicationConfig.class,
        excludeFilters = { @Filter(Controller.class), @Filter(value = WebMvcConfig.class, type = ASSIGNABLE_TYPE) })
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@EnableScheduling
@EnableDatabaseConstraints(baseClasses = ApplicationConfig.class, entityManagerFactory = "entityManagerFactory")
@EnableJpaRepositories(basePackageClasses = ApplicationConfig.class)
public class ApplicationConfig implements SchedulingConfigurer {

    @Autowired
    private DataSource dataSource;

    @Autowired
    @Qualifier("hibernateDialect")
    private String hibernateDialect;

    @Autowired
    private AccountConfig accountConfig;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataStorer dataStorer() {
        return new DataStorer(accountConfig);
    }

    @Bean
    public JpaDialect jpaDialect() {
        return new HibernateJpaDialect();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
        entityManagerFactoryBean.setPackagesToScan("nl.koekoek");
        entityManagerFactoryBean.setJpaDialect(jpaDialect());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", hibernateDialect);
        jpaProperties.put("hibernate.hbm2ddl.auto", "validate");
        jpaProperties.put("hibernate.ejb.naming_strategy", nl.koekoek.support.CustomNamingStrategy.class.getName());
        jpaProperties.put("hibernate.jdbc.use_get_generated_keys", true);
        jpaProperties.put("hibernate.generate_statistics", true);
        jpaProperties.put("hibernate.show_sql", false);
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        transactionManager.setJpaDialect(jpaDialect());
        return transactionManager;
    }

    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.afterPropertiesSet();
        return validatorFactoryBean.getValidator();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }

    @Configuration
    @Profile("default")
    public static class ProductionApplicationConfig {
        
        public static final String DATA_SOURCE_JNDI_NAME = "java:comp/env/postgres/jdbc";
        public static final String HIBERNATE_DIALECT_JNDI_NAME = "java:comp/env/koekoek/hibernate-dialect";
        public static final String N2_JNDI_NAME = "java:comp/env/n2";

        private final JndiBeanLocator jndiBeanLocator;
        
        public ProductionApplicationConfig() {
            this(new JndiBeanLocator());
        }
        
        public ProductionApplicationConfig(JndiBeanLocator jndiBeanLocator) {
            this.jndiBeanLocator = jndiBeanLocator;
        }

        /**
         * Create a new DataSource from the JNDI name.
         *
         * @return Datasource
         */
        @Bean
        public DataSource dataSource() {
            return (DataSource) jndiBeanLocator.lookupBean(DATA_SOURCE_JNDI_NAME);
        }

        @Bean
        public String hibernateDialect() {
            return (String) jndiBeanLocator.lookupBean(HIBERNATE_DIALECT_JNDI_NAME);
        }

        /**
         * Return account config retrieved from JNDI property.
         *
         * @return account config
         */
        @Bean
        public AccountConfig accountConfig() {
            return (AccountConfig) jndiBeanLocator.lookupBean(N2_JNDI_NAME);
        }

    }

}
