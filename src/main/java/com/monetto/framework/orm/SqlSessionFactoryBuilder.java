package com.monetto.framework.orm;

/**
 * @author monetto
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactory.getInstance();
        sqlSessionFactory.build();
        return sqlSessionFactory;
    }
}
