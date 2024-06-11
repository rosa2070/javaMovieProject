package MovieSearch;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MovieSearchTableModel extends AbstractTableModel {

	private ArrayList<MovieSearchTO> datas;
	
	private String[] columnNames = new String[] { "영화제목", "장르", "감독", "개봉날짜", "국가" };
	private boolean[] columnEditables = new boolean[] { false, false, false, false, false };
	
	public MovieSearchTableModel() {
		// TODO Auto-generated constructor stub
		
		/*
		BoxOfficeTO to = new BoxOfficeTO();
		to.setRnum( "1" );
		to.setRank( "1" );
		to.setRankInten( "0" );
		to.setRankOldAndNew( "OLD" );
		to.setMovieCd( "20236653" );
		to.setMovieNm( "퓨리오사: 매드맥스 사가" );
		to.setOpenDt( "2024-05-22" );
		to.setAudiCnt( "28378" );
		to.setAudiAcc( "1079407" );
		
		datas = new ArrayList<BoxOfficeTO>();
		datas.add( to );
		*/
		
		MovieSearchDAO dao = new MovieSearchDAO();
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
