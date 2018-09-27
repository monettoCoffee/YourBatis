package com.monetto.framework.orm;

import com.monetto.framework.entry.Function;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;

/**
 * @author monetto
 */
public class Executor {
    private HashMap<String, Function> functions;
    private SqlSession sqlSessionProxy;

    public Executor(HashMap functions,SqlSession sqlSession){
        this.functions = functions;
        this.sqlSessionProxy = sqlSession;
    }

    public <T> T selectOne(String id,Object parameter,Connection connection){
        Function function = this.functions.get(id);
        String resultType = function.getResultType();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(function.getSql());
            preparedStatement.setString(1,parameter.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            Object resultObject = Class.forName(resultType).newInstance();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            resultSet.next();
            int allValueNum = resultSetMetaData.getColumnCount();
            for(int index = 1 ;index <= allValueNum ;){
                String s = resultSetMetaData.getColumnName(index++);
                Class clazz = ((T)resultObject).getClass();
                Field nameField = clazz.getDeclaredField(s);
                nameField.setAccessible(true);
                nameField.set(resultObject, resultSet.getString(s));
            }
            this.sqlSessionProxy.setUse(false);
            return (T)resultObject;
        } catch (Exception e){
            e.printStackTrace();
            this.sqlSessionProxy.setUse(false);
            return null;
        }

    }

}
