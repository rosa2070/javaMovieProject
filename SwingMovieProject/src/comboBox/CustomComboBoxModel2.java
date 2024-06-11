package comboBox;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

public class CustomComboBoxModel2 extends DefaultComboBoxModel<String> {
	
	// 데이터
	private ArrayList<String> items = new ArrayList<String>();
	
	public CustomComboBoxModel2() {
		// TODO Auto-generated constructor stub
		items.add("사과");
		items.add("참외");
		items.add("수박");
		items.add("딸기");
	}
	
	// 시스템 호출
	// 데이터 갯수
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
//		System.out.println("getSize() 호출");
		return items.size();
	}
	
	// 한 개의 데이터를 가져오는 방법
	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
//		System.out.println("getElementAt() 호출");
		return items.get(index);
	}

}
