package com.fabricetas.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.rits.cloning.Cloner;

/** It is responsible for call stored procedure and functions
 * Useful service to fetch dependencies
 * Created on 24/04/2017.
 * @author belman
 */
@Service
public class CallService<T> {

    @Autowired
    private DataSource dataSource;

    public Collection<T> callProcedure(
            T returnType, String procedureName, Collection<?> arguments, Collection<String> parameters) {

        Collection<T> returnProcedure = Lists.newArrayList();

        String argumentQuantity = arguments.isEmpty()? "" : "?";
        int quantityArgs = arguments.size();

        while(quantityArgs-- > 1)
            argumentQuantity += ",?";

        try {
            Connection connection = dataSource.getConnection();
            connection.createStatement().execute("USE fabricetas");
            CallableStatement callableStatement = connection.prepareCall("{call "+procedureName+"("+argumentQuantity+")}");

            for(int index=1; index<=arguments.size(); index++)
                callableStatement.setObject(index, ((List<?>)arguments).get(index-1));

            ResultSet result = callableStatement.executeQuery();

            while(result.next()){
                for(String par : parameters) {
                    String setField = "set" + par.substring(0, 1).toUpperCase() + par.substring(1);
                    try {
                        returnType.getClass().getMethod(setField, String.class)
                            .invoke(returnType, result.getString(par));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }

                returnProcedure.add(new Cloner().deepClone(returnType));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnProcedure;
    }

}
