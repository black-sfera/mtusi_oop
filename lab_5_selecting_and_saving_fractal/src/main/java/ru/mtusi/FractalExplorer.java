package ru.mtusi;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
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

        JPanel panelNorth = new JPanel();
        JLabel label = new JLabel();
        label.setText("Fractals: ");

        JComboBox<FractalGenerator> comboBox = new JComboBox();
        comboBox.addItem(new Mandelbrot());
        comboBox.addItem(new Tricorn());
        comboBox.addItem(new BurningShip());
        comboBox.addActionListener(e -> {
            fractal = (FractalGenerator) comboBox.getSelectedItem();
            fractal.getInitialRange(range);
            drawFractal();
        });

        panelNorth.add(label);
        panelNorth.add(comboBox);

        contentPane.add(panelNorth, BorderLayout.NORTH);

        JPanel panelSouth = new JPanel();

        JButton saveImage = new JButton("Save Image");
        saveImage.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
            chooser.setFileFilter(filter);
            chooser.setAcceptAllFileFilterUsed(false);

            int i = chooser.showSaveDialog(frame);
            if (i == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();

                try {
                    ImageIO.write(imageDisplay.getImage(), "png", file);
                } catch (IOException ioException) {
                    ioException.printStackTrace();

                    JOptionPane.showMessageDialog(frame, ioException.getMessage(),"Cannot Save Image", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton findPathButton = new JButton("Return");
        findPathButton.addActionListener(e -> {
            fractal.getInitialRange(range);
            drawFractal();
        });

        panelSouth.add(saveImage);
        panelSouth.add(findPathButton);

        contentPane.add(panelSouth, BorderLayout.SOUTH);

        imageDisplay.repaint();

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal() {
        imageDisplay.clearImage();
        for (int x = 0; x < sizeDisplay; x++) {
            for (int y = 0; y < sizeDisplay; y++) {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width,
                        sizeDisplay, x);
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
        FractalExplorer fractalExplorer = new FractalExplorer(800);
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal();
    }

}