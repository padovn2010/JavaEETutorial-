package com.duongpt.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.sql.DataSource;

/**
 *
 * @author Pado
 */
@Stateful
@LocalBean
public class AjaxDao {
    
    private final String sqlSelect = "select user_name from USERACCOUNT" ;
    private final List<String> userNameList;
    
    @Resource(lookup = "jdbc/__AccountDB")
    private DataSource dataSource;
    
    private Connection connection;
    
    public AjaxDao() {
        userNameList = new ArrayList<String>();
    }
    
    @SuppressWarnings("CallToPrintStackTrace")
    public List<String> getUserNameList(){
        
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            //prevent duplicating values to userNameList
            if (userNameList.isEmpty()){
                while (resultSet!=null && resultSet.next()){
                    userNameList.add(resultSet.getString(1));
                }
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userNameList;
    }
    
}
