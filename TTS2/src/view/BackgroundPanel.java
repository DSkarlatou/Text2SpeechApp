package view;

/* draws the background image*/
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BackgroundPanel extends JPanel {
    private BufferedImage image;

    public BackgroundPanel(BufferedImage image) {
        this.image = image;  
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), 200, this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    } 
}