package JFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class UpdateStudentDialog extends JDialog implements ActionListener {

    private Map<String, String> originalInfo;
    private JTextField idField, nameField, sexField, ageField, classField, addressField, facialField, timeField;
    private JButton updateButton, cancelButton;
    private boolean isUpdated = false;

    public UpdateStudentDialog(Map<String, String> personInfo) {
        originalInfo = personInfo;

        JPanel panel = new JPanel(new GridLayout(9, 2));

        JLabel idLabel = new JLabel("学号:");
        idField = new JTextField(originalInfo.get("id"));

        JLabel nameLabel = new JLabel("姓名:");
        nameField = new JTextField(originalInfo.get("name"));

        JLabel sexLabel = new JLabel("性别:");
        sexField = new JTextField(originalInfo.get("sex"));

        JLabel ageLabel = new JLabel("年龄:");
        ageField = new JTextField(originalInfo.get("age"));

        JLabel classLabel = new JLabel("班级:");
        classField = new JTextField(originalInfo.get("class"));

        JLabel addressLabel = new JLabel("地址:");
        addressField = new JTextField(originalInfo.get("address"));

        JLabel facialLabel = new JLabel("政治面貌:");
        facialField = new JTextField(originalInfo.get("facial"));

        JLabel timeLabel = new JLabel("入学时间:");
        timeField = new JTextField(originalInfo.get("time"));

        updateButton = new JButton("确认修改");
        cancelButton = new JButton("取消");

        updateButton.addActionListener(this);
        cancelButton.addActionListener(this);

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(sexLabel);
        panel.add(sexField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(classLabel);
        panel.add(classField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(facialLabel);
        panel.add(facialField);
        panel.add(timeLabel);
        panel.add(timeField);
        panel.add(updateButton);
        panel.add(cancelButton);

        getContentPane().add(panel);

        setTitle("修改学生信息");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setModal(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            Map<String, String> updatedInfo = new HashMap<>();
            updatedInfo.put("id", idField.getText());
            updatedInfo.put("name", nameField.getText());
            updatedInfo.put("sex", sexField.getText());
            updatedInfo.put("age", ageField.getText());
            updatedInfo.put("class", classField.getText());
            updatedInfo.put("address", addressField.getText());
            updatedInfo.put("facial", facialField.getText());
            updatedInfo.put("time", timeField.getText());

            originalInfo.putAll(updatedInfo);
            isUpdated = true;
            dispose();
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }

    public boolean isUpdated() {
        return isUpdated;
    }
}

