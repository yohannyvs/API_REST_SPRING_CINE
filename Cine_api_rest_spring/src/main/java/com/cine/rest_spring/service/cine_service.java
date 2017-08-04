
package com.cine.rest_spring.service;
import java.sql.*;

public class cine_service
{
       
    public int login(String user, String pass) throws ClassNotFoundException, SQLException
    {
        Connection cn = conectar.con();
        CallableStatement cs = null;
    
        cs= cn.prepareCall("{call login (?,?)}");
        
        cs.setString(1, user);
        cs.setString(2, pass);
        
        ResultSet rs = cs.executeQuery();
        int v = 0;
        
        while (rs.next()) 
        {
            v = Integer.parseInt(rs.getString(1).toString());
        }

        return v;
    }
    
}
