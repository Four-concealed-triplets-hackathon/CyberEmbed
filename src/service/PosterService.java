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

        desc = desc.substring(0, Math.min(150, desc.length()));
        String[] words = desc.split("\\s+");
        ArrayList<String> descriptions = new ArrayList<>();
        int lineLimit = 30;
        int lineNum = 0;
        StringBuilder s = new StringBuilder();
        int wordsIndex = 0;
        while (wordsIndex < words.length) {
            while (wordsIndex < words.length && lineNum + words[wordsIndex].length() < lineLimit) {
                s.append(words[wordsIndex]).append(" ");
                lineNum += words[wordsIndex++].length();
            }
            descriptions.add(s.toString());
            lineNum = 0;
            s = new StringBuilder();
        }

        // add other information
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 30));
        g.setColor(new Color(255, 255, 255));

        g.drawString("Authors: ", 60, 180);
        g.drawString(author, 90, 210);

        g.drawString("Description: ", 60, 240);
        for (int i = 0; i < descriptions.size(); i++) {
            g.drawString(descriptions.get(i), 90, 270 + (i * 30));
        }

        g.drawString("stars: " + star, 60, 500);
        g.drawString("forks: " + fork, 240, 500);

        // create qr code
        BufferedImage qrCode = createQrCode(url, 300, 300, "./public/img/github_logo.png");
        g.drawImage(qrCode.getScaledInstance(qrCode.getWidth(), qrCode.getHeight(), Image.SCALE_SMOOTH), 750, 40, 200, 200, null);

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

        abs = abs.substring(0, Math.min(150, abs.length()));
        String[] words = abs.split("\\s+");
        ArrayList<String> abstracts = new ArrayList<>();
        int lineLimit = 30;
        int lineNum = 0;
        StringBuilder s = new StringBuilder();
        int wordsIndex = 0;
        while (wordsIndex < words.length) {
            while (wordsIndex < words.length && lineNum + words[wordsIndex].length() < lineLimit) {
                s.append(words[wordsIndex]).append(" ");
                lineNum += words[wordsIndex++].length();
            }
            abstracts.add(s.toString());
            lineNum = 0;
            s = new StringBuilder();
        }

        // add other information
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 30));
        g.setColor(new Color(255, 255, 255));

        g.drawString("Authors: ", 60, 180);
        g.drawString(authors, 90, 210);

        g.drawString("Key words: " + keyWords, 60, 240);

        g.drawString("Abstract: ", 60, 270);
        for (int i = 0; i < abstracts.size(); i++) {
            g.drawString(abstracts.get(i), 90, 300 + (i * 30));
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

//            Image logo = ImageIO.read(new File(logoPath));
//            int logoWidth = Math.min(logo.getWidth(null), image.getWidth() * 2 / 10);
//            int logoHeight = Math.min(logo.getHeight(null), image.getHeight() * 2 / 10);
//            int logoX = (image.getWidth() - logoWidth) / 2;
//            int logoY = (image.getHeight() - logoHeight) / 2;

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
                }
            }

            Graphics2D graphics = image.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

//            graphics.drawImage(logo, logoX, logoY, logoWidth, logoHeight, null);
//
//            graphics.setStroke(new BasicStroke(5));
//            graphics.setColor(Color.WHITE);
//            graphics.drawRect(logoX, logoY, logoWidth, logoHeight);

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

        String outputPath = "./public/img/embed.jpg";

        try {
            BufferedImage poster = generateGithubPoster(
                    "https://github.com/CappuccinoCup/MyChair",
                    "MyChair",
                    "CappuccinoCup",
                    "vue-cli3 front end for 2020 SE, more words needed more words needed more words needed more words needed more words needed more words needed more words needed more words needed more words needed more words needed...",
                    "0",
                    "0"
            );
            ImageIO.write(poster, "jpg", new File(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
