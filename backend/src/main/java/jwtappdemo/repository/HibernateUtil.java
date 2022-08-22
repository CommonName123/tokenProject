package jwtappdemo.repository;

import javax.persistence.Query;

import org.hibernate.query.NativeQuery;

public class HibernateUtil {
    static public <T> Query aliasToBean(Query query, Class<T> classBean){
        return query.unwrap(NativeQuery.class).setResultTransformer(new HibernateResultTransformer(classBean));
    }
}
