package MovieSearch2;

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

public class MovieSearchDAO {

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
	
	public ArrayList<MovieSearchTO> parseXML(String movieNm) {
		
		StringBuilder sbData = this.readData( "https://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.xml?key=f5eef3421c602c6cb7ea224104795888&movieNm=" + movieNm);
		
		ArrayList<MovieSearchTO> datas = new ArrayList<MovieSearchTO>();
		
		Document doc = Jsoup.parse( sbData.toString() );
		
		Elements movies = doc.getElementsByTag( "movie" );
		for( Element movie : movies ) {
			MovieSearchTO to = new MovieSearchTO();
			
			to.setMovieNm( movie.child(1).text() );
			to.setGenreAlt( movie.child(8).text() );			
			to.setOpenDt( movie.child(4).text() );
			to.setNationAlt( movie.child(7).text() );
			
			// director 얻어오기 어렵
			Elements directors = movie.select("director");
			// director 태그가 있는 경우에만 처리
			if (!directors.isEmpty()) { 
				for (Element director : directors) {
		            // 각 director 태그의 peopleNm 태그 선택하여 이름 출력
					String directorName = director.select("peopleNm").text();
//		            System.out.println("감독 이름: " + directorName);
		            to.setDirector(directorName);
				}
			} else {
//				System.out.println("감독 정보 없음");
			}
		
			datas.add( to );
		}
		return datas;
	}
}
