package JFrame;

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
import java.sql.*;

public class TeacherFrame extends JFrame {

    private JLabel imageLabel, stuLabel, teaLabel, courLabel;
    private JTable table;
    private String longinAccount;

    public TeacherFrame(String longinAccount) {
        this.longinAccount = longinAccount;
    }

    public TeacherFrame() {
        setTitle("教师端");
        setDefaultCloseOperation(1);
        setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int panelWidthLeft = screenWidth / 3;
        int panelWidthRight = 2 * panelWidthLeft;
        int panelHeight = screenHeight;
        JPanel panelLeft = new JPanel();
        panelLeft.setBounds(0, 0, panelWidthLeft, panelHeight);
        // 加载图片并调整大小以适应标签
        ImageIcon originalIcon = new ImageIcon("D:\\Java实训\\计算机学院徽标2.png"); // 替换为你的图片路径
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(400, -1, Image.SCALE_SMOOTH); // 缩放图片以适应宽度500px，高度按比例自动调整
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBounds(180, 50, 400, 400);
        //查看本班学生信息标签
        stuLabel = new JLabel("查看本班信息");
        stuLabel.setBounds(270, 550, 300, 200);
        stuLabel.setFont(new Font("宋体", Font.BOLD, 40));


        //查看个人信息标签
        teaLabel = new JLabel("查看个人信息");
        teaLabel.setBounds(270, 700, 400, 200);
        teaLabel.setFont(new Font("宋体", Font.BOLD, 40));

        //统计学生信息标签
        courLabel = new JLabel("统计学生信息");
        courLabel.setBounds(270, 850, 400, 200);
        courLabel.setFont(new Font("宋体", Font.BOLD, 40));


        panelLeft.setLayout(null);
        panelLeft.add(imageLabel);
        panelLeft.add(stuLabel);
        panelLeft.add(teaLabel);
        panelLeft.add(courLabel);


        JPanel panelRight = new JPanel();
        panelRight.setBounds(panelWidthLeft, 0, panelWidthRight, panelHeight);
        panelRight.setLayout(null);
        // Create a table and add it to panelRight
        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        // Customize header font and column width
        JTableHeader header = table.getTableHeader();
        Font headerFont = new Font("宋体", Font.BOLD, 20); // Custom header font
        header.setFont(headerFont);

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
        JButton addButton = new JButton("添加");
        addButton.setBounds(150, 120, 150, 60);
        addButton.setFont(new Font("宋体", Font.PLAIN, 24)); // 设置字体大小为20
        JButton deleteButton = new JButton("删除");
        deleteButton.setBounds(550, 120, 150, 60);
        deleteButton.setFont(new Font("宋体", Font.PLAIN, 24)); // 设置字体大小为20
        JButton updateButton = new JButton("更新");
        updateButton.setBounds(950, 120, 150, 60);
        updateButton.setFont(new Font("宋体", Font.PLAIN, 24)); // 设置字体大小为20
        JButton searchButton = new JButton("搜索");
        searchButton.setBounds(1350, 120, 150, 60);
        searchButton.setFont(new Font("宋体", Font.PLAIN, 24)); // 设置字体大小为20

        panelRight.add(addButton);
        panelRight.add(deleteButton);
        panelRight.add(updateButton);
        panelRight.add(searchButton);
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
                showTable("", new String[]{"学号", "姓名", "性别", "年龄", "班级", "家庭地址", "政治面貌", "入学时间"});
            }
        });
        teaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PersonalInfoGUI1("", "", "", "", "", "", "").setVisible(true);
            }
        });
        courLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 在鼠标点击时调用 showTable 方法来显示课程信息
                showTable("course", new String[]{"课程号", "课程名称", "任课教师", "上课时间", "上课地点"});
            }
        });


    }

    // Label click listener class
    private class LabelClickListener implements ActionListener {
        private String tableName;
        private String[] columnNames;

        public LabelClickListener(String tableName, String[] columnNames) {
            this.tableName = tableName;
            this.columnNames = columnNames;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            showTable(tableName, columnNames);
        }
    }

    // 显示数据库表格
    private void showTable(String tableName, String[] columnNames) {
        try {
            // 连接数据库
            Connection conn = DbUtil.getConnection();

            // 查询数据
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM studentinfo where class=" + tableName);

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
        new TeacherFrame();
    }

}
