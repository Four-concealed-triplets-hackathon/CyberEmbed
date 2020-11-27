package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SpiderService {
    public static void getEmbed(String url){
        try {
            Document document = Jsoup.connect(url).get();
            // github url
            if (url.contains("github.com")) {
                String author = document.select("[itemprop=author]").text();
                String title = document.select("[itemprop=name]").text();
                System.out.println(author);
                System.out.println(title);
            }
        } catch (Exception e){
            System.out.println("get html content error");
        }
    }
//    public static void main(String[] args) {
//        getEmbed("https://github.com/jhy/jsoup");
//    }
}
