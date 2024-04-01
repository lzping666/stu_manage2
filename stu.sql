-- 创建数据库
CREATE DATABASE IF NOT EXISTS student_management_system;

-- 切换到创建的数据库
USE student_management_system;

-- 创建用户表
CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

INSERT INTO user (username, password) VALUES ('lzpnb', '666');


-- 创建学生表
CREATE TABLE IF NOT EXISTS student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    birthDate DATE NOT NULL,
    studentId VARCHAR(20) NOT NULL,
    qqNumber VARCHAR(20) NOT NULL
);

INSERT INTO student (name, gender, birthDate, studentId, qqNumber) VALUES ('张三', '男', '1990-01-01', '1001', '123456');
INSERT INTO student (name, gender, birthDate, studentId, qqNumber) VALUES ('李四', '女', '1991-02-02', '1002', '234567');
INSERT INTO student (name, gender, birthDate, studentId, qqNumber) VALUES ('王五', '男', '1992-03-03', '1003', '345678');

