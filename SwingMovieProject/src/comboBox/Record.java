package comboBox;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.stream.IntStream;

public class Record extends JFrame {

    private JComboBox<Integer> dayComboBox;
    private JComboBox<Integer> monthComboBox;
    private JComboBox<Integer> yearComboBox;
    private JTextArea textArea;

    public Record() {
        setTitle("코멘트 작성하기");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setLayout(new GridLayout(2, 1));
        getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        dayComboBox = new JComboBox<>();
        monthComboBox = new JComboBox<>();
        yearComboBox = new JComboBox<>();

        IntStream.rangeClosed(1, 31).forEach(dayComboBox::addItem);

        IntStream.rangeClosed(1, 12).forEach(monthComboBox::addItem);

        int currentYear = LocalDate.now().getYear();
        IntStream.rangeClosed(currentYear - 100, currentYear).forEach(yearComboBox::addItem);

        panel.add(new JLabel("년:"));
        panel.add(yearComboBox);
        panel.add(new JLabel("월:"));
        panel.add(monthComboBox);
        panel.add(new JLabel("일:"));
        panel.add(dayComboBox);

        getContentPane().add(panel, BorderLayout.NORTH);

        textArea = new JTextArea();
//        textArea.setBounds(56, 55, 320, 270);
//        panel.add(textArea);


        JButton saveButton = new JButton("작성 완료");
        saveButton.addActionListener(e -> {
            int day = (int) dayComboBox.getSelectedItem();
            int month = (int) monthComboBox.getSelectedItem();
            int year = (int) yearComboBox.getSelectedItem();
            LocalDate selectedDate = LocalDate.of(year, month, day);
            JOptionPane.showMessageDialog(this, "Selected Date: " + selectedDate);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        JTextArea textArea_1 = new JTextArea();
        getContentPane().add(textArea_1, BorderLayout.CENTER);

//        add(saveButton);
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
