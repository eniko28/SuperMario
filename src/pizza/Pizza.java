package pizza;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pizza {
    public int x;
    public int y;
    public int speed;
    public int size;

    private BufferedImage image;

    public Pizza() {
        this.x = 0;
        this.y = 0;
        this.speed = 0;
        this.size = 0;

        try {
            image = ImageIO.read(new File("images/pizza.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, size, size, null);
    }
}
