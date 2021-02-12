package ru.mtusi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.logging.Logger;

public class FractalExplorer {

    Logger logger = Logger.getLogger(FractalExplorer.class.getSimpleName());

    private int sizeDisplay;

    private JImageDisplay imageDisplay;

    private FractalGenerator fractal;

    private Rectangle2D.Double range;

    public FractalExplorer(int sizeDisplay) {
        this.sizeDisplay = sizeDisplay;
        this.imageDisplay = new JImageDisplay(sizeDisplay, sizeDisplay);
        this.fractal = new Mandelbrot();
        this.range = new Rectangle2D.Double();
        this.fractal.getInitialRange(range);
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Fractal Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        imageDisplay.addMouseListener(new MouseHandler());

        contentPane.add(imageDisplay, BorderLayout.CENTER);

        logger.info("add imageDisplay");

        JButton findPathButton = new JButton("Return");
        findPathButton.addActionListener(e -> {
            fractal.getInitialRange(range);
            drawFractal();
        });
        contentPane.add(findPathButton, BorderLayout.SOUTH);

        imageDisplay.repaint();

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal() {
        imageDisplay.clearImage();
        for (int x = 0; x < sizeDisplay; x++) {
            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width,
                    sizeDisplay, x);
            for (int y = 0; y < sizeDisplay; y++) {
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height,
                        sizeDisplay, y);

                int numIters = fractal.numIterations(xCoord, yCoord);

                if (numIters == -1) {
                    imageDisplay.drawPixel(x, y, 0);
                } else {
                    float hue = 0.7f + (float) numIters / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    imageDisplay.drawPixel(x, y, rgbColor);
                }
            }
        }
        imageDisplay.repaint();
        logger.info("fractal draw");
    }

    private class MouseHandler extends MouseAdapter implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width,
                    sizeDisplay, e.getX());
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height,
                    sizeDisplay, e.getY());

            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5d);
            drawFractal();
        }
    }

    public static void main(String[] args) {
        FractalExplorer fractalExplorer = new FractalExplorer(400);
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal();
    }

}
