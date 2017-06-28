/*
 * (C) 2013 42 bv (www.42.nl). All rights reserved.
 */
package nl.koekoek;

import javax.sql.DataSource;

import nl.koekoek.n2.api.AccountConfig;

import org.hibernate.dialect.PostgreSQL82Dialect;
import org.jarbframework.migrations.MigratingDataSource;
import org.jarbframework.migrations.liquibase.LiquibaseMigrator;
import org.jarbframework.populator.DatabasePopulator;
import org.jarbframework.populator.PopulateApplicationListener;
import org.jarbframework.populator.PopulateApplicationListenerBuilder;
import org.jarbframework.utils.orm.hibernate.ImprovedHsqlDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestApplicationConfig {
    
    @Bean
    public AccountConfig accountConfig() {
        AccountConfig accountConfig = new AccountConfig();
        return accountConfig;
    }

    private static MigratingDataSource buildMigratingDataSource(DataSource delegate) {
        LiquibaseMigrator databaseMigrator = new LiquibaseMigrator();
        databaseMigrator.setChangeLogPath("src/main/database/changelog.groovy");
        return new MigratingDataSource(delegate, databaseMigrator);
    }

    @Configuration
    @Profile("in-memory-db")
    public static class InMemoryTestConfig {
        
        @Bean
        public DataSource dataSource() {
            org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
            dataSource.setDriverClassName(org.hsqldb.jdbcDriver.class.getName());
            dataSource.setUrl("jdbc:hsqldb:mem:koekoek");
            dataSource.setUsername("sa");
            dataSource.setPassword("");
            return buildMigratingDataSource(dataSource);
        }
        
        @Bean
        public String hibernateDialect() {
            return ImprovedHsqlDialect.class.getName();
        }
        
    }
    
    @Configuration
    @Profile("sample-data")
    public static class SampleDataTestConfig {

        @Bean
        public PopulateApplicationListener populateAppliationListener() {
            return new PopulateApplicationListenerBuilder()
                        .initializer()
                            .add(databasePopulator())
                        .build();
        }
        
        @Bean
        public DatabasePopulator databasePopulator() {
            return new IntegrationDatabasePopulator();
        }
        
    }
    
    @Configuration
    @Profile("postgres-db")
    public static class PostgresTestConfig {
        
        @Bean
        public DataSource dataSource() {
            org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
            dataSource.setDriverClassName(org.postgresql.Driver.class.getName());
            dataSource.setUrl("jdbc:postgresql://localhost:5432/koekoek");
            dataSource.setUsername("postgres");
            dataSource.setPassword("root");
            return buildMigratingDataSource(dataSource);
        }
        
        @Bean
        public String hibernateDialect() {
            return PostgreSQL82Dialect.class.getName();
        }
        
    }

}
