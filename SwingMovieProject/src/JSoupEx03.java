import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoupEx03 {

    public static void main(String[] args) {
        BufferedReader br = null;
        StringBuilder sbHtml = new StringBuilder();
        
        try {
            URL url = new URL("https://www.kobis.or.kr/kobis/business/main/main.do");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String line = null;
            while ((line = br.readLine()) != null) {
                sbHtml.append(line).append(System.lineSeparator());
            }
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
        
        System.out.println(sbHtml.toString());
        
        Document doc = Jsoup.parse(sbHtml.toString());
        
        Elements lists = doc.getElementsByClass("td span.ellip");
        for (Element list : lists) {
            System.out.println(list.text());
        }
    }
}
