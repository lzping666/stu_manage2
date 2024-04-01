package com.liao.view;

import com.liao.dao.UserDao;
import com.liao.pojo.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class AdminLoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    UserDao userDao = new UserDao();

    public AdminLoginGUI() {
        setTitle("学生管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null); // 居中显示

        // 加载自定义图标

        InputStream inputStream = AdminLoginGUI.class.getClassLoader().getResourceAsStream("img/student.png");
        if (inputStream != null) {
            try {
                // 从输入流中读取图片
                Image image = ImageIO.read(inputStream);
                // 创建ImageIcon
                ImageIcon icon = new ImageIcon(image);
                // 现在你可以使用icon了，例如将其设置为JLabel的图标

                setIconImage(icon.getImage());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 关闭输入流
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("无法找到图片资源");
        }


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JLabel titleLabel = new JLabel("管理员登录", SwingConstants.CENTER);
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        panel.add(titleLabel);

        // 加载并显示图片


        inputStream = AdminLoginGUI.class.getClassLoader().getResourceAsStream("img/stu.png");
        if (inputStream != null) {
            try {
                // 从输入流中读取图片
                Image image = ImageIO.read(inputStream);
                // 创建ImageIcon
                ImageIcon logoIcon = new ImageIcon(image);
                // 现在你可以使用icon了，例如将其设置为JLabel的图标
                JLabel logoLabel = new JLabel(logoIcon);
                panel.add(logoLabel); // 将图片添加到面板
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 关闭输入流
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("无法找到图片资源");
        }

        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setFont(new Font("宋体", Font.PLAIN, 16));
        usernameField = new JTextField(14);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        panel.add(usernamePanel);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(new Font("宋体", Font.PLAIN, 16));
        passwordField = new JPasswordField(14);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        panel.add(passwordPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 5)); // 增加水平间距
        JButton loginButton = new JButton("登录");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                User user = userDao.searchUser(username,password);

                if(user != null){
                    setVisible(false);
                    dispose(); // 释放登录界面资源

                    // 创建并显示主界面
                    MainGUI mainGUI = new MainGUI();
                    mainGUI.setVisible(true);
                }else {
                    // 登录验证失败，弹出提示框
                    JOptionPane.showMessageDialog(null, "账号或密码错误，请重新输入！", "登录失败", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton exitButton = new JButton("退出");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // 退出程序
            }
        });
        buttonPanel.add(loginButton);
        buttonPanel.add(exitButton);
        panel.add(buttonPanel);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminLoginGUI();
            }
        });
    }
}





