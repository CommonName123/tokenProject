package net.proselyte.jwtappdemo.repository;

import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.spatial.dialect.postgis.PostgisPG93Dialect;
import org.hibernate.type.StandardBasicTypes;

public class CustomPostgreSQL93Dialect extends PostgisPG93Dialect {
    public CustomPostgreSQL93Dialect() {
        super();
        registerHibernateType(1111, "pg-uuid");
        registerFunction("replace", new SQLFunctionTemplate(StandardBasicTypes.STRING, "replace(?1, ?2, ?3)"));
    }
}
