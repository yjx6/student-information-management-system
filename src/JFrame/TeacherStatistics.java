package JFrame;

import utils.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TeacherStatistics extends JFrame {

    private JComboBox<String> statisticsOptions;
    private JButton statisticsButton;
    private JTextArea resultTextArea;

    public TeacherStatistics() {
        setTitle("教师信息统计");
        setDefaultCloseOperation(1);
        setLayout(new BorderLayout(10, 10));
        Font font = new Font("宋体", Font.PLAIN, 24);

        // 整体背景色
        getContentPane().setBackground(new Color(245, 245, 245));

        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        optionPanel.setBackground(new Color(220, 220, 220));

        String[] options = {"学历", "政治面貌"};
        statisticsOptions = new JComboBox<>(options);
        statisticsOptions.setFont(font);

        statisticsButton = new JButton("统计");
        statisticsButton.setFont(font);
        statisticsButton.setBackground(new Color(0, 128, 255));
        statisticsButton.setForeground(Color.WHITE);

        JLabel jLabel = new JLabel("选择统计项：");
        jLabel.setFont(font);
        jLabel.setForeground(Color.BLACK);

        optionPanel.add(jLabel);
        optionPanel.add(statisticsOptions);
        optionPanel.add(statisticsButton);

        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);
        resultTextArea.setFont(new Font("宋体", Font.PLAIN, 24));
        resultTextArea.setBackground(new Color(255, 255, 255));

        add(optionPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultTextArea), BorderLayout.CENTER);

        statisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) statisticsOptions.getSelectedItem();
                String sql = generateSQL(selectedOption);
                performStatistics(sql);
            }
        });

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String generateSQL(String selectedOption) {
        String tableName = "academic";
        switch (selectedOption) {
            case "学历":
                return "SELECT academic, COUNT(*) as count FROM teacher  GROUP BY academic";
            case "政治面貌":
                return "SELECT facial, COUNT(*) as count FROM teacher GROUP BY facial";
            default:
                return "";
        }
    }

    private void performStatistics(String sql) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            StringBuilder result = new StringBuilder();
            while (resultSet.next()) {
                String category = resultSet.getString(1);
                int count = resultSet.getInt(2);
                result.append(category).append(": ").append(count).append("\n");
            }

            resultTextArea.setText(result.toString());

        } catch (SQLException ex) {
            ex.printStackTrace();
            resultTextArea.setText("查询出错");
        }
    }

    public static void main(String[] args) {
        new TeacherStatistics();
    }
}