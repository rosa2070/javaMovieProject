package DailyBoxOffice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BoxOfficeDAO {

	public StringBuilder readData(String url) {
		StringBuilder sbData = new StringBuilder();
		
		BufferedReader br = null;
		
		try {
			URLConnection conn = new URL( url ).openConnection();
			br = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
			
			String line = null;
			while( ( line = br.readLine() ) != null ) {
				sbData.append( line );
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println( "[에러] " + e.getMessage() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( br != null ) try { br.close(); } catch( IOException e ) {}
		}
		return sbData;
	}
	
	public ArrayList<BoxOfficeTO> parseXML() {
		// 현재 날짜
		LocalDate currentDate = LocalDate.now();
		
		//하루 전 날짜 
		LocalDate previousDate = currentDate.minusDays(1);
		
		String date = previousDate.toString();
		String date2 = date.replace("-", "");
		
		StringBuilder sbData = this.readData( "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=f5eef3421c602c6cb7ea224104795888&targetDt=" + date2 );
		
		ArrayList<BoxOfficeTO> datas = new ArrayList<BoxOfficeTO>();
		
		Document doc = Jsoup.parse( sbData.toString() );
		
		Elements dailyBoxOffices = doc.getElementsByTag( "dailyBoxOffice" );
		for( Element dailyBoxOffice : dailyBoxOffices ) {
			BoxOfficeTO to = new BoxOfficeTO();
			to.setRnum( dailyBoxOffice.child(0).text() );
			to.setRank( dailyBoxOffice.child(1).text() );
			to.setRankInten( dailyBoxOffice.child(2).text() );
			to.setRankOldAndNew( dailyBoxOffice.child(3).text() );
			to.setMovieCd( dailyBoxOffice.child(4).text() );
			to.setMovieNm( dailyBoxOffice.child(5).text() );
			to.setOpenDt( dailyBoxOffice.child(6).text() );
			to.setAudiCnt( dailyBoxOffice.child(12).text() );
			to.setAudiAcc( dailyBoxOffice.child(15).text() );
		
			datas.add( to );
		}
		return datas;
	}
}
