package com.monetto;

import com.monetto.dao.MapperDao;
import com.monetto.dao.SqlDao;
import com.monetto.framework.orm.SqlSessionFactory;
import com.monetto.framework.orm.SqlSessionFactoryBuilder;

import java.lang.ref.WeakReference;

/**
 * @author monetto
 */
public class MainClass {
    public static void main(String[] args){
        WeakReference<SqlSessionFactoryBuilder> reference = new WeakReference<SqlSessionFactoryBuilder>(new SqlSessionFactoryBuilder());
        //使用弱引用创建SqlSessionFactoryBuilder,保证下次GC时回收该对象。
        SqlSessionFactory sqlSessionFactory = reference.get().build();
        // 接口动态代理调用
        MapperDao mapperDao = sqlSessionFactory.getMapper(MapperDao.class);
        // 指定 SQL ID 调用
        SqlDao sqlDao = new SqlDao(sqlSessionFactory);
        System.out.println(mapperDao.getUserByName("123").getPassword());
        System.out.println(sqlDao.getUserByName("234").getPassword());
    }
}