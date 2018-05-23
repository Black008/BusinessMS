package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBconnection {

    private static Connection con;
    public static Connection getDb() throws SQLException, ClassNotFoundException, IOException {
        if(con == null){
            // 加载properties文件
            InputStream is = DBconnection.class.getResourceAsStream("/db.properties");
            Properties properties = new Properties();
            properties.load(is);
            String dbDriver = properties.getProperty("dbDriver");
            String dburl =  properties.getProperty("dburl");
            String dbUser =  properties.getProperty("dbUser");
            String dbPwd =  properties.getProperty("dbPwd");
            Class.forName(dbDriver);
            con = DriverManager.getConnection(dburl,dbUser,dbPwd);
        }
        return con;
    }

    public static void closeConn(){
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
