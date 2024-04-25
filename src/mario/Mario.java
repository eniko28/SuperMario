package mario;

import javax.swing.*;

public class Mario {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mario Game");

        frame.setBounds(0, 0, 3000, 2400);

        Game game = new Game();
        frame.add(game);
        game.startMarioGame();

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.setVisible(true);
    }
}