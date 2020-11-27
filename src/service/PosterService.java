package service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class PosterService {
    /**
     * generate poster for Github repository
     */
    private static BufferedImage generateGithubPoster(String url, String title, String authors, String desc, int star, int fork) throws IOException {
        BufferedImage poster = new BufferedImage(1000, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = poster.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // fill background
        BufferedImage bg = ImageIO.read(new File("./public/img/dark_bg.jpg"));
        g.drawImage(bg.getScaledInstance(bg.getWidth(), bg.getHeight(), Image.SCALE_SMOOTH), 0, 0, 1000, 600, null);

        // add title information
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 40));
        g.setColor(new Color(255, 255, 255));
        g.drawString(title, 60, 80);

        // add other information
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 30));
        g.setColor(new Color(255, 255, 255));
        g.drawString("Authors: " + authors, 60, 180);
        g.drawString("Description: " + desc, 60, 230);
        g.drawString("stars: " + star, 60, 500);
        g.drawString("forks: " + fork, 240, 500);

        // create qr code
        BufferedImage qrCode = createQrCode(url, 200, 200);
        g.drawImage(qrCode.getScaledInstance(qrCode.getWidth(), qrCode.getHeight(), Image.SCALE_SMOOTH), 750, 40, 200, 200, null);

        // release resource
        g.dispose();

        return poster;
    }

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    public static BufferedImage createQrCode(String url, int width, int height) {
        try {
            Map<EncodeHintType, String> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
                }
            }
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

        String outputPath = "./public/img/poster.jpg";

        try {
            BufferedImage poster = generateGithubPoster(
                    "https://github.com/CappuccinoCup/MyChair",
                    "MyChair",
                    "CappuccinoCup",
                    "vue-cli3 front end for 2020 SE",
                    0,
                    0
            );
            ImageIO.write(poster, "jpg", new File(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
