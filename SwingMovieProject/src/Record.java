
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.stream.IntStream;

public class Record extends JFrame {

    private JComboBox<Integer> dayComboBox;
    private JComboBox<Integer> monthComboBox;
    private JComboBox<Integer> yearComboBox;
    private JComboBox<Integer> rateComboBox;
    private JTextArea textArea;
    private JTextArea titleArea;

    public Record() {
        setTitle("코멘트 작성하기");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 584, 72);

        dayComboBox = new JComboBox<>();
        dayComboBox.setBounds(327, 42, 32, 23);
        monthComboBox = new JComboBox<>();
        monthComboBox.setBounds(269, 42, 32, 23);
        yearComboBox = new JComboBox<>();
        yearComboBox.setBounds(211, 42, 32, 23);
        rateComboBox = new JComboBox<>();
        rateComboBox.setBounds(422, 42, 32, 23);
        titleArea = new JTextArea();
        titleArea.setBounds(111, 38, 88, 24);

        IntStream.rangeClosed(1, 31).forEach(dayComboBox::addItem);
        IntStream.rangeClosed(1, 12).forEach(monthComboBox::addItem);

        int currentYear = LocalDate.now().getYear();
        IntStream.rangeClosed(currentYear - 100, currentYear).forEach(yearComboBox::addItem);

        IntStream.rangeClosed(1, 5).forEach(rateComboBox::addItem);
        getContentPane().setLayout(null);
        panel.setLayout(null);

//        panel.add(new JLabel("코멘트 작성"));
        JLabel titleLabel = new JLabel("코멘트 작성");
        titleLabel.setBounds(98, 5, 101, 24);
        titleLabel.setFont(new Font("나눔고딕", Font.BOLD, 20));
        panel.add(titleLabel);

        JLabel label = new JLabel("제목");
        label.setBounds(82, 42, 24, 15);
        panel.add(label);
        panel.add(titleArea);

        panel.add(yearComboBox);
        JLabel label_1 = new JLabel("년 ");
        label_1.setBounds(248, 46, 16, 15);
        panel.add(label_1);
        panel.add(monthComboBox);
        JLabel label_2 = new JLabel("월 ");
        label_2.setBounds(306, 46, 16, 15);
        panel.add(label_2);
        panel.add(dayComboBox);
        JLabel label_3 = new JLabel("일 ");
        label_3.setBounds(364, 46, 16, 15);
        panel.add(label_3);

        getContentPane().add(panel);

        textArea = new JTextArea();
//        textArea.setBounds(56, 55, 320, 270);
//        panel.add(textArea);

        JLabel label_4 = new JLabel("평점: ");
        label_4.setBounds(385, 46, 32, 15);
        panel.add(label_4);
        panel.add(rateComboBox);


        JButton saveButton = new JButton("작성 완료");
        saveButton.addActionListener(e -> {
            //String title = titleField.getText();
            int day = (int) dayComboBox.getSelectedItem();
            int month = (int) monthComboBox.getSelectedItem();
            int year = (int) yearComboBox.getSelectedItem();
            LocalDate selectedDate = LocalDate.of(year, month, day);
            JOptionPane.showMessageDialog(this, "Selected Date: " + selectedDate);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 728, 584, 33);
        buttonPanel.add(saveButton);
        getContentPane().add(buttonPanel);

        JTextArea textArea_1 = new JTextArea();
        textArea_1.setBounds(0, 34, 584, 694);
        getContentPane().add(textArea_1);

//        add(saveButton);
    }

    private void saveReviewToData(String title, LocalDate date, String comment) {
        String url = "jdbc:mariadb://localhost:3306/movieProject";
        String user = "movieProject";
        String password = "123456!j";

        String sql = "INSERT INTO movieProject(title, date, comm) VALUES (?, ?, ?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = conn.prepareStatement(sql); {

                pstmt.setString(1, title);
                pstmt.setDate(2, java.sql.Date.valueOf(date));
                pstmt.setString(3, comment);
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "저장되었습니다");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "에러" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Record record = new Record();
            record = new Record();
            record.setVisible(true);
        });
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            Record2 record = new Record2();
//            record = new Record2();
//            record.setVisible(true);
//        });
//    }

}
