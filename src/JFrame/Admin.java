package JFrame;

import dao.ClassDelUpdateSea;
import dao.CourDelUpdateSea;
import dao.StuDelUpdateSea;
import dao.TeaDelUpdateSea;
import utils.DbUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Admin extends JFrame {

    private JLabel imageLabel, stuLabel, teaLabel, courLabel,classLabel;
    private JTable table;
    public Admin() {
        setTitle("管理员端");
        setDefaultCloseOperation(1);
        setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // 创建菜单栏
        JMenuBar menuBar = new JMenuBar();
        // 设置菜单的字体大小
        Font menuFont = new Font("宋体", Font.BOLD, 30);
        menuBar.setFont(menuFont);
        menuBar.setBackground(new Color(187, 255, 187));  // 淡绿色菜单栏背景
        // 创建一级菜单
        JMenu studentMenu = new JMenu("学生信息管理");
        JMenu teacherMenu = new JMenu("教师信息管理");
        JMenu courseMenu = new JMenu("课程信息管理");
        JMenu classMenu = new JMenu("班级信息管理");
        studentMenu.setFont(menuFont);
        studentMenu.setForeground(new Color(0, 128, 255));  // 蓝色文字
        studentMenu.setBackground(new Color(255, 228, 181));  // 淡橙色背景
        teacherMenu.setFont(menuFont);
        teacherMenu.setForeground(new Color(255, 140, 0));  // 橙色文字
        teacherMenu.setBackground(new Color(204, 255, 204));  // 淡绿色背景
        courseMenu.setFont(menuFont);
        courseMenu.setForeground(new Color(128, 0, 128));  // 紫色文字
        courseMenu.setBackground(new Color(255, 204, 204));  // 淡粉色背景
        classMenu.setFont(menuFont);
        classMenu.setForeground(new Color(0, 255, 0));  // 绿色文字
        classMenu.setBackground(new Color(204, 204, 255));  // 淡紫色背景
        // 为学生信息管理菜单添加二级菜单项
        JMenuItem addStudentItem = new JMenuItem("增加学生");
        JMenuItem SeadeleteupdateStuItem = new JMenuItem("学生信息维护");
        addStudentItem.setFont(menuFont);
        addStudentItem.setForeground(new Color(255, 255, 255));  // 白色文字
        addStudentItem.setBackground(new Color(0, 191, 255));  // 深蓝色背景
        addStudentItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudentGUI().setVisible(true);
            }
        });
        SeadeleteupdateStuItem.setFont(menuFont);
        SeadeleteupdateStuItem.setForeground(new Color(255, 255, 255));
        SeadeleteupdateStuItem.setBackground(new Color(0, 191, 255));
        SeadeleteupdateStuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StuDelUpdateSea().setVisible(true);
            }
        });

        // 为教师信息管理菜单添加二级菜单项
        JMenuItem addTeacherItem = new JMenuItem("增加教师");
        JMenuItem SeadeleteupdateTeaItem = new JMenuItem("教师信息维护");
        addTeacherItem.setFont(menuFont);
        addTeacherItem.setForeground(new Color(255, 255, 255));
        addTeacherItem.setBackground(new Color(255, 69, 0));  // 橙红色背景
        addTeacherItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTeacherGUI().setVisible(true);
            }
        });
        SeadeleteupdateTeaItem.setFont(menuFont);
        SeadeleteupdateTeaItem.setForeground(new Color(255, 255, 255));
        SeadeleteupdateTeaItem.setBackground(new Color(255, 69, 0));
        SeadeleteupdateTeaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TeaDelUpdateSea().setVisible(true);
            }
        });
        // 为课程信息管理菜单添加二级菜单项
        JMenuItem addCourseItem = new JMenuItem("增加课程");
        JMenuItem SeadeleteupdateCourseItem = new JMenuItem("课程信息维护");
        addCourseItem.setFont(menuFont);
        addCourseItem.setForeground(new Color(255, 255, 255));
        addCourseItem.setBackground(new Color(128, 0, 128));  // 紫色背景
        addCourseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCourseGUI().setVisible(true);
            }
        });
        SeadeleteupdateCourseItem.setFont(menuFont);
        SeadeleteupdateCourseItem.setForeground(new Color(255, 255, 255));
        SeadeleteupdateCourseItem.setBackground(new Color(128, 0, 128));
        SeadeleteupdateCourseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CourDelUpdateSea().setVisible(true);
            }
        });
        // 为班级信息管理菜单添加二级菜单项
        JMenuItem addClassItem = new JMenuItem("增加班级");
        JMenuItem SeadeleteupdateClassItem = new JMenuItem("班级信息维护");
        addClassItem.setFont(menuFont);
        addClassItem.setForeground(new Color(255, 255, 255));
        addClassItem.setBackground(new Color(0, 255, 0));  // 绿色背景
        addClassItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddClassGUI().setVisible(true);
            }
        });
        SeadeleteupdateClassItem.setFont(menuFont);
        SeadeleteupdateClassItem.setForeground(new Color(255, 255, 255));
        SeadeleteupdateClassItem.setBackground(new Color(0, 255, 0));
        SeadeleteupdateClassItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClassDelUpdateSea().setVisible(true);
            }
        });

        // 将二级菜单项添加到一级菜单中
        studentMenu.add(addStudentItem);
        studentMenu.add(SeadeleteupdateStuItem);

        teacherMenu.add(addTeacherItem);
        teacherMenu.add(SeadeleteupdateTeaItem);

        courseMenu.add(addCourseItem);
        courseMenu.add(SeadeleteupdateCourseItem);

        classMenu.add(addClassItem);
        classMenu.add(SeadeleteupdateClassItem);

        // 将一级菜单添加到菜单栏
        menuBar.add(studentMenu);
        menuBar.add(teacherMenu);
        menuBar.add(courseMenu);
        menuBar.add(classMenu);
        // 将菜单栏设置到frame中
        setJMenuBar(menuBar);

        int panelWidthLeft = screenWidth / 3;
        int panelWidthRight = 2 * panelWidthLeft;
        int panelHeight = screenHeight;
        JPanel panelLeft = new JPanel();
        panelLeft.setBounds(0, 0, panelWidthLeft, panelHeight);
        panelLeft.setBackground(new Color(255, 204, 153));  // 淡黄色背景
        // 加载图片并调整大小以适应标签
        ImageIcon originalIcon = new ImageIcon("D:\\Java实训\\校徽.png"); // 替换为你的图片路径
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(400, -1, Image.SCALE_SMOOTH); // 缩放图片以适应宽度500px，高度按比例自动调整
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBounds(180, 50, 400, 400);
        //学生信息管理标签
        stuLabel = new JLabel("学生信息查看");
        stuLabel.setBounds(270, 550, 300, 200);
        stuLabel.setFont(new Font("宋体", Font.BOLD, 40));
        stuLabel.setForeground(new Color(255, 0, 0));  // 红色文字
        //教师信息管理标签
        teaLabel = new JLabel("教师信息查看");
        teaLabel.setBounds(270, 700, 400, 200);
        teaLabel.setFont(new Font("宋体", Font.BOLD, 40));
        teaLabel.setForeground(new Color(0, 0, 255));  // 蓝色文字

        //课程信息管理标签
        courLabel = new JLabel("课程信息查看");
        courLabel.setBounds(270, 850, 400, 200);
        courLabel.setFont(new Font("宋体", Font.BOLD, 40));
        courLabel.setForeground(new Color(0, 128, 0));  // 绿色文字
        //班级信息管理标签
        classLabel = new JLabel("班级信息查看");
        classLabel.setBounds(270, 1000, 400, 200);
        classLabel.setFont(new Font("宋体", Font.BOLD, 40));
        classLabel.setForeground(new Color(128, 0, 128));  // 紫色文字
        panelLeft.setLayout(null);
        panelLeft.add(imageLabel);
        panelLeft.add(stuLabel);
        panelLeft.add(teaLabel);
        panelLeft.add(courLabel);
        panelLeft.add(classLabel);

        JPanel panelRight = new JPanel();
        panelRight.setBounds(panelWidthLeft, 0, panelWidthRight, panelHeight);
        panelRight.setLayout(null);
        panelRight.setBackground(new Color(224, 224, 224));  // 淡灰色背景
        // Create a table and add it to panelRight
        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        // Customize header font and column width
        JTableHeader header = table.getTableHeader();
        Font headerFont = new Font("宋体", Font.BOLD, 20); // Custom header font
        header.setFont(headerFont);
        header.setBackground(new Color(153, 204, 255));  // 淡蓝色表头背景

        // Set preferred column width for each column
        TableColumnModel columnModel = table.getColumnModel();
        int[] columnWidths = {150, 100, 120, 80, 150}; // Custom column widths
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(columnWidths[i]);
        }
