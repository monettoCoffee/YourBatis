package com.monetto.dao;

import com.monetto.entry.User;
import com.monetto.framework.orm.SqlSessionDaoSupport;
import com.monetto.framework.orm.SqlSessionFactory;

/**
 * @author monetto
 */
public class SqlDao extends SqlSessionDaoSupport {
    public SqlDao(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }

    public User getUserByName(String name){
        return this.getSqlSession().selectOne("getUserByName", name);
    }
}
