package player;

import mario.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gamer {
    Game game;
    Keys key;

    public int x;
    public int y;
    public int speed;

    public BufferedImage image;

    public Gamer(Game game, Keys key) {
        this.game = game;
        this.key = key;
        setPlayer();
        marioImage();
        marioSong();
    }

    public void setPlayer() {
        x = 1400;
        y = 700;
        speed = 20;
    }
    public void marioImage() {
        File ph = new File("images/ujmario.png");
        this.image = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        try {
            image = ImageIO.read(ph);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void marioSong() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("music/mariosong.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("IO error");
        }
    }

    public void deadSong() {
        try {
                AudioInputStream audio2 = AudioSystem.getAudioInputStream(new File("music/zene.wav"));
                Clip clip2 = AudioSystem.getClip();
                clip2.open(audio2);
                clip2.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("IO error");
        }
    }

    public void update() {
        if (key.up && y - 10 > 0) {
            y -= speed;
        } else if (key.down && y + 70 + 50 < game.getHeight()) {
            y += speed;
        } else if (key.left && x - 10 > 0) {
            x -= speed;
        } else if (key.right && x + 30 + 50 < game.getWidth()) {
            x += speed;
        }
        for (pizza.Pizza pizza : game.pizzaGame.pizzas) {
            if (pizza.x >= x && pizza.x <= x + 80 && pizza.y >= y && pizza.y <= y + 80) {
                game.catchPizza();
                deadSong();
            } else {
                if (x == 0 && y == 0) {
                    game.winner();
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, 80, 80, null);
    }
}
