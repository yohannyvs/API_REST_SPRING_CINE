
package com.cine.rest_spring.service;
import java.sql.*;

public class cine_service
{
       
    public void insert() throws ClassNotFoundException, SQLException
    {
        Connection cn = conectar.con();
        CallableStatement cs = null;
    
        cs= cn.prepareCall("");
    }
    
}
