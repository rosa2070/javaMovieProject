package comboBox;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class JComboBoxEx01 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JComboBoxEx01 frame = new JComboBoxEx01();
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
	public JComboBoxEx01() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		//
		comboBox.addItemListener(new ItemListener() {
			// 상태값이 바뀌면 무조건 실행
			// selected / deselected
			public void itemStateChanged(ItemEvent e) {
				if( e.getStateChange() == ItemEvent.SELECTED ) {
					System.out.println( "이벤트 " + (String)comboBox.getSelectedItem() );	
				}
				
			}
		});
		
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"사과", "참외", "수박", "딸기"}));
		//comboBox.setModel( new CustomComboBoxModel1() );
		comboBox.setModel( new CustomComboBoxModel2() );
		//comboBox.setSelectedIndex(1);
		
		comboBox.setBounds(12, 10, 560, 23);
		contentPane.add(comboBox);
		
		JButton btn = new JButton("내용 가져오기");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println( comboBox.getSelectedIndex() );
				System.out.println( (String)comboBox.getSelectedItem() );
			}
		});
		btn.setBounds(12, 43, 142, 23);
		contentPane.add(btn);
	}
}
