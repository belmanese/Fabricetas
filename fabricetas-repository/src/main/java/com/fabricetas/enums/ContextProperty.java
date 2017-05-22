package com.fabricetas.enums;

/**
 * Created by belman on 08/04/2017.
 */
public enum ContextProperty {

    // For Database Connection
    DB_DRIVER_CLASS_NAME("jdbc.driverClassName"),
    DB_URL("jdbc.url"),
    DB_USERNAME("jdbc.username"),
    DB_PASSWORD("jdbc.password"),
    HIBERNATE_DIALECT("hibernate.dialect"),
    HIBERNATE_SHOW_SQL("hibernate.show_sql"),
    HIBERNATE_FORMAT_SQL("hibernate.format_sql"),

    //For Context Path
    PATH_COMPONENT_SCAN("com.fabricetas"),
    PATH_ENTITY_SCAN("com.fabricetas.domain"),
    PATH_JPA_REPOSITORIES("com.fabricetas.repos"),
    CLASSPATH_HIBERNATE_PROPERTIES("classpath:hibernate.properties");

    private String propertie;

    ContextProperty(String propertie) {
        this.propertie = propertie;
    }

    public String getPropertie(){ return this.propertie; }

}
