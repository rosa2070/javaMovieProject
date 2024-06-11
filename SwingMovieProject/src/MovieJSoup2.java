import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MovieJSoup2 {

    public static void main(String[] args) {
        BufferedReader br = null;
        StringBuilder sbHtml = new StringBuilder();

        try {
            // 웹페이지에서 HTML을 가져옴
            URLConnection conn = new URL("https://www.kobis.or.kr/kobis/business/main/main.do").openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            // HTML을 한 줄씩 읽어와 StringBuilder에 저장
            while ((line = br.readLine()) != null) {
                sbHtml.append(line).append(System.lineSeparator());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // HTML 파싱하여 Document 객체 생성
        Document doc = Jsoup.parse(sbHtml.toString());

        // 특정 테이블 요소 선택
        Elements spans = doc.getElementsByTag("td").select("span.ellip");
        
        
        // 선택된 span 요소에서 텍스트를 가져와 출력
        for (Element span : spans) {
            System.out.println(span.text());
        }
    }
}
