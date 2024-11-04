package JFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JLabel backgroundLabel;
    public Main(){
        // 设置窗口标题
        setTitle("主页面");
        // 设置窗口大小
        setSize(2560, 1600);
        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置布局管理器为null
        setLayout(null);
        setLocationRelativeTo(null);
        // 加载背景图片
        ImageIcon backgroundIcon = new ImageIcon("D:\\Java实训\\理工东门.png");
        // 获取窗口的实际大小
        int windowWidth = getWidth();
        int windowHeight = getHeight();
        // 缩放背景图片以适应窗口大小
        Image img = backgroundIcon.getImage().getScaledInstance(windowWidth, windowHeight, Image.SCALE_SMOOTH);
        backgroundIcon = new ImageIcon(img);
        backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, windowWidth, windowHeight);
        // 将背景标签添加到窗口的内容面板中
        getContentPane().add(backgroundLabel);

        // 创建菜单栏
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar); // 设置窗口的菜单栏

        // 创建“快速入口”菜单
        JMenu quickAccessMenu = new JMenu("快速入口");
        menuBar.add(quickAccessMenu); // 将菜单添加到菜单栏
        // 设置菜单的字体大小
        Font menuFont = new Font("宋体", Font.BOLD, 30); // 定义字体，可以根据需要调整大小和样式
        quickAccessMenu.setFont(menuFont);
        // 创建菜单项“教务网络管理系统”
        JMenuItem jwMenu = new JMenuItem("教务网络管理系统");
        quickAccessMenu.add(jwMenu); // 将菜单项添加到“快速入口”菜单
        // 设置菜单项的字体大小
        Font menuItemFont = new Font("宋体", Font.PLAIN, 26); // 定义字体，可以根据需要调整大小和样式
        jwMenu.setFont(menuItemFont);
        // 为“教务网络管理系统”菜单项添加点击事件处理
        jwMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里添加点击菜单项后的操作
                new Login().setVisible(true);
                // 可以在这里打开新的窗口或者执行其他操作
            }

        });
    }
    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}
