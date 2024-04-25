package pizza;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PizzaGame {
    private int WINDOW_WIDTH = 3000;

    private int WINDOW_HEIGHT = 2400;
    private int MIN_SPEED = 5;
    private int MAX_SPEED = 10;
    public ArrayList<Pizza> pizzas;
    public Random random;

    public PizzaGame() {
        pizzas = new ArrayList<>();
        random = new Random();
    }

    public void update() {
        for (int i = 0; i < pizzas.size(); i += 2) {
            Pizza pizza = pizzas.get(i);
            pizza.y += pizza.speed;
        }
        createPizza();
    }

    public void render(Graphics2D g2) {
        for (Pizza pizza : pizzas) {
            pizza.draw(g2);
        }
    }

    private void createPizza() {
        Pizza pizza = new Pizza();
        pizza.x = random.nextInt(WINDOW_WIDTH);
        pizza.y = -100;
        pizza.speed = MIN_SPEED + random.nextInt(MAX_SPEED - MIN_SPEED + 1);
        pizza.size = 50;

        pizzas.add(pizza);
    }
}


