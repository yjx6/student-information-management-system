package JFrame;

import utils.DbUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class System3 extends JFrame implements ActionListener {//继承自JFrame使得这个类成为一个窗体,可以对窗体的属性进行扩展并且可以定义自己需要的特殊操作方法
    private static final long serialVersionUID = 1L;//把java对象序列化而后进行保存
    private Map<String, String> PersonInfo;
    public static Vector<Vector<String>> info = new Vector<Vector<String>>();
    private JLabel keyLab;
    private JButton searchBtn, createBtn, updateBtn, deleteBtn,exitBtn;
    public static JTable infoTable;
    private JTextField keyText;
    public static Vector<String> column;


    public System3() throws SQLException {
        PersonInfo = new HashMap<String, String>();//数组和链表的结合体,HashMap底层就是一个数组结构，数组中的每一项又是一个链表。新建一个HashMap的时候，就会初始化一个数组
        Font font = new Font("宋体", Font.PLAIN, 15);//设置字体，类型和大小；Front.PLAIN普通，Front.BLOD加粗，Front.ITALIC斜体
        JPanel pNorth = new JPanel();
        pNorth.setLayout(new FlowLayout(FlowLayout.RIGHT));
        keyLab = new JLabel("请输入关键字：");
        keyText = new JTextField(10);//搜索文本框

        //创建系统功能按钮
        searchBtn = new JButton("搜索学生信息");
        createBtn = new JButton("新增学生信息");
        updateBtn = new JButton("修改学生信息");
//        deleteBtn = new JButton("删除学生信息");
        exitBtn = new JButton("退出系统");

        //设置字体大小
        keyLab.setFont(font);
        searchBtn.setFont(font);
        createBtn.setFont(font);
        updateBtn.setFont(font);
//        deleteBtn.setFont(font);
        exitBtn.setFont(font);

        //添加监听器
        searchBtn.addActionListener(this);
        createBtn.addActionListener(this);
        updateBtn.addActionListener(this);
//        deleteBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        //在JPanel面板的上方加入搜索功能所需的一系列组件
        pNorth.add(keyLab);
        pNorth.add(keyText);
        pNorth.add(searchBtn);

        //在JPanel面板下方加入系统功能组件
        JPanel pSouth = new JPanel();
        pSouth.add(createBtn);
        pSouth.add(updateBtn);
//        pSouth.add(deleteBtn);
        pSouth.add(exitBtn);

        //表格数据
        column = new Vector<String>();
        column.add("学号");
        column.add("姓名");
        column.add("性别");
        column.add("年龄");
        column.add("班级");
        column.add("地址");
        column.add("政治面貌");
        column.add("入学时间");
        flashInfo();//将数据存入数据库

        // 设置表格字体
        Font tableFont = new Font("宋体", Font.PLAIN, 18);
        infoTable = new JTable(info, column) {
            @Override
            public Font getFont() {
                return tableFont;
            }
        };

        // 设置行高
        infoTable.setRowHeight(35);

        TableColumn column1 = infoTable.getColumnModel().getColumn(0);
        column1.setPreferredWidth(120);

        TableColumn column3 = infoTable.getColumnModel().getColumn(2);
        column3.setPreferredWidth(60);

        JScrollPane pCenter = new JScrollPane(infoTable);//创建垂直滚动面板
        this.add(pNorth, "North");
        this.add(pCenter, "Center");
        this.add(pSouth, "South");

        this.setTitle("学生信息管理系统");
        this.setSize(800, 450);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(1);
    }

    public static void flashInfo() throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        info.clear();
        try {
            stmt = DbUtil.getConnection().createStatement();//创建一个 Statement 对象，封装 SQL 语句发送给数据库
            rs = stmt.executeQuery("select * from studentinfo where class='计科22103'");//下达命令执行查询语句并且存放在ResultSet对象中
            while (rs.next()) {
                Vector<String> row = new Vector<String>();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                row.add(rs.getString(6));
                row.add(rs.getString(7));
                row.add(rs.getString(8));
                info.add(row);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();//在命令行打印异常信息在程序中出错的位置及原因
        } finally {
            try {
                if (stmt!= null) {
                    stmt.close();
                }
                if (rs!= null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        int rowNum = infoTable.getSelectedRow();//返回第一个选定行的索引
        if (rowNum!= -1) {
            PersonInfo = new HashMap<String, String>();
            //将值插入HasMap中
            PersonInfo.put("id", (String) infoTable.getValueAt(rowNum, 0));//返回表格row和column位置的单元格值
            PersonInfo.put("name", (String) infoTable.getValueAt(rowNum, 1));
            PersonInfo.put("sex", (String) infoTable.getValueAt(rowNum, 2));
            PersonInfo.put("age", (String) infoTable.getValueAt(rowNum, 3));
            PersonInfo.put("class", (String) infoTable.getValueAt(rowNum, 4));
            PersonInfo.put("address", (String) infoTable.getValueAt(rowNum, 5));
            PersonInfo.put("facial", (String) infoTable.getValueAt(rowNum, 6));
            PersonInfo.put("time", (String) infoTable.getValueAt(rowNum, 7));
        }

        if (e.getSource() == searchBtn) {//搜索
            String keyStr = keyText.getText();
            searchInfo(keyStr);
        } else if (e.getSource() == createBtn) {//新建
            new AddStudentGUI().setVisible(true);
        } else if (e.getSource() == updateBtn) {//修改
            int rowNum2 = infoTable.getSelectedRow();
            if (rowNum2!= -1) {
                // 创建一个新的对话框来获取修改后的数据
                UpdateStudentDialog updateDialog = new UpdateStudentDialog(PersonInfo);
                updateDialog.setVisible(true);

                // 当对话框确认修改后，获取修改后的数据并更新表格和数据库
                if (updateDialog.isUpdated()) {
                    try {
                        updateStudentInDatabase(PersonInfo);
                        flashInfo();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "请选择要修改的学生");
            }
        } else if (e.getSource() == deleteBtn) {//删除

        }else if(e.getSource()==exitBtn) {//退出
            this.setVisible(false);
        }
    }

    protected void searchInfo(String key) {//搜索

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = DbUtil.getConnection().createStatement();
            String sql = "select * from studentinfo where name like'%" + key + "%' and class='计科22103'";
//			String sql2 = "select * from student where sex like'%" + key + "%'";
//			String sql3 = "select * from student where telephone like'%" + key + "%'";
//            String sql4 = "select * from student where number like'%" + key + "%'";
//			String sql5 = "select * from student where birthday like'%" + key + "%'";
//			String sql6 = "select * from student where note like'%" + key + "%'";
//			String sql7 = "select * from student where id like'%" + key + "%'";

            rs = stmt.executeQuery(sql);
//			rs = stmt.executeQuery(sql2);
//			rs = stmt.executeQuery(sql3);
//            rs = stmt.executeQuery(sql4);
//			rs = stmt.executeQuery(sql5);
//			rs = stmt.executeQuery(sql6);
//			rs = stmt.executeQuery(sql7);
            info.clear();
            while (rs.next()) {
                Vector<String> row = new Vector<String>();//创建自增长数组
                row.add(rs.getString(1));//向Vector中添加值
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                row.add(rs.getString(6));
                row.add(rs.getString(7));
                row.add(rs.getString(8));
                info.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt!= null) {
                    stmt.close();
                }
                if (rs!= null) {
                    rs.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            DefaultTableModel model = new DefaultTableModel(System3.info, System3.column);//构造一个 DefaultTableModel，并通过将 data 和 columnNames 传递到 setDataVector 方法来初始化该表。
            System3.infoTable.setModel(model);//数据绑定
            TableColumn column1 = System3.infoTable.getColumnModel().getColumn(0);
            column1.setMaxWidth(150);
            column1.setMinWidth(150);
            TableColumn column3 = System3.infoTable.getColumnModel().getColumn(2);
            column3.setMaxWidth(90);
            column3.setMinWidth(90);
        }
    }

    private void updateStudentInDatabase(Map<String, String> updatedInfo) throws SQLException {
        Statement stmt = null;
        try {
            stmt = DbUtil.getConnection().createStatement();
            String sql = "UPDATE studentinfo SET name = '" + updatedInfo.get("name") + "', sex = '" + updatedInfo.get("sex") + "', age = " + updatedInfo.get("age") + ", class = '" + updatedInfo.get("class") + "', address = '" + updatedInfo.get("address") + "', facial = '" + updatedInfo.get("facial") + "', time = '" + updatedInfo.get("time") + "' WHERE id = " + updatedInfo.get("id");
            stmt.executeUpdate(sql);
        } finally {
            if (stmt!= null) {
                stmt.close();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        new System3();
    }
}