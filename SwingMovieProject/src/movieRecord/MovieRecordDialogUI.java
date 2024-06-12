package movieRecord;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.stream.IntStream;

public class MovieRecordDialogUI extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static String date;
    private static String grade;
    private static JTextArea textArea;
    private static JTextField textField;

    public MovieRecordDialogUI(JFrame parent) {
        super(parent, "코멘트 작성", true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 536);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("코멘트 작성");
        lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 17));
        lblNewLabel.setBounds(230, 47, 101, 15);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("본 날짜");
        lblNewLabel_1.setBounds(40, 114, 57, 15);
        contentPane.add(lblNewLabel_1);

        JComboBox<Integer> yearComboBox = new JComboBox<>();
        yearComboBox.setBounds(90, 110, 57, 23);
        contentPane.add(yearComboBox);

        JLabel lblNewLabel_2 = new JLabel("년");
        lblNewLabel_2.setBounds(159, 114, 20, 15);
        int currentYear = LocalDate.now().getYear();
        IntStream.rangeClosed(currentYear - 20, currentYear).forEach(yearComboBox::addItem);
        contentPane.add(lblNewLabel_2);

        JComboBox<Integer> monthComboBox = new JComboBox<>();
        monthComboBox.setBounds(191, 110, 64, 23);
        IntStream.rangeClosed(1, 12).forEach(monthComboBox::addItem);
        contentPane.add(monthComboBox);

        JLabel lblNewLabel_3 = new JLabel("월");
        lblNewLabel_3.setBounds(267, 114, 20, 15);
        contentPane.add(lblNewLabel_3);

        JComboBox<Integer> dayComboBox = new JComboBox<>();
        dayComboBox.setBounds(299, 110, 51, 23);
        IntStream.rangeClosed(1, 31).forEach(dayComboBox::addItem);
        contentPane.add(dayComboBox);

        JLabel lblNewLabel_4 = new JLabel("일");
        lblNewLabel_4.setBounds(362, 114, 20, 15);
        contentPane.add(lblNewLabel_4);

        textArea = new JTextArea();
        textArea.setBounds(41, 139, 440, 304);
        contentPane.add(textArea);

        JLabel lblNewLabel_5 = new JLabel("평점");
        lblNewLabel_5.setBounds(46, 453, 32, 15);
        contentPane.add(lblNewLabel_5);

        JComboBox<Integer> gradeComboBox = new JComboBox<>();
        gradeComboBox.setBounds(90, 449, 51, 23);
        IntStream.rangeClosed(1, 5).forEach(gradeComboBox::addItem);
        contentPane.add(gradeComboBox);

        JButton btn = new JButton("작성 완료");
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // IntStream 문자열로 바꾸려면 .toString()
            	String title = textField.getText();
            	String comment = textArea.getText();
                String year = yearComboBox.getSelectedItem().toString();
                String month = (int) monthComboBox.getSelectedItem() < 10 ? "0" + monthComboBox.getSelectedItem().toString() : monthComboBox.getSelectedItem().toString();
                String day = (int) dayComboBox.getSelectedItem() < 10 ? "0" + dayComboBox.getSelectedItem().toString() : dayComboBox.getSelectedItem().toString();

                date = year + "-" + month + "-" + day;
                grade = gradeComboBox.getSelectedItem().toString();

                MovieRecordDAO movieRecordDAO = new MovieRecordDAO();
                movieRecordDAO.insertRecords(title, date, grade, comment);

                
                // 대화 상자 닫기
                dispose();
            }
        });
        btn.setBounds(384, 449, 97, 23);
        contentPane.add(btn);

        JLabel lblNewLabel_6 = new JLabel("영화제목");
        lblNewLabel_6.setBounds(40, 85, 57, 15);
        contentPane.add(lblNewLabel_6);

        textField = new JTextField();
        textField.setBounds(90, 83, 116, 21);
        contentPane.add(textField);
        textField.setColumns(10);
    }
}
