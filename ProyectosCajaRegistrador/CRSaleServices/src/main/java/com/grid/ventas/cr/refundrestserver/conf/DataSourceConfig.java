/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.grid.ventas.cr.refundrestserver.conf;

import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_PASSWORD;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_URL;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_USER;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;

@Configuration
// @ConfigurationProperties(prefix = "localStore")
// public class DataSourceConfig extends HikariConfig {
// public class DataSourceConfig extends JpaBaseConfiguration {
public class DataSourceConfig extends JpaBaseConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    private static HashMap<String, Object> properties400;

    @Value("${spring.datasource.username}")
    private String dbUserName;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.schema}")
    private String dbSchema;

    @Value("#{'${spring.datasource.url}'.split(';')}")
    private List<String> dbURL;

    @Value("${spring.jpa.database}")
    private String dataBase;

    @Bean
    public LocalEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalEntityManagerFactoryBean lemfb = new LocalEntityManagerFactoryBean();

        logger.info("dbURL -> " + dbURL);
        logger.info("dbUserName -> " + dbUserName);
        properties400 = new HashMap<String, Object>();
        if (dataBase.equalsIgnoreCase("DB2")) {
            // Para DB2 debo pasar el esquema
            properties400.put(JDBC_URL, dbURL.get(0) + "/" + dbSchema);
        } else {
            // Para MySQL no es necesario pasar el esquema
            properties400.put(JDBC_URL, dbURL.get(0));
        }
        properties400.put(JDBC_USER, dbUserName);
        properties400.put(JDBC_PASSWORD, dbPassword);
        lemfb.setJpaPropertyMap(properties400);
        lemfb.setPersistenceUnitName("CR400PU");
        return lemfb;
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        EclipseLinkJpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();
        return adapter;
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        return map;
    }

    public static HashMap<String, Object> getProperties400() {
        return properties400;
    }

    public static void setProperties400(HashMap<String, Object> properties400) {
        DataSourceConfig.properties400 = properties400;
    }

}