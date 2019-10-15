package com.lhm.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestPreparedStatement {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        //prepareStatement能防止SQL注入攻击，能够批量处理SQL语句
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://127.0.0.1:8017/db_briup";
            String user = "root";
            String passwd = "root";

            conn = DriverManager.getConnection(url, user, passwd);

            //将事务的提交方式改成手动提交
            conn.setAutoCommit(false);

            //向PreparedStatement对象，需要提供预处理的sql语句
            //向s_emp表中插入数据，id，last_name，salary
            String sql = "insert into s_emp(id,last_name,salary) value(?,?,?)";

            ps = conn.prepareStatement(sql);

            //给占位符赋予真实值
            ps.setInt(1,300);
            ps.setString(2,"lhm");
            ps.setDouble(3,12345);

            //执行SQL语句,返回值表示此SQL语句执行之后影响了数据库中的多少行数据
            int i = ps.executeUpdate();
            System.out.println(i);

            //将所有的SQL语句先添加到缓冲区中
            //            ///ps.addBatch();

            //因为已经修改了事务提交方式，必须手动提交
            conn.commit();
        }catch (Exception e){
            //捕获异常，将事务回滚,那么必须将事务提交方式改成手动提交
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            //关闭连接释放资源
            if (ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
