package movieRecord;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


public class MovieRecordTableModel extends AbstractTableModel {

	private ArrayList<MovieRecordDTO> datas;
	
	private String[] columnNames = new String[] { "영화 제목", "코멘트", "평점", "본 날짜"};
	private boolean[] columnEditables = new boolean[] { false, false, false, false };

	
	public MovieRecordTableModel() {
		// TODO Auto-generated constructor stub
		
		MovieRecordDAO dao = new MovieRecordDAO();
		datas = dao.recordList();
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return columnEditables[columnIndex];
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		MovieRecordDTO to = datas.get( rowIndex );
		
		String result = "";
		switch(columnIndex) {
		case 0:
			result = to.getTitle();
			break;
		case 1:
			result = to.getDate();
			break;
		case 2:
			result = to.getRate();
			break;
		case 3:
			result = to.getComm();
			break;
		}
		return result;
	}


}
