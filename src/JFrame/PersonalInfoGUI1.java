package JFrame;

import javax.swing.*;
import java.awt.*;

public class PersonalInfoGUI1 extends JFrame {

    public PersonalInfoGUI1(String empId, String name, String gender, String education, String phoneNumber,
                           String politicalStatus, String classTaught) {

        setTitle("个人信息");
        setSize(800, 600);
        setDefaultCloseOperation(1);
        setLocationRelativeTo(null); // 居中显示

        // 创建面板
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 创建字体
        Font labelFont = new Font("宋体", Font.BOLD, 26); // 设置字体为SansSerif，加粗，大小为16

        // 添加标签和信息
        panel.add(createLabel("工号:", labelFont));
        panel.add(createLabel(empId, labelFont));

        panel.add(createLabel("姓名:", labelFont));
        panel.add(createLabel(name, labelFont));

        panel.add(createLabel("性别:", labelFont));
        panel.add(createLabel(gender, labelFont));

        panel.add(createLabel("学历:", labelFont));
        panel.add(createLabel(education, labelFont));

        panel.add(createLabel("手机号:", labelFont));
        panel.add(createLabel(phoneNumber, labelFont));

        panel.add(createLabel("政治面貌:", labelFont));
        panel.add(createLabel(politicalStatus, labelFont));

        panel.add(createLabel("所带班级:", labelFont));
        panel.add(createLabel(classTaught, labelFont));

        // 将面板添加到窗口中
        add(panel);

        setVisible(true);
    }

    // 创建带有指定字体的标签
    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }


}





