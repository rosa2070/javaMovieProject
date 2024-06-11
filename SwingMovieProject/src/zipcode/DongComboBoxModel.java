package zipcode;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

public class DongComboBoxModel extends DefaultComboBoxModel<String> {
	private ArrayList<ZipcodeTO> dongs;
	
	public DongComboBoxModel(String sido, String gugun) {
		// TODO Auto-generated constructor stub
		ZipcodeDAO dao = new ZipcodeDAO();
		dongs = dao.listDong(sido, gugun);
		
		ZipcodeTO to = new ZipcodeTO();
		to.setDong("동 선택");
		dongs.add(0, to);
	}
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return dongs.size();
	}
	
	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
		ZipcodeTO to = dongs.get(index);
		return to.getDong();
	}
}
