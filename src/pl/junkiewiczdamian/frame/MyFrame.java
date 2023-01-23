package pl.junkiewiczdamian.frame;

import pl.junkiewiczdamian.Pizza;
import pl.junkiewiczdamian.snake.Snake;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private SnakeMap snakeMap;
    private MenuPanel menuPanel;

    public MyFrame(String title) {
        super(title);
        snakeMap = new SnakeMap(18);
        menuPanel = new MenuPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        GridBagLayout g = new GridBagLayout();
        this.setLayout(g);

        this.add(snakeMap);
        this.add(menuPanel);

        ///////////////////////////////////////////////////
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void repaintSnakeMap(){
        snakeMap.repaint();
    }
    public void updateSnakeMap(Snake snake){
        snakeMap.update(snake);
    }
    public void updateSnakeMap(Pizza pizza){
        snakeMap.update(pizza);
    }
    public void updateSnakeMap(Snake snake, Pizza pizza){
        snakeMap.update(snake, pizza);
    }
    public int getSnakeMapSize(){
        return snakeMap.getMapSize();
    }
    public SnakeMap getSnakeMap() {
        return snakeMap;
    }
    public MenuPanel getMenuPanel() {
        return menuPanel;
    }
}
