package mario;

import pizza.PizzaGame;
import player.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;


public class Game extends JPanel implements Runnable {

    Thread threadMario;
    Keys key = new Keys();

    public PizzaGame pizzaGame = new PizzaGame();

    public boolean pizzaCount = false;
    public boolean win = false;

    Gamer game = new Gamer(this, key);

    public Game() {

        setBounds(0, 0, 3000, 2400);

        setBackground(Color.CYAN);

        addKeyListener(key);

        setFocusable(true);
    }

    public void startMarioGame() {
        threadMario = new Thread(this);
        threadMario.start();
    }

    @Override
    public void run() {
        while (!pizzaCount && !win) {
            update();
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }

    public void update() {
        game.update();
        pizzaGame.update();
    }

    public void catchPizza() {
        pizzaCount = true;
    }

    public void winner() {
        win = true;
    }

    public void render(Graphics2D g2) {
        if (pizzaCount) {
            pizzaGame.render(g2);

            Font font = new Font("Arial", Font.PLAIN, 100);
            g2.setFont(font);
            FontMetrics metrics = g2.getFontMetrics();
            Rectangle2D bounds = metrics.getStringBounds("Game over!:(", g2);

            int width = (int) bounds.getWidth();
            int height = (int) bounds.getHeight();

            g2.setColor(Color.BLACK);
            g2.fillRect(400, 500 - height, width, height);

            g2.setColor(Color.WHITE);
            g2.drawString("Game over!:(", 400, 500);
        } else {
            if (win) {
                pizzaGame.render(g2);

                Font font = new Font("Arial", Font.PLAIN, 100);
                g2.setFont(font);
                FontMetrics metrics = g2.getFontMetrics();
                Rectangle2D bounds = metrics.getStringBounds("Congratulations!:)", g2);

                int width = (int) bounds.getWidth();
                int height = (int) bounds.getHeight();

                g2.setColor(Color.BLACK);
                g2.fillRect(400, 500 - height, width, height);

                g2.setColor(Color.WHITE);
                g2.drawString("Congratulations!:)", 400, 500);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        game.draw(g2);
        pizzaGame.render(g2);
        render(g2);
       // g2.dispose();
    }
}
