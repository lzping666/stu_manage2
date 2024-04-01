package com.liao.view;

/**
 * @author lzp
 * @version 1.0
 * lzpnb!
 */

import com.liao.dao.StudentDao;
import com.liao.pojo.Student;
import com.liao.util.DateUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;


public class AddStudentDialog extends JDialog {
    private JTextField  nameField, genderField, birthDateField, studentIdField, qqNumberField;
    private JButton addButton, cancelButton;
    private DefaultTableModel tableModel;
    StudentDao studentDao = new StudentDao();

    public AddStudentDialog(JFrame parent, DefaultTableModel model) {
        super(parent, "新增学生信息", true);
        setSize(300, 250);
        setLocationRelativeTo(parent);

        this.tableModel = model;

        JPanel panel = new JPanel(new GridBagLayout()); // 使用GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();

        // 设置通用的GridBagConstraints
        gbc.insets = new Insets(2, 2, 2, 2); // 设置组件之间的间距
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.gridwidth = 1;

        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 16); // 设置文本框的字体


        nameField = new JTextField(10);
        nameField.setFont(font); // 设置文本框的字体
        addLabeledField(panel, "姓名:", nameField, gbc);

        genderField = new JTextField(10);
        genderField.setFont(font); // 设置文本框的字体
        addLabeledField(panel, "性别:", genderField, gbc);

        birthDateField = new JTextField(10);
        birthDateField.setFont(font); // 设置文本框的字体
        addLabeledField(panel, "出生日期:", birthDateField, gbc);

        studentIdField = new JTextField(10);
        studentIdField.setFont(font); // 设置文本框的字体
        addLabeledField(panel, "学号:", studentIdField, gbc);

        qqNumberField = new JTextField(10);
        qqNumberField.setFont(font); // 设置文本框的字体
        addLabeledField(panel, "QQ号:", qqNumberField, gbc);

        addButton = new JButton("添加");
        cancelButton = new JButton("取消");
        addButton.setFont(font);
        cancelButton.setFont(font);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取输入的学生信息
                String name = nameField.getText();
                String gender = genderField.getText();
                String birthDate = birthDateField.getText();
                String studentId = studentIdField.getText();
                String qqNumber = qqNumberField.getText();

                if ( nameField.getText().isEmpty() ||
                        genderField.getText().isEmpty() || birthDateField.getText().isEmpty() ||
                        studentIdField.getText().isEmpty() || qqNumberField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(AddStudentDialog.this, "所填入的信息不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Student student = null;
                try {
                    student = new Student(name,gender, DateUtil.parseStringToDate(birthDate),studentId,qqNumber);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                studentDao.addStudent(student);
                // 将学生信息添加到表格的数据模型中
                // 关闭对话框
                dispose();
                // 弹出对话框提示添加成功
                JOptionPane.showMessageDialog(AddStudentDialog.this, "添加成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 设置按钮面板布局
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        // 添加面板到对话框
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack(); // 自动调整窗口大小以适应其内容
        setVisible(true);
    }

    private void addLabeledField(JPanel panel, String labelText, JTextField textField, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText);
        label.setFont(textField.getFont());
        panel.add(label, gbc); // 添加标签

        gbc.gridx++; // 切换到下一个单元格
        panel.add(textField, gbc); // 添加文本框
        gbc.gridx--; // 重置到第一列
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 300);
                frame.setLocationRelativeTo(null);

                DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String[]{"编号", "姓名", "性别", "出生日期", "学号", "QQ号"});
                JTable table = new JTable(model);

                JButton addButton = new JButton("打开添加学生信息对话框");
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new AddStudentDialog(frame, model);
                    }
                });

                frame.setLayout(new FlowLayout());
                frame.add(addButton);
                frame.setVisible(true);
            }
        });
    }
}

