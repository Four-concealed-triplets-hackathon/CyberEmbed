package service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class PosterService {
    /**
     * generate poster for Github repository
     */
    public static BufferedImage generateGithubPoster(String url, String title, String author, String desc, String star, String fork) throws IOException {
        BufferedImage embed = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = embed.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // fill background
        BufferedImage bg = ImageIO.read(new File("./public/img/resources/embed_github_bg.png"));
        g.drawImage(bg.getScaledInstance(bg.getWidth(), bg.getHeight(), Image.SCALE_SMOOTH), 0, 0, 600, 600, null);

        // add author information
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 25));
        g.setColor(Color.BLACK);
        g.drawString(author, 40, 133);

        // add title information
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 50));
        g.setColor(Color.BLACK);
        g.drawString(title, 40, 203);

        // add other information
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
        g.setColor(Color.BLACK);

        FontMetrics fontMetrics = g.getFontMetrics();
        ArrayList<String> descriptions = new ArrayList<>();
        int lineNumLimit = 5;
        int lineWidthLimit = 500;
        int lineNum = 0;
        int charIndex = 0;
        while (lineNum < lineNumLimit) {
            StringBuilder s = new StringBuilder();
            while (charIndex < desc.length()) {
                if (fontMetrics.stringWidth(s.append(desc.charAt(charIndex++)).toString()) >= lineWidthLimit) {
                    break;
                }
            }
            descriptions.add(s.toString());
            lineNum++;
        }
        if (charIndex < desc.length() - 1) {
            String lastStr = descriptions.get(descriptions.size() - 1);
            descriptions.set(descriptions.size() - 1, lastStr.substring(0, lastStr.length() - 2) + "...");
        }

        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
        g.setColor(Color.BLACK);
        g.drawString("About", 40, 250);
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
        for (int i = 0; i < descriptions.size(); i++) {
            String s = descriptions.get(i);
            g.drawString(descriptions.get(i), 40, 280 + (i * 27));
        }

        BufferedImage starIcon = ImageIO.read(new File("./public/img/resources/star.png"));
        g.drawImage(starIcon.getScaledInstance(starIcon.getWidth(), starIcon.getHeight(), Image.SCALE_SMOOTH), 40, 425, 30, 30, null);
        g.drawString("Stars  " + star, 75, 447);
        BufferedImage forkIcon = ImageIO.read(new File("./public/img/resources/fork.png"));
        g.drawImage(forkIcon.getScaledInstance(forkIcon.getWidth(), forkIcon.getHeight(), Image.SCALE_SMOOTH), 40, 455, 30, 30, null);
        g.drawString("Forks  " + fork, 75, 477);

        g.setFont(new Font("Microsoft YaHei", Font.ITALIC, 15));
        g.setColor(Color.GRAY);
        g.drawString("github.com", 40, 545);
        g.drawString("© 2020 CyberEmbed, Inc. All Rights Preserved", 40, 575);

        // create qr code
        BufferedImage qrCode = createQrCode(url, 300, 300, "./public/img/resources/github_logo.png");
        g.drawImage(qrCode.getScaledInstance(qrCode.getWidth(), qrCode.getHeight(), Image.SCALE_SMOOTH), 425, 425, 150, 150, null);

        // release resource
        g.dispose();

        return embed;
    }

    public static BufferedImage generateCnkiPoster(String url, String title, String authors, String abs, String keyWords, String download, String page) {
        BufferedImage embed = new BufferedImage(1000, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = embed.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

//        // fill background
//        BufferedImage bg = ImageIO.read(new File("./public/img/git_bg.jpg"));
//        g.drawImage(bg.getScaledInstance(bg.getWidth(), bg.getHeight(), Image.SCALE_SMOOTH), 0, 0, 1000, 600, null);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 600);

        // add title information
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 40));
        g.setColor(new Color(255, 255, 255));
        g.drawString(title, 60, 80);

        // add other information
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 30));
        g.setColor(new Color(255, 255, 255));

        g.drawString("Authors: ", 60, 180);
        g.drawString(authors, 90, 210);

        g.drawString("Key words: " + keyWords, 60, 240);

        FontMetrics fontMetrics = g.getFontMetrics();
        ArrayList<String> descriptions = new ArrayList<>();
        int lineNumLimit = 5;
        int lineWidthLimit = 500;
        int lineNum = 0;
        int charIndex = 0;
        while (lineNum < lineNumLimit) {
            StringBuilder s = new StringBuilder();
            while (charIndex < abs.length()) {
                if (fontMetrics.stringWidth(s.append(abs.charAt(charIndex++)).toString()) >= lineWidthLimit) {
                    break;
                }
            }
            descriptions.add(s.toString());
            lineNum++;
        }
        if (charIndex < abs.length() - 1) {
            String lastStr = descriptions.get(descriptions.size() - 1);
            descriptions.set(descriptions.size() - 1, lastStr.substring(0, lastStr.length() - 2) + "...");
        }

        g.drawString("Description: ", 60, 240);
        for (int i = 0; i < descriptions.size(); i++) {
            String s = descriptions.get(i);
            g.drawString(descriptions.get(i), 90, 270 + (i * 30));
        }

        g.drawString("downloads: " + download, 60, 500);
        g.drawString("pages: " + page, 240, 500);

        // create qr code
        BufferedImage qrCode = createQrCode(url, 300, 300, "./public/img/cnki_logo.png");
        g.drawImage(qrCode.getScaledInstance(qrCode.getWidth(), qrCode.getHeight(), Image.SCALE_SMOOTH), 750, 40, 200, 200, null);

        // release resource
        g.dispose();

        return embed;
    }

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private static BufferedImage createQrCode(String url, int width, int height, String logoPath) {
        try {
            HashMap hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.MARGIN, 1);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            Image logo = ImageIO.read(new File(logoPath));
            int logoWidth = Math.min(logo.getWidth(null), image.getWidth() * 2 / 10);
            int logoHeight = Math.min(logo.getHeight(null), image.getHeight() * 2 / 10);
            int logoX = (image.getWidth() - logoWidth) / 2;
            int logoY = (image.getHeight() - logoHeight) / 2;

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
                }
            }

            Graphics2D graphics = image.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            graphics.drawImage(logo, logoX, logoY, logoWidth, logoHeight, null);

            graphics.setStroke(new BasicStroke(5));
            graphics.setColor(Color.WHITE);
            graphics.drawRect(logoX, logoY, logoWidth, logoHeight);

            graphics.dispose();

            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * an easy poster demo
     */
    public static void main(String[] args) {

        String outputPath = "./public/img/github_embed_demo.jpg";

        try {
            BufferedImage poster = generateGithubPoster(
                    "https://github.com/zxing/zxing",
                    "zxing",
                    "zxing",
                    "ZXing (\"Zebra Crossing\") barcode scanning library for Java, Android",
                    "26.7k",
                    "8.8k"
            );
            ImageIO.write(poster, "jpg", new File(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
