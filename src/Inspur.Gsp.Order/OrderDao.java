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
    private static ArrayList<String> sqls;
    private static void init()
    {
        sqls.add("CREATE TABLE GspOrder(ID VARCHAR(36) NOT NULL , Code VARCHAR(50) NOT NULL , Name VARCHAR(50) NOT NULL , Price DOUBLE(8, 2), OrderCount INT, OrderAmount DOUBLE(8, 2), Status INT, IsVip BOOL, Date DATETIME)ENGINE=InnoDB DEFAULT CHARSET=utf8;");
    }
    public static void main(String[] args) throws Exception
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
}