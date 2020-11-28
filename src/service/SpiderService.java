package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
                String name = String.valueOf(System.currentTimeMillis());
                String outputPath = "./public/img/github/"+name+".jpg";
                ImageIO.write(image, "jpg", new File(outputPath));
                return "/CyberEmbed_Web_exploded/public/img/github/"+name+".jpg";
            }
            //cnki url
            else if(url.contains("kns.cnki.net")){
                String title = document.select("h1").text();
                Elements authors = document.select("#authorpart").get(0).children();
                String author = "";
                for(int i=0; i<authors.size(); i++)author += authors.get(i).text().replaceAll("\\d+", "").replaceAll(",", "")+ " ";
                String abs = document.select("#ChDivSummary").text();
                String topic = "";
                Elements topics = document.select(".keywords").get(0).children();
                for(int i=0; i<topics.size(); i++)topic += topics.get(i).text() + " ";
                Elements infos = document.select("[class=total-inform]").get(0).children();
                String downloads = "";
                String pages = "";
                for(int i=0; i<infos.size(); i++){
                    if(infos.get(i).text().contains("下载：")){
                        downloads = infos.get(i).text().split("下载：")[1];
                    }
                    if(infos.get(i).text().contains("页数：")){
                        pages = infos.get(i).text().split("页数：")[1];
                    }
                }
                BufferedImage image = PosterService.generateCnkiPoster(url, title, author, abs, topic, downloads, pages);
                String name = String.valueOf(System.currentTimeMillis());
                String outputPath = "./public/img/cnki/"+name+".jpg";
                ImageIO.write(image, "jpg", new File(outputPath));
                return "/CyberEmbed_Web_exploded/public/img/cnki/"+name+".jpg";
            }
            //twitter url
            else if(url.contains("store.steampowered.com")){
                String title = document.select("[class=apphub_AppName]").text();
                String header_photo = document.select("[class=game_header_image_full]").attr("src");
                String desc = document.select("[class=game_description_snippet]").text();
                Elements info = document.select("[class=summary column]");
                String recentReview = info.get(0).children().get(0).text() + info.get(0).children().get(1).text();
                String allReview = "";
                if(info.size()<4) allReview = recentReview;
                else allReview = info.get(1).children().get(0).text() + info.get(1).children().get(1).text();
                String tag = document.select("[class=glance_tags popular_tags]").get(0).children().get(0).text();
                BufferedImage image = PosterService.generateSteamPoster(url, header_photo, desc, recentReview, allReview, tag);
                String name = String.valueOf(System.currentTimeMillis());
                String outputPath = "./public/img/steam/"+name+".jpg";
                ImageIO.write(image, "jpg", new File(outputPath));
                return "/CyberEmbed_Web_exploded/public/img/steam/"+name+".jpg";
            }
            else {
                return new String("parse url error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new String("parse url error");
        }
    }

    public static void main(String[] args) {
//        getEmbed("https://github.com/cilebritain/PRML-Spring20-FDU");
//        getEmbed("https://kns.cnki.net/KCMS/detail/33.1151.s.20201120.0950.002.html");
//        getEmbed("https://kns.cnki.net/KCMS/detail/11.3536.F.20201120.1432.020.html");
//        testImage();
//        getEmbed("https://store.steampowered.com/app/1217060/_/");
//        getEmbed("https://store.steampowered.com/app/578080/PLAYERUNKNOWNS_BATTLEGROUNDS/");
        getEmbed("https://store.steampowered.com/app/292030/_3/");
    }
}
