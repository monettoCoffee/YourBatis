package com.monetto.framework.orm;

import java.sql.Connection;

/**
 * @author monetto
 */
public class SqlSession {
    private SqlSessionFactory sqlSessionFactory;
    private Executor executor;
    private Connection connection;
    private boolean isUse = false;

    public SqlSession(SqlSessionFactory sqlSessionFactory,Connection connection,Boolean isUse){
        this.sqlSessionFactory = sqlSessionFactory;
        this.executor = this.sqlSessionFactory.getExecutor(this);
        this.connection = connection;
        this.isUse = isUse;
    }

    public <T> T selectOne(String id,Object parameter){
        this.isUse = true;
        return executor.selectOne(id,parameter,this.connection);
    }

    public Connection getConnection(){
        return this.connection;
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }

}
