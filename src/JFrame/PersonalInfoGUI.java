package JFrame;

import model.Teacher;

import javax.swing.*;
import java.awt.*;

public class PersonalInfoGUI extends JFrame {
    private Teacher teacher;
    public PersonalInfoGUI(Teacher teacher) {
        this.teacher=teacher;
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
        panel.add(createLabel(teacher.getId(), labelFont));

        panel.add(createLabel("姓名:", labelFont));
        panel.add(createLabel(teacher.getName(), labelFont));

        panel.add(createLabel("性别:", labelFont));
        panel.add(createLabel(teacher.getSex(), labelFont));

        panel.add(createLabel("学历:", labelFont));
        panel.add(createLabel(teacher.getAcademic(), labelFont));

        panel.add(createLabel("手机号:", labelFont));
        panel.add(createLabel(teacher.getPhone(), labelFont));

        panel.add(createLabel("政治面貌:", labelFont));
        panel.add(createLabel(teacher.getFacial(), labelFont));

        panel.add(createLabel("所带班级:", labelFont));
        panel.add(createLabel(teacher.getManage_class(), labelFont));

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





