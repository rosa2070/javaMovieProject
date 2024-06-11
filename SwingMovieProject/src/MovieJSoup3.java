import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class MovieJSoup3 {

    public static void main(String[] args) {
        try {
            // 웹페이지의 HTML을 가져옴
            Document doc = Jsoup.connect("https://pedia.watcha.com/ko-KR/contents/mW42aEXkobis.or.kr/kobis/business/main/main.do").get();
            
            // td 요소를 선택하고, span 요소의 클래스가 "ellip"인 것을 선택
            Elements spans = doc.select("td span.ellip");

            // 선택된 span 요소에서 텍스트를 가져와 출력
            for (Element span : spans) {
                System.out.println(span.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
