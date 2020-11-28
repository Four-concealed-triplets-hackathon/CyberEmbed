package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class SpiderService {
    public static String getEmbed(String url) {
        try {
            Document document = Jsoup.connect(url).get();

            // github url
            if (url.contains("github.com")) {
                String author = document.select("[itemprop=author]").text();
                String title = document.select("[itemprop=name]").text();
                String desc = document.getElementsMatchingOwnText("About").get(0).nextElementSibling().text();
                String stars = document.getElementsByAttributeValueContaining("aria-label", "starred this repository").text();
                String forks = document.getElementsByAttributeValueContaining("aria-label", "forked this repository").text();
                BufferedImage image = PosterService.generateGithubPoster(url, title, author, desc, stars, forks);
                String name = author + title;
                String outputPath = "../webapps/CyberEmbed_Web_exploded/public/img/"+name+".jpg";
                ImageIO.write(image, "jpg", new File(outputPath));
                return "/CyberEmbed_Web_exploded/public/img/"+name+".jpg";
            }
        } catch (Exception e) {
            System.out.println("get html content error");
        }
        return null;
    }

    public static void main(String[] args) {
        getEmbed("https://github.com/cilebritain/PRML-Spring20-FDU");
    }
}
