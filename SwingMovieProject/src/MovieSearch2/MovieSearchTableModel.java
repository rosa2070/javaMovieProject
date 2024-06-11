package MovieSearch2;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MovieSearchTableModel extends AbstractTableModel {

	private ArrayList<MovieSearchTO> datas;
	
	private String[] columnNames = new String[] { "영화제목", "장르", "감독", "개봉날짜", "국가" };
	private boolean[] columnEditables = new boolean[] { false, false, false, false, false };
	
	public MovieSearchTableModel(String searchMovieNm) {
		// TODO Auto-generated constructor stub
		
		MovieSearchDAO dao = new MovieSearchDAO();
		datas = dao.parseXML(searchMovieNm);
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
		MovieSearchTO to = datas.get( rowIndex );
		
		String result = "";
		switch(columnIndex) {
		case 0:
			result = to.getMovieNm();
			break;
		case 1:
			result = to.getGenreAlt();
			break;
		case 2:
			result = to.getDirector();
			break;
		case 3:
			result = to.getOpenDt();
			break;
		case 4:
			result = to.getNationAlt();
			break;
		}
		return result;
	}

}
