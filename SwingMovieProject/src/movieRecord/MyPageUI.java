package movieRecord;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MyPageUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyPageUI frame = new MyPageUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyPageUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 99, 435, 640);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null},
//			},
//			new String[] {
//				"\uC601\uD654 \uC81C\uBAA9", "\uCF54\uBA58\uD2B8", "\uBCF8 \uB0A0\uC9DC", "\uD3C9\uC810"
//			}
//		));
		
		table.setModel(new MovieRecordTableModel());
		
		JLabel lblNewLabel = new JLabel("My Page");
		lblNewLabel.setBounds(257, 10, 80, 24);
		lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("삭제");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectRow = table.getSelectedRow();
				if (selectRow != -1) {
					String title = (String) table.getValueAt(selectRow, 0);
					MovieRecordDAO movieRecordDAO = new MovieRecordDAO();
					movieRecordDAO.deleteRecords(title);
					
		            // JTable의 모델에서 해당 행 제거
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.removeRow(selectRow);
					
		            // JTable 다시 그리기
					scrollPane.revalidate();
					scrollPane.repaint();
					
					
				}
			}
		});
		btnNewButton.setBounds(400, 66, 97, 23);
		contentPane.add(btnNewButton);
	}
}
