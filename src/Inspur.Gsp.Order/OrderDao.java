/*
* EntityName: 销售订单
* EntityDescription: 销售订单模型
* */

package Inspur.Gsp.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDao
{
    private static ArrayList<String> sqls = new ArrayList<>();
    
    public OrderDao() throws Exception
    {
        init();
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver Load Properly!");

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try
        {
            String url = "jdbc:mysql://localhost:3306/";
            connection = DriverManager.getConnection(url);
            System.out.println("Database Connected!");

            statement = connection.createStatement();
            for(String sql:sqls)
            {
                resultSet = statement.executeQuery(sql);

                resultSet.beforeFirst();
                while (resultSet.next())
                {
                    System.out.println(resultSet.getString(1));
                }
                System.out.println("Valid Operation!");
            }
        } catch(Throwable t)
        {
            // TODO Handle Exception
            t.printStackTrace();
        } finally
        {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
            System.out.println("Close Properly!");
        }
    }

    private static void handleSQL(String sql) throws Exception
    {
        handleSQL(sql, null);
    }

    private static void handleSQL(String sql, String queryField) throws Exception
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try
        {
            String url = "jdbc:mysql://localhost:3306/";
            connection = DriverManager.getConnection(url);
            System.out.println("Database Connected!");

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSet.beforeFirst();
            while (resultSet.next())
            {
                if (queryField != null)
                    System.out.println(resultSet.getString(queryField));
            }
            System.out.println("Valid Operation!");
        } catch(Throwable t)
        {
            // TODO Handle Exception
            t.printStackTrace();
        } finally
        {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
            System.out.println("Close Properly!");
        }
    }
    
    private void init()
    {
        sqls.add("CREATE TABLE IF NOT EXISTS GspOrder(ID VARCHAR(36) NOT NULL , Code VARCHAR(50) NOT NULL , Name VARCHAR(50) NOT NULL , Price DOUBLE(8, 2), OrderCount INT, OrderAmount DOUBLE(8, 2), Status INT, IsVip BOOL, CreateTime DATETIME, Demo VARCHAR(10))ENGINE=InnoDB DEFAULT CHARSET=utf8;");
        
    }
    
    public void insertOne(String ID, String Code, String Name) throws Exception
    {
        String insertSQL = "INSERT INTO GspOrder (ID, Code, Name) VALUES ";
        StringBuilder builder = new StringBuilder(insertSQL);
        builder.append("("+"\""+ID+"\", "+"\""+Code+"\", "+"\""+Name+"\","+");");
        handleSQL(builder.toString());
    }
    
    public void select(String queryField, String field, String value) throws Exception
    {
        String selectSQL = "SELECT * FROM GspOrder WHERE "+field+"="+value+";";
        handleSQL(selectSQL, queryField);
    }
    
    public void updateOne(String queryField, String queryValue, String field, String value) throws Exception
    {
        String updateSQL = "UPDATE GspOrder SET "+queryField+"="+queryValue+"WHERE "+field+"="+value+";";
        handleSQL(updateSQL);
    }
    
    public void delete(String field, String value) throws Exception
    {
        String deleteSQL = "DELETE FROM GspOrder WHERE "+field+"="+value+";";
        handleSQL(deleteSQL);
    }
}