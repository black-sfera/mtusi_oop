package ru.mtusi;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

public class JImageDisplay extends JComponent {

    Logger logger = Logger.getLogger(JImageDisplay.class.getSimpleName());

    private BufferedImage image;

    public JImageDisplay(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        super.setPreferredSize(new Dimension(width, height));
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        logger.info("print image");
    }

    public void clearImage() {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.setRGB(i, j, 0);
            }
        }
        logger.info("clear image");
    }

    public void drawPixel(int x, int y, int rgbColor) {
        image.setRGB(x, y, rgbColor);
    }

}
