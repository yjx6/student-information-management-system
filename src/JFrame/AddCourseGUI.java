package JFrame;

import utils.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCourseGUI extends JFrame {

    private JTextField studentIdField;
    private JTextField nameField;

    private JTextField ageField;
    private JTextField classField;
    private JTextField addressField;

    private JButton addButton;

    public AddCourseGUI() {
        setTitle("添加课程信息");
        setDefaultCloseOperation(1);
        Font font = new Font("宋体", Font.BOLD, 24);
        // 使用 BorderLayout 布局
        setLayout(new BorderLayout(10, 10));

        JPanel contentPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel studentIdLabel = new JLabel("课程号:");
        studentIdField = new JTextField(20);
        studentIdLabel.setFont(font);
        JLabel nameLabel = new JLabel("课程名:");
        nameField = new JTextField(20);
        nameLabel.setFont(font);

        JLabel ageLabel = new JLabel("授课老师:");
        ageField = new JTextField(20);
        ageLabel.setFont(font);
        JLabel classLabel = new JLabel("授课时间:");
        classField = new JTextField(20);
        classLabel.setFont(font);
        JLabel addressLabel = new JLabel("授课地点:");
        addressField = new JTextField(20);
        addressLabel.setFont(font);

        addButton = new JButton("添加");
        addButton.setBackground(Color.BLUE);
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("宋体", Font.BOLD, 20));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudentInfo();
            }
        });
        contentPanel.add(studentIdLabel);
        contentPanel.add(studentIdField);
        contentPanel.add(nameLabel);
        contentPanel.add(nameField);
        contentPanel.add(ageLabel);
        contentPanel.add(ageField);
        contentPanel.add(classLabel);
        contentPanel.add(classField);
        contentPanel.add(addressLabel);
        contentPanel.add(addressField);

        add(contentPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // 设置背景颜色
        contentPanel.setBackground(Color.LIGHT_GRAY);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void addStudentInfo() {
        String id = studentIdField.getText();
        String name = nameField.getText();

        String age = ageField.getText();
        String classValue = classField.getText();
        String address = addressField.getText();


        try {
            Connection conn = DbUtil.getConnection();
            String query = "INSERT INTO course (cno, cname, teacher, time, place) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, age);
            stmt.setString(4, classValue);
            stmt.setString(5, address);


            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "课程信息添加成功");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "课程信息添加失败");
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    private void clearFields() {
        studentIdField.setText("");
        nameField.setText("");
        ageField.setText("");
        classField.setText("");
        addressField.setText("");

    }
    public static void main(String[] args) {
        new AddCourseGUI();
    }
}