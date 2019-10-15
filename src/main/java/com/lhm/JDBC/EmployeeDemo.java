package com.lhm.JDBC;

import java.sql.*;
import java.util.*;
import java.util.function.Consumer;

public class EmployeeDemo {
    private static List<Employee> list = new ArrayList<>();

    public static void MySQLOperation() throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //获取连接
        String url = "jdbc:mysql://127.0.0.1:8017/db_briup";
        String user = "root";
        String passwd = "root";
        Connection connection = DriverManager.getConnection(url, user, passwd);

        //获取statement对象
        Statement statement = connection.createStatement();

        String sql = "select last_name,salary from s_emp";
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()){
            String last_name = rs.getString("last_name");
            float salary = rs.getFloat("salary");
            Employee employee = new Employee();
            employee.setLast_name(last_name);
            employee.setSalary(salary);
            list.add(employee);
        }

        rs.close();
        statement.close();
        connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        EmployeeDemo.MySQLOperation();

        list.forEach(employee -> {
            String last_name = employee.getLast_name();
            float salary = employee.getSalary();
            System.out.println(last_name + "===" + salary);
        });
    }
}