// Set preferred size for table header (adjust height)
        header.setPreferredSize(new Dimension(header.getWidth(), 40)); // Adjust the height as needed

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(panelWidthRight, panelHeight - 600));
        scrollPane.setBounds(0, 480, panelWidthRight, panelHeight - 600);

        panelRight.add(scrollPane);
// Buttons for CRUD operations
        JButton studentButton = new JButton("统计学生信息");
        studentButton.setBounds(250, 150, 200, 60);
        studentButton.setFont(new Font("宋体", Font.PLAIN, 24)); // 设置字体大小为20
        studentButton.setForeground(new Color(255, 255, 255));  // 白色文字
        studentButton.setBackground(new Color(0, 191, 255));  // 深蓝色按钮背景
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentStatistics().setVisible(true);
            }
        });
        JButton teachButton = new JButton("统计教师信息");
        teachButton.setBounds(750, 150, 200, 60);
        teachButton.setFont(new Font("宋体", Font.PLAIN, 24)); // 设置字体大小为20
        teachButton.setForeground(new Color(255, 255, 255));
        teachButton.setBackground(new Color(255, 69, 0));  // 橙红色按钮背
        teachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TeacherStatistics().setVisible(true);
            }
        });
        JButton importButton = new JButton("导入");
        importButton.setBounds(1250, 150, 150, 60);
        importButton.setFont(new Font("宋体", Font.PLAIN, 24)); // 设置字体大小为20
        importButton.setForeground(new Color(255, 255, 255));
        importButton.setBackground(new Color(128, 0, 128));  // 紫色按钮背景
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importDataFromTxt();  // 点击导入按钮时执行导入操作
            }
        });

