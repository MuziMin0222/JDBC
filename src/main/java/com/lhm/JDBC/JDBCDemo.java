package com.lhm.JDBC;

import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //驱动版8.0.17，驱动类的类全名：com.mysql.cj.jdbc.Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://127.0.0.1:8017/db_briup";
        String user = "root";
        String passwd = "root";

        Connection connection = DriverManager.getConnection(url, user, passwd);

        Statement statement = connection.createStatement();

        String sql = "select last_name,salary from s_emp";
//        statement.executeQuery()    //resultset，用于执行DQL
//        statement.executeUpdate()      //int 执行DML
//        statement.execute()             //boolean，执行DDL，该方法的返回值指的是SQL语句
        ResultSet rs = statement.executeQuery(sql);

        String name;
        double salary;
        while (rs.next()){
            //如果字段有别名，在获取值时必须使用别名获取
            name = rs.getString("last_name");
//            name = rs.getString(1);   //尽量使用别名，而不是用索引
            salary = rs.getDouble("salary");
            System.out.println(name + ";;;" + salary);
        }

        rs.close();
        statement.close();
        connection.close();
    }
}
