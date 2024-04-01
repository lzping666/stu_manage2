package com.liao.dao;

/**
 * @author lzp
 * @version 1.0
 * lzpnb!
 */
import com.liao.pojo.Student;
import com.liao.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentDao {


    List<Student> studentList = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public List<Student> searchStudent(String keyword) {

        try {
            conn = DBUtil.getConnection();
            studentList.clear();
            String sql = "SELECT * FROM student WHERE name LIKE ?  OR gender LIKE ? OR studentId LIKE ? OR qqNumber LIKE ?";
            stmt = conn.prepareStatement(sql);
            //stmt.setString(1, "%" + keyword + "%");
            String likeKeyword = "%" + keyword + "%";
            for (int i = 1; i <= 4; i++) {
                stmt.setString(i, likeKeyword);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                Date birthDate = rs.getDate("birthDate");
                String studentId = rs.getString("studentId");
                String qqNumber = rs.getString("qqNumber");

                Student student = new Student(id, name, gender, birthDate, studentId, qqNumber);
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }

        return studentList;
    }

    public void addStudent(Student student) {

        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO student (name, gender, birthDate, studentId, qqNumber) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getGender());
            stmt.setDate(3, new Date(student.getBirthDate().getTime()));
            stmt.setString(4, student.getStudentId());
            stmt.setString(5, student.getQqNumber());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    public void updateStudent(Student student) {

        try {
            conn = DBUtil.getConnection();
            studentList.clear();
            String sql = "UPDATE student SET name=?, gender=?, birthDate=?, studentId=?, qqNumber=? WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getGender());
            stmt.setDate(3, new Date(student.getBirthDate().getTime()));
            stmt.setString(4, student.getStudentId());
            stmt.setString(5, student.getQqNumber());
            stmt.setInt(6, student.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    public boolean deleteStudent(int id) {
        boolean success = false; // 默认删除失败

        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM student WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int i = stmt.executeUpdate();
            if (i > 0) {
                success = true; // 删除成功
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt);

        }

        return success;
    }

    public List<Student> listStudent() {

        try {
            conn = DBUtil.getConnection();
            studentList.clear();
            String sql = "SELECT * FROM student";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                Date birthDate = rs.getDate("birthDate");
                String studentId = rs.getString("studentId");
                String qqNumber = rs.getString("qqNumber");

                Student student = new Student(id, name, gender, birthDate, studentId, qqNumber);
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }

        return studentList;
    }
}


