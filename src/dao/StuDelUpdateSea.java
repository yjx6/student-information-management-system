package dao;

import utils.DbUtil;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
public class StuDelUpdateSea extends JFrame {
    private JScrollPane jsp = new JScrollPane();
    private JTable table = new JTable();
    private JButton btn1 = new JButton("修改");
    private JButton btn2 = new JButton("删除");
    private JButton prevButton = new JButton("上一页");
    private JButton nextButton = new JButton("下一页");
    private MyModel model;
    private JLabel jLabel1 = new JLabel("学号：");
    private JLabel jLabel2 = new JLabel("姓名：");
    private JLabel jLabel3 = new JLabel("性别：");
    private JLabel jLabel4 = new JLabel("年龄：");
    private JLabel jLabel5 = new JLabel("班级：");
    private JLabel jLabel6 = new JLabel("家庭地址：");
    private JLabel jLabel7 = new JLabel("政治面貌：");
    private JLabel jLabel8 = new JLabel("入学时间：");
    private JTextField jTextField1 = new JTextField();
    private JTextField jTextField2 = new JTextField();
    private JTextField jTextField3 = new JTextField();
    private JTextField jTextField4 = new JTextField();
    private JTextField jTextField5 = new JTextField();
    private JTextField jTextField6 = new JTextField();
    private JTextField jTextField7 = new JTextField();
    private JTextField jTextField8 = new JTextField();
    private JButton jButton1 = new JButton("查找");
    private JTextField jTextField = new JTextField();
    private Panel panBtn = new Panel();
    private Panel panLab = new Panel();
    private int currentPage = 1; // 当前页码
    private int pageSize = 10; // 每页显示的行数
    private int totalPages; // 总页数
    public StuDelUpdateSea() {
// this.setSize(600, 500);
        this.setSize(1000, 800);
        setTitle("学生信息修改与删除");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        model = new MyModel("select * from studentinfo limit " + (currentPage - 1) * pageSize + ", " + pageSize);
        table.setModel(model);
//设置表头字体
        JTableHeader header = table.getTableHeader();
        Font headerFont = new Font("宋体", Font.BOLD, 20); // Custom header font
        header.setFont(headerFont);
// 修改列名称
        table.getColumnModel().getColumn(0).setHeaderValue("学号");
        table.getColumnModel().getColumn(1).setHeaderValue("姓名");
        table.getColumnModel().getColumn(2).setHeaderValue("性别");
        table.getColumnModel().getColumn(3).setHeaderValue("年龄");
        table.getColumnModel().getColumn(4).setHeaderValue("班级");
        table.getColumnModel().getColumn(5).setHeaderValue("家庭地址");
        table.getColumnModel().getColumn(6).setHeaderValue("政治面貌");
        table.getColumnModel().getColumn(7).setHeaderValue("入学时间");
// 设置行高
        table.setRowHeight(30); // 设置行高为30像素，根据需要调整
// 设置字体大小
        Font tableFont = new Font("宋体", Font.PLAIN, 20); // 设置字体为Arial，大小为16像素，根据需要调整
        table.setFont(tableFont);
// 设置可见视图的接口
        jsp.setViewportView(table);
// 定义表格 宽600，高度200
        jsp.setPreferredSize(new Dimension(600, 350));
// 设置横向和垂直滚动条可见
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        Font font = new Font("宋体", Font.BOLD, 18);
        btn1.setFont(font);
        btn2.setFont(font);
        prevButton.setFont(font);
        nextButton.setFont(font);
        panBtn.add(btn1);
        panBtn.add(btn2);
        panBtn.add(prevButton);
        panBtn.add(nextButton);
// 设置中间的panel布局为空
        panLab.setLayout(null);
// 单选框
        JRadioButton JB1 = new JRadioButton("按学号查找");
        JRadioButton JB2 = new JRadioButton("按姓名查找");
        JB1.setSelected(true); // 默认选择JB1
        JB1.setFont(font);
        JB2.setFont(font);
// 加入组，避免出现可以两个都选择的情况
        ButtonGroup bg = new ButtonGroup();
        bg.add(JB1);
        bg.add(JB2);
// 给组件设置位置
        JB1.setSize(150, 30);
// JB1.setLocation(40, 20);
        JB1.setLocation(180, 50);
        JB2.setSize(170, 30);
        JB2.setLocation(330, 50);
        jTextField.setSize(140, 30);
        jTextField.setLocation(510, 50);
        jButton1.setSize(100, 30);
        jButton1.setLocation(680, 50);
        jButton1.setFont(font);
        jLabel1.setSize(100, 100);
        jLabel1.setLocation(180, 80);
        jLabel1.setHorizontalAlignment(JLabel.RIGHT);
        jLabel1.setFont(font);
        jTextField1.setSize(150, 30);
        jTextField1.setLocation(330, 115);
        jLabel2.setSize(100, 100);
        jLabel2.setLocation(480, 80);
        jLabel2.setHorizontalAlignment(JLabel.RIGHT);
        jLabel2.setFont(font);
        jTextField2.setSize(150, 30);
        jTextField2.setLocation(630, 115);
        jLabel3.setSize(100, 100);
        jLabel3.setLocation(180, 140);
        jLabel3.setHorizontalAlignment(JLabel.RIGHT);
        jLabel3.setFont(font);
        jTextField3.setSize(150, 30);
        jTextField3.setLocation(330, 175);
        jLabel4.setSize(100, 100);
        jLabel4.setLocation(480, 140);
        jLabel4.setHorizontalAlignment(JLabel.RIGHT);
        jLabel4.setFont(font);
        jTextField4.setSize(150, 30);
        jTextField4.setLocation(630, 175);
        jLabel5.setSize(100, 100);
        jLabel5.setLocation(180, 200);
        jLabel5.setHorizontalAlignment(JLabel.RIGHT);
        jLabel5.setFont(font);
        jTextField5.setSize(150, 30);
        jTextField5.setLocation(330, 235);
        jLabel6.setSize(100, 100);
        jLabel6.setLocation(480, 200);
        jLabel6.setHorizontalAlignment(JLabel.RIGHT);
        jLabel6.setFont(font);
        jTextField6.setSize(150, 30);
        jTextField6.setLocation(630, 235);
        jLabel7.setSize(100, 100);
        jLabel7.setLocation(180, 260);
        jLabel7.setHorizontalAlignment(JLabel.RIGHT);
        jLabel7.setFont(font);
        jTextField7.setSize(150, 30);
        jTextField7.setLocation(330, 295);
        jLabel8.setSize(100, 100);
        jLabel8.setLocation(480, 260);
        jLabel8.setHorizontalAlignment(JLabel.RIGHT);
        jLabel8.setFont(font);
        jTextField8.setSize(150, 30);
        jTextField8.setLocation(630, 295);
// 把标签和文本框加到panLab面板中
        panLab.add(jLabel1);
        panLab.add(jLabel2);
        panLab.add(jLabel3);
        panLab.add(jLabel4);
        panLab.add(jLabel5);
        panLab.add(jLabel6);
        panLab.add(jLabel7);
        panLab.add(jLabel8);
        panLab.add(jTextField1);
        panLab.add(jTextField2);
        panLab.add(jTextField3);
        panLab.add(jTextField4);
        panLab.add(jTextField5);
        panLab.add(jTextField6);
        panLab.add(jTextField7);
        panLab.add(jTextField8);
        panLab.add(JB1);
        panLab.add(JB2);
        panLab.add(jTextField);
        panLab.add(jButton1);
        this.add(jsp, BorderLayout.NORTH);
        this.add(panLab, BorderLayout.CENTER);
        this.add(panBtn, BorderLayout.SOUTH);
// 获取表里的值
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String id, name, type, writter, price, address, facial, time;
                int selRow = table.getSelectedRow();
                id = table.getValueAt(selRow, 0).toString().trim();
                name = table.getValueAt(selRow, 1).toString().trim();
                type = table.getValueAt(selRow, 2).toString().trim();
                writter = table.getValueAt(selRow, 3).toString().trim();
                price = table.getValueAt(selRow, 4).toString().trim();
                address = table.getValueAt(selRow, 5).toString().trim();
                facial = table.getValueAt(selRow, 6).toString().trim();
                time = table.getValueAt(selRow, 7).toString().trim();
                jTextField1.setText(id);
                jTextField2.setText(name);
                jTextField3.setText(type);
                jTextField4.setText(writter);
                jTextField5.setText(price);
                jTextField6.setText(address);
                jTextField7.setText(facial);
                jTextField8.setText(time);
            }
        });
        jTextField1.setEditable(false);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (JB1.isSelected()) {
                    String s = jTextField.getText().trim();
                    String sql = "select * from studentinfo where id like '%" + s + "%';";
                    model = new MyModel(sql);
                    table.setModel(model);
                    table.getColumnModel().getColumn(0).setHeaderValue("学号");
                    table.getColumnModel().getColumn(1).setHeaderValue("姓名");
                    table.getColumnModel().getColumn(2).setHeaderValue("性别");
                    table.getColumnModel().getColumn(3).setHeaderValue("年龄");
                    table.getColumnModel().getColumn(4).setHeaderValue("班级");
                    table.getColumnModel().getColumn(5).setHeaderValue("家庭地址");
                    table.getColumnModel().getColumn(6).setHeaderValue("政治面貌");
                    table.getColumnModel().getColumn(7).setHeaderValue("入学时间");
                } else {
                    String s = jTextField.getText().trim();
                    String sql = "select * from studentinfo where name like '%" + s + "%'";
                    model = new MyModel(sql);
                    table.setModel(model);
                    table.getColumnModel().getColumn(0).setHeaderValue("学号");
                    table.getColumnModel().getColumn(1).setHeaderValue("姓名");
                    table.getColumnModel().getColumn(2).setHeaderValue("性别");
                    table.getColumnModel().getColumn(3).setHeaderValue("年龄");
                    table.getColumnModel().getColumn(4).setHeaderValue("班级");
                    table.getColumnModel().getColumn(5).setHeaderValue("家庭地址");
                    table.getColumnModel().getColumn(6).setHeaderValue("政治面貌");
                    table.getColumnModel().getColumn(7).setHeaderValue("入学时间");
                }
            }
        });
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String id = jTextField1.getText().trim();
                String name = jTextField2.getText().trim();
                String type = jTextField3.getText().trim();
                String writer = jTextField4.getText().trim();
                String price = jTextField5.getText().trim();
                String address = jTextField6.getText().trim();
                String facial = jTextField7.getText().trim();
                String time = jTextField8.getText().trim();
                try {
                    Connection coon = DbUtil.getConnection();
                    Statement stmt = coon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    String sql = "update studentinfo set name = '" + name + "',sex = '" + type + "',age = '" + writer + "',class = '" + price + "',address = '" + address + "',facial = '" + facial + "',time = '" + time + "' where id = " + id + ";";
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "修改成功。");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
//        btn2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                String id = jTextField1.getText().trim();
//                int result = JOptionPane.showConfirmDialog(null, "确定删除吗?",
//                        "提示", JOptionPane.YES_NO_OPTION);
//                if (result == JOptionPane.YES_OPTION) {
//                    try {
//                        Connection coon = DbUtil.getConnection();
//                        Statement stmt = coon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                        String sql = "delete from studentinfo where id = " + id + ";";
//                        stmt.executeUpdate(sql);
//                        JOptionPane.showMessageDialog(null, "已删除！");
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    dispose();
//                }
//            }
//        });
        btn2.addActionListener(new ActionListener() { // 批量删除按钮的点击事件
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();
                if (selectedRows.length == 0) {
                    JOptionPane.showMessageDialog(null, "请先选择要批量删除的行！");
                    return;
                }
                StringBuilder sqlBuilder = new StringBuilder("delete from studentinfo where id in (");
                for (int i = 0; i < selectedRows.length; i++) {
                    String id = table.getValueAt(selectedRows[i], 0).toString();
                    sqlBuilder.append(id);
                    if (i < selectedRows.length - 1) {
                        sqlBuilder.append(",");
                    }
                }
                sqlBuilder.append(");");
                try (Connection coon = DbUtil.getConnection();
                     Statement stmt = coon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                    stmt.executeUpdate(sqlBuilder.toString());
                    //sqlBuilder 是一个 StringBuilder 对象，用于构建 SQL 语句的字符串。.toString() 方法将 StringBuilder 对象转换为字符串类型
                    JOptionPane.showMessageDialog(null, "批量删除成功！");
                    updateData();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "批量删除失败！");
                }
            }
        });
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 1) {
                    currentPage--;
                    updateData();
                }
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage < totalPages) {
                    currentPage++;
                    updateData();
                }
            }
        });




        updateTotalPages();
    }
    private void updateData() {
        model = new MyModel("select * from studentinfo limit " + (currentPage - 1) * pageSize + ", " + pageSize);
        //0, 10 表示从第 1 行开始，返回 10 行记录；10, 10 表示从第 11 行开始，返回 10 行记录，以此类推
        table.setModel(model);
        table.getColumnModel().getColumn(0).setHeaderValue("学号");
        table.getColumnModel().getColumn(1).setHeaderValue("姓名");
        table.getColumnModel().getColumn(2).setHeaderValue("性别");
        table.getColumnModel().getColumn(3).setHeaderValue("年龄");
        table.getColumnModel().getColumn(4).setHeaderValue("班级");
        table.getColumnModel().getColumn(5).setHeaderValue("家庭地址");
        table.getColumnModel().getColumn(6).setHeaderValue("政治面貌");
        table.getColumnModel().getColumn(7).setHeaderValue("入学时间");

    }
    private void updateTotalPages() {
        try (Connection coon = DbUtil.getConnection();
             Statement stmt = coon.createStatement();
             ResultSet rs = stmt.executeQuery("select count(*) from studentinfo")) {
            if (rs.next()) {
                int totalRows = rs.getInt(1);
                totalPages = (int) Math.ceil((double) totalRows / pageSize);
                //Math.ceil()：这个方法返回大于或等于其参数的最小整数值，即向上取整。它确保即使最后一页只有少量记录，也会计算为一页。
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    class MyModel extends AbstractTableModel {
        private int row;
        private int column;
        private ResultSet rs;
        private Statement stmt;
        public MyModel(String sql) {
            try {
                Connection coon = DbUtil.getConnection();
                Statement stmt = coon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs = stmt.executeQuery(sql);
                rs.last();// 将光标移到最后一行
                row = rs.getRow();// 获取行号(最大行索引)
                ResultSetMetaData rsmd = rs.getMetaData();// 通过结果集对象来获取
                column = rsmd.getColumnCount();// 获取列数
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        public int getColumnCount() {
            return column;
        }
        @Override
        public int getRowCount() {
            return row;
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Object value = null;
            try {
                rs.absolute(rowIndex + 1);
                value = rs.getString(columnIndex + 1);// 获取表里的数据
            } catch (Exception e) {
                e.printStackTrace();
            }
            return value;
        }
    }
    public static void main(String[] args) {
        new StuDelUpdateSea().setVisible(true);
    }
}