//        JButton exportButton = new JButton("导出");
//        exportButton.setBounds(1400, 150, 150, 60);
//        exportButton.setFont(new Font("宋体", Font.PLAIN, 24)); // 设置字体大小为20
//        exportButton.setForeground(new Color(255, 255, 255));
//        exportButton.setBackground(new Color(0, 255, 0));  // 绿色按钮背景

        panelRight.add(studentButton);
        panelRight.add(teachButton);
        panelRight.add(importButton);
//        panelRight.add(exportButton);
        add(panelLeft);
        add(panelRight);

        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null);
        setVisible(true);
        // Add action listeners to labels
        stuLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 在鼠标点击时调用 showTable 方法来显示课程信息
                showTable("studentinfo", new String[]{"学号", "姓名", "性别", "年龄", "班级", "家庭地址", "政治面貌", "入学时间"});
            }
        });
        teaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 在鼠标点击时调用 showTable 方法来显示课程信息
                showTable("teacher", new String[]{"教师工号", "姓名", "性别", "学历", "联系方式", "政治面貌", "所带班级", "账户密码"});
            }
        });
        courLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 在鼠标点击时调用 showTable 方法来显示课程信息
                showTable("course", new String[]{"课程号", "课程名称", "任课教师", "上课时间", "上课地点"});
            }
        });
        classLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 在鼠标点击时调用 showTable 方法来显示课程信息
                showTable("class", new String[]{"班主任", "班级名", "年级", "学生人数", "所属学院"});
            }
        });

    }




    // 导入数据的方法
    private void importDataFromTxt() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                Connection connection = DbUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO studentinfo (id, name, sex, age, class, address, facial, time) VALUES (?,?,?,?,?,?,?,?)");
                while ((line = reader.readLine())!= null) {
                    String[] values = line.split(",");  // 假设数据以逗号分隔
                    if (values.length == 8) {  // 确保数据字段数量正确
                        preparedStatement.setString(1, values[0]);
                        preparedStatement.setString(2, values[1]);
                        preparedStatement.setString(3, values[2]);
                        preparedStatement.setInt(4, Integer.parseInt(values[3]));
                        preparedStatement.setString(5, values[4]);
                        preparedStatement.setString(6, values[5]);
                        preparedStatement.setString(7, values[6]);
                        preparedStatement.setString(8, values[7]);
                        preparedStatement.executeUpdate();
                    } else {
                        System.out.println("无效的数据行: " + line);
                    }
                }
                preparedStatement.close();
                connection.close();
                JOptionPane.showMessageDialog(this, "数据导入成功！");
            } catch (IOException | SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "数据导入失败: " + e.getMessage());
            }
        }
    }


    // 显示数据库表格
    private void showTable(String tableName, String[] columnNames) {
        try {
            // 连接数据库
            Connection conn = DbUtil.getConnection();

            // 查询数据
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);

            // 获取结果集的元数据
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // 创建表格模型
            DefaultTableModel tableModel = new DefaultTableModel();

            // 设置表格模型的列名
            tableModel.setColumnIdentifiers(columnNames);

            // 添加行数据
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tableModel.addRow(row);
            }

            // 设置表格模型
            table.setModel(tableModel);

            // 关闭连接
            rs.close();
            stmt.close();
            conn.close();
            // 调整表格外观
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // 自动调整列宽
            table.setRowHeight(35); // 设置行高为30像素

            // 设置表格中字体的大小
            Font tableFont = new Font("宋体", Font.PLAIN, 20); // 16号字体
            table.setFont(tableFont);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "数据库连接或查询错误：" + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Admin();
    }

}
