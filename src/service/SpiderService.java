package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class SpiderService {
    public static BufferedImage getEmbed(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            // github url
            if (url.contains("github.com")) {
                String author = document.select("[itemprop=author]").text();
                String title = document.select("[itemprop=name]").text();
                String desc = document.getElementsMatchingOwnText("About").get(0).nextElementSibling().text();
                String stars = document.getElementsByAttributeValueContaining("aria-label", "starred this repository").text();
                String forks = document.getElementsByAttributeValueContaining("aria-label", "forked this repository").text();
                System.out.println(author);
                System.out.println(title);
                System.out.println(desc);
                System.out.println(stars);
                System.out.println(forks);
                BufferedImage image = PosterService.generateGithubPoster(url, title, author, desc, stars, forks);
                String outputPath = "./public/img/poster.jpg";
                ImageIO.write(image, "jpg", new File(outputPath));
                return image;
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
