package JFrame;

import model.Teacher;
import utils.Check;
import utils.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    private JTextField usernameField,checkField;
    private JPasswordField passwordField;
    private JButton LoginButton;
    private JLabel backgroundLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel checkLabel;
    private JLabel loginLabel;
    private JLabel checkLabelImg;
//    private JLabel comLabel;
    private JCheckBox showPasswordCheckBox;
//    private JComboBox<String> comboBox;
    private BufferedImage checkImage;
    private String checkCode;
    public Login(){
        // 设置窗口标题
        setTitle("登录页面");
        // 设置窗口大小
        setSize(2560, 1600);
        // 设置窗口关闭操作
        setDefaultCloseOperation(1);
        // 设置布局管理器为null
        setLayout(null);
        setLocationRelativeTo(null);
        // 加载背景图片
        ImageIcon backgroundIcon = new ImageIcon("D:\\Java实训\\理工图书馆.png");
        // 获取窗口的实际大小
        int windowWidth = getWidth();
        int windowHeight = getHeight();
        // 缩放背景图片以适应窗口大小
        Image img = backgroundIcon.getImage().getScaledInstance(windowWidth, windowHeight, Image.SCALE_SMOOTH);
        backgroundIcon = new ImageIcon(img);
        backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, windowWidth, windowHeight);

        // 创建一个标签作为图片容器
        loginLabel = new JLabel();
        loginLabel.setBounds(780, 100, 1000, 350); // 设置标签的位置和大小

        // 加载图片并缩放以适应标签大小
        ImageIcon loginImageIcon = new ImageIcon("D:\\Java实训\\理工logo.png"); // 替换为你的图片路径
        Image imgg = loginImageIcon.getImage();
        Image scaledImg = imgg.getScaledInstance(loginLabel.getWidth(), loginLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        // 设置缩放后的图片为标签的图标
        loginLabel.setIcon(scaledIcon);
        // 创建用户名提示文字
        usernameLabel = new JLabel("用户名:");
        usernameLabel.setBounds(1000, 450, 200, 180);
        usernameLabel.setForeground(Color.white);
        usernameLabel.setFont(new Font("宋体", Font.BOLD, 30));
        // 创建用户名输入文本域
        usernameField = new JTextField();
        usernameField.setBounds(1200, 513, 300, 50);
        // 设置字体大小为 20
        Font font = new Font("Arial", Font.PLAIN, 24); // 这里选择了 Arial 字体，可以根据需要更改
        usernameField.setFont(font);
        // 创建密码提示文字
        passwordLabel = new JLabel("密  码:");
        passwordLabel.setBounds(1000, 550, 200, 180);
        passwordLabel.setForeground(Color.white);
        passwordLabel.setFont(new Font("宋体", Font.BOLD, 30));
        // 创建密码输入文本域
        passwordField = new JPasswordField();
        passwordField.setBounds(1200, 613, 300, 50);
        passwordField.setFont(font);



        // 创建"显示密码"复选框
        showPasswordCheckBox = new JCheckBox("显示密码");
        showPasswordCheckBox.setBounds(1200, 670, 150, 30);
        showPasswordCheckBox.setForeground(Color.white);
        showPasswordCheckBox.setFont(new Font("宋体", Font.BOLD, 18));
        showPasswordCheckBox.setOpaque(false); // 使复选框背景透明
        // 创建验证码提示文字
        checkLabel = new JLabel("验证码:");
        checkLabel.setBounds(1000, 650, 200, 180);
        checkLabel.setForeground(Color.white);
        checkLabel.setFont(new Font("宋体", Font.BOLD, 30));
        // 创建验证码输入文本域
        checkField = new JTextField();
        checkField.setBounds(1200, 713, 150, 50);
        checkField.setFont(font);
        //创建验证码图片
        checkLabelImg=new JLabel();
        checkLabelImg.setBounds(1360, 713, 150, 50); // 设置位置和大小
        checkLabelImg.setForeground(Color.white); // 设置前景色为白色
        checkLabelImg.setFont(new Font("宋体", Font.BOLD, 30)); // 设置字体
//        //选择角色提示文字
//        comLabel=new JLabel("登录方式:");
//        comLabel.setBounds(1000, 730, 200, 180);
//        comLabel.setForeground(Color.white);
//        comLabel.setFont(new Font("宋体", Font.BOLD, 30));
//        //选择角色
//        String[] options = {"教师登录", "管理员登录"};
//        comboBox = new JComboBox<>(options);
//        comboBox.setBounds(1200, 800, 200, 35);
//        comboBox.setFont(new Font("宋体", Font.BOLD, 30)); // 设置字体
        // 创建"登录"按钮
        LoginButton = new JButton("登录");
        LoginButton.setBounds(1200, 900, 200, 60);
        LoginButton.setFont(new Font("宋体", Font.BOLD, 30));
//验证码的动作事件
        checkLabelImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                generateCheckCode(); // 生成新的验证码
                refreshCheckCodeImage(); // 刷新验证码图片
            }
        });
