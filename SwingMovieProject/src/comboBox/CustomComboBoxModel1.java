package comboBox;
import javax.swing.DefaultComboBoxModel;

public class CustomComboBoxModel1 extends DefaultComboBoxModel<String> {
	
	// 데이터
	private String[] items = new String[] {"사과", "참외", "수박", "딸기"};
	
	// 시스템 호출
	// 데이터 갯수
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
//		System.out.println("getSize() 호출");
		return items.length;
	}
	
	// 한 개의 데이터를 가져오는 방법
	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
//		System.out.println("getElementAt() 호출");
		return items[index];
	}

}
