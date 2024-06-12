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
import javax.swing.JDialog;

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
		
		JButton deleteBtn = new JButton("삭제");
		deleteBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectRow = table.getSelectedRow();
				if (selectRow != -1) {
					String title = (String) table.getValueAt(selectRow, 0);
					MovieRecordDAO movieRecordDAO = new MovieRecordDAO();
					movieRecordDAO.deleteRecords(title);
					
					// 삭제 후 모델 갱신
					table.setModel(new MovieRecordTableModel());
				}
			}
		});
		deleteBtn.setBounds(400, 66, 97, 23);
		contentPane.add(deleteBtn);
		
		JButton updateBtn = new JButton("수정");
		updateBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectRow = table.getSelectedRow();
				if (selectRow != -1) {
					// 선택된 행에서 데이터 가져오기
					String title = (String) table.getValueAt(selectRow, 0);
					String date = (String) table.getValueAt(selectRow, 1);
					String rate = (String) table.getValueAt(selectRow, 2);
					String comment = (String) table.getValueAt(selectRow, 3);
					
					
					String[] dates = date.split("-");
					System.out.println(dates);
					String year = dates[0];
					String month= dates[1].startsWith("0") ? dates[1].substring(1) : dates[1];
					String day = dates[2].startsWith("0") ? dates[2].substring(1) : dates[2];
					
//					System.out.println(title);
//					System.out.println(year);
//					System.out.println(month);
//					System.out.println(day);
//					
//					
//					
//					System.out.println(rate);
//					System.out.println(comment);
//					
					MovieUpdateDialogUI dialog = new MovieUpdateDialogUI(MyPageUI.this, title, year, month, day, rate, comment);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setModal(true);
					dialog.setVisible(true);
					
//					MovieRecordDAO movieRecordDAO = new MovieRecordDAO();
//					movieRecordDAO.updateRecords(title, date, rate, comment);
					
					// 모델 갱신
					table.setModel(new MovieRecordTableModel());
				}
			}
		});
		updateBtn.setBounds(295, 66, 97, 23);
		contentPane.add(updateBtn);
		
		JButton createBtn = new JButton("코멘트 작성");
		createBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MovieRecordDialogUI dialog = new MovieRecordDialogUI(MyPageUI.this);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setModal(true);
				dialog.setVisible(true);
				
				// 모델 갱신
				table.setModel(new MovieRecordTableModel());
				
				
			}
		});
		createBtn.setBounds(61, 66, 134, 23);
		contentPane.add(createBtn);
	}
}
