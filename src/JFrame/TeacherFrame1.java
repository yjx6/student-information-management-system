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
import java.io.*;
import java.sql.*;

public class TeacherFrame1 extends JFrame {

    private JLabel imageLabel, stuLabel, teaLabel, courLabel;

    private JTable table;

    public TeacherFrame1() {
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
        panelLeft.setBackground(new Color(255, 228, 181));  // 淡橙色背景
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
        stuLabel.setForeground(new Color(0, 191, 255));  // 深蓝色文字

        //查看个人信息标签
        teaLabel = new JLabel("查看个人信息");
        teaLabel.setBounds(270, 700, 400, 200);
        teaLabel.setFont(new Font("宋体", Font.BOLD, 40));
        teaLabel.setForeground(new Color(255, 69, 0));  // 橙红色文字
        //统计学生信息标签
        courLabel = new JLabel("统计学生信息");
        courLabel.setBounds(270, 850, 400, 200);
        courLabel.setFont(new Font("宋体", Font.BOLD, 40));
        courLabel.setForeground(new Color(128, 0, 128));  // 紫色文字

        panelLeft.setLayout(null);
        panelLeft.add(imageLabel);
        panelLeft.add(stuLabel);
        panelLeft.add(teaLabel);
        panelLeft.add(courLabel);


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
        JButton exportButton = new JButton("导出");
        exportButton.setBounds(250, 150, 150, 60);
        exportButton.setFont(new Font("宋体", Font.PLAIN, 24)); // 设置字体大小为20
        exportButton.setForeground(new Color(255, 255, 255));  // 白色文字
        exportButton.setBackground(new Color(0, 191, 255));  // 深蓝色背景
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportTableToTXT();
            }
        });
        JButton moreButton = new JButton("信息维护");
        moreButton.setBounds(800, 150, 150, 60);
        moreButton.setFont(new Font("宋体", Font.PLAIN, 24)); // 设置字体大小为20
        moreButton.setForeground(new Color(255, 255, 255));
        moreButton.setBackground(new Color(255, 69, 0));  // 橙红色背景
        moreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new System1();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        JButton importButton = new JButton("导入");
        importButton.setBounds(1350, 150, 150, 60);
        importButton.setFont(new Font("宋体", Font.PLAIN, 24)); // 设置字体大小为20
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importDataFromTxt();
            }
        });
        importButton.setForeground(new Color(255, 255, 255));
        importButton.setBackground(new Color(128, 0, 128));  // 紫色背景
//        JButton searchButton = new JButton("搜索");
//        searchButton.setBounds(1350, 120, 150, 60);
//        searchButton.setFont(new Font("宋体", Font.PLAIN, 24)); // 设置字体大小为20
//        searchButton.setForeground(new Color(255, 255, 255));
//        searchButton.setBackground(new Color(0, 255, 0));  // 绿色背景

        panelRight.add(exportButton);
        panelRight.add(moreButton);
        panelRight.add(importButton);
//        panelRight.add(searchButton);
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
                showTable(new String[]{"学号", "姓名", "性别", "年龄", "班级", "家庭地址", "政治面貌", "入学时间"});
            }
        });
        teaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PersonalInfoGUI1("2001", "小杨", "男", "硕士研究生", "18330068959", "党员", "计科22101").setVisible(true);
            }
        });
        courLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new StudentStatistics1().setVisible(true);
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
                    //split(",") 方法是Java中的字符串方法，用于根据指定的分隔符 , 将字符串 line 分割成一个字符串数组。
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
    private void showTable(String[] columnNames) {
        try {
            // 连接数据库
            Connection conn = DbUtil.getConnection();

            // 查询数据
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM studentinfo where class= '计科22101' ");

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
                //遍历查询结果集 rs，将每行数据作为一个 Object 数组 row 添加到表格模型 tableModel 中。
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

    // 导出表格数据到TXT文件
    private void exportTableToTXT() {
        try {
            //创建一个文件选择器 JFileChooser，并设置对话框标题为 "选择保存位置"。
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("选择保存位置");
            int userSelection = fileChooser.showSaveDialog(this);//调用 showSaveDialog(this) 显示保存文件对话框，this 表示当前窗口是其父组件。
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                FileWriter fw = new FileWriter(fileToSave);

                // 写入表头
                for (int i = 0; i < table.getColumnCount(); i++) {
                    fw.append(table.getColumnName(i));
                    if (i < table.getColumnCount() - 1) {
                        fw.append('\t'); // 使用制表符分隔列
                    } else {
                        fw.append('\n');
                    }
                }

                // 写入数据行
                for (int row = 0; row < table.getRowCount(); row++) {
                    for (int col = 0; col < table.getColumnCount(); col++) {
                        fw.append(table.getValueAt(row, col).toString());
                        if (col < table.getColumnCount() - 1) {
                            fw.append('\t'); // 使用制表符分隔列
                        } else {
                            fw.append('\n');
                        }
                    }
                }

                fw.flush();
                fw.close();
                JOptionPane.showMessageDialog(this, "表格数据已成功导出到 " + fileToSave.getAbsolutePath());

            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "导出过程中出现错误：" + e.getMessage());
        }
    }


    public static void main(String[] args) {
        new TeacherFrame1();
    }

}