// 在登录按钮的事件处理方法中添加验证码比对逻辑
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = checkField.getText().trim(); // 获取用户输入的验证码
                if (userInput.equalsIgnoreCase(checkCode)) { // 比对用户输入与生成的验证码
                    // 验证码正确，继续登录流程
                    // TODO: 执行登录操作
                    adminLogin();
                } else {
                    // 验证码错误，显示错误提示
                    JOptionPane.showMessageDialog(null, "验证码错误，请重新输入", "验证码错误", JOptionPane.ERROR_MESSAGE);
                    // 刷新验证码
                    generateCheckCode(); // 生成新的验证码
                    refreshCheckCodeImage(); // 刷新验证码图片
                    checkField.setText(""); // 清空验证码输入框
                }
            }
        });

        generateCheckCode(); // 初始化生成验证码
        refreshCheckCodeImage(); // 初始化显示验证码图片

        // 将组件添加到窗口中
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginLabel);
        add(LoginButton);
        add(showPasswordCheckBox);
        add(checkLabel);
        add(checkField);
        add(checkLabelImg); // 将验证码提示文字添加到界面中
//        add(comLabel);
//        add(comboBox);
        // 将背景标签添加到窗口的内容面板中
        getContentPane().add(backgroundLabel);
        // 添加复选框动作事件
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0); // 显示密码
                } else {
                    passwordField.setEchoChar('\u2022'); // 隐藏密码
                    //这是 Unicode 中表示的圆点（bullet）
                }
            }
        });
    }
    private Teacher getUserDetails(String username) {
        try (Connection connection = DbUtil.getConnection()) {
            String query = "SELECT * FROM teacher WHERE t_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("t_id");
                String name = resultSet.getString("name");
                String sex = resultSet.getString("sex");
                String academic = resultSet.getString("academic");
                String phone = resultSet.getString("phone");
                String facial = resultSet.getString("facial");
                String manage_class = resultSet.getString("class");

                return new Teacher(id,  name,  sex,  academic,  phone,  facial,  manage_class);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // 生成随机验证码字符串
    private void generateCheckCode() {

        Check generator = new Check();
        checkCode = generator.generateCheckCode(4); // 生成长度为4的随机验证码

    }
    private void adminLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if (username.isEmpty()||password.isEmpty()){
            JOptionPane.showMessageDialog(this, "用户名密码不能为空");
            return;
        }
        if (DbUtil.validateAdmin(username, password)) {
            JOptionPane.showMessageDialog(this, "登录成功");
           new Admin().setVisible(true);
        }else if (DbUtil.validateUser(username, password)){
            JOptionPane.showMessageDialog(this, "登录成功");
            String username2=usernameField.getText().trim();
            if (username2.equals("2001")){
                Teacher teacher=getUserDetails(username2);
                new TeacherFrame1().setVisible(true);
                new PersonalInfoGUI(teacher);
            }
            if (username2.equals("2002")){
                Teacher teacher=getUserDetails(username2);
                new TeacherFrame2().setVisible(true);
                new PersonalInfoGUI(teacher);
            }
            if (username2.equals("2003")){
                Teacher teacher=getUserDetails(username2);
                new TeacherFrame3().setVisible(true);
                new PersonalInfoGUI(teacher);
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "用户名或密码不正确");
        }
    }

    // 根据验证码字符串生成验证码图片
    private void refreshCheckCodeImage() {
        int width = 140;
        int height = 50;
        checkImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = checkImage.createGraphics();//用于在 checkImage 图片上绘制图形。
        // 填充背景色
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        // 绘制验证码文字
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("宋体", Font.BOLD, 30));
        g2d.drawString(checkCode, 20, 35);//使用 drawString() 方法在图片上绘制 checkCode 变量中存储的验证码字符串，起始坐标为 (20, 35)。
        g2d.dispose(); // 释放图形上下文资源
        // 更新界面上显示的验证码图片
        checkLabelImg.setIcon(new ImageIcon(checkImage.getScaledInstance(140, 50, Image.SCALE_SMOOTH)));
        //将其显示在界面上的一个标签 (checkLabelImg) 中。
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
