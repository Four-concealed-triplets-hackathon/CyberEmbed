package service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PosterService {
    /**
     * 生成Github项目海报
     *
     * @author cappuccinocup
     */
    private static BufferedImage generateGithubPoster(String title, String[] authors, String desc, int star, int fork) {
        BufferedImage poster = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        // TODO:
        Graphics2D g = poster.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 500, 500);// 填充整个屏幕

        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
        g.setColor(new Color(255, 255, 255));
        g.drawString(title, 50, 20);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// 消除画图锯齿
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);// 消除文字锯齿
        g.dispose();

        return poster;
    }

    public static void main(String[] args) {
        String outputPath = "./poster.jpg";
        BufferedImage poster = generateGithubPoster("four-concealed-triplets-hackathon",
                new String[]{"cli", "FLL", "Ruku", "cap"},
                "Hackathon2020@eastchina，Team Four Concealed Triplets. A website that can embed website content into a simple image (supporting Github, CNKI, Twitter, Spotify)",
                100, 5);
        try {
            ImageIO.write(poster, "jpg", new File(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
