package DailyBoxOffice;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class BoxOfficeTableModel extends AbstractTableModel {

	private ArrayList<BoxOfficeTO> datas;
	
	private String[] columnNames = new String[] { "영화명", "개봉일자", "해당일 관객수", "누적 관객수", "증감율" };
	private boolean[] columnEditables = new boolean[] { false, false, false, false, false };
	
	public BoxOfficeTableModel() {
		// TODO Auto-generated constructor stub
		
		BoxOfficeDAO dao = new BoxOfficeDAO();
		datas = dao.parseXML();
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
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		BoxOfficeTO to = datas.get( rowIndex );
		
		String result = "";
		switch(columnIndex) {
		case 0:
			result = to.getRnum() + " " + to.getMovieNm();
			break;
		case 1:
			result = to.getOpenDt();
			break;
		case 2:
			result = to.getAudiCnt();
			break;
		case 3:
			result = to.getAudiAcc();
			break;
		case 4:
			result = to.getRankInten();
			break;
		}
		return result;
	}

}
