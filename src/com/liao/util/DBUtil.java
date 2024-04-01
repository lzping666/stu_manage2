package com.liao.util;

/**
 * @author lzp
 * @version 1.0
 * lzpnb!
 */
import java.sql.*;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/student_management_system?serverTimezone=GMT%2B8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Database connection error: " + e.getMessage());
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection connection, PreparedStatement stmt) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection connection, PreparedStatement stmt, ResultSet rs) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();
            if (connection != null) {
                System.out.println("成功获取数据库连接！");
            } else {
                System.out.println("无法获取数据库连接！");
            }
        } catch (SQLException e) {
            System.out.println("获取数据库连接失败：" + e.getMessage());
        } finally {
            // 释放数据库连接
            DBUtil.closeConnection(connection);
        }
    }
}

