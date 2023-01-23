package pl.junkiewiczdamian.frame;

import pl.junkiewiczdamian.Pizza;
import pl.junkiewiczdamian.snake.Snake;
import pl.junkiewiczdamian.snake.SnakePart;

import javax.swing.*;
import java.awt.*;

public class SnakeMap extends JLayeredPane {
    private final SnakeMapField[][] fields;
    private final JLabel auxLabel;
    private final int mapSize;
    private final ImageIcon snakeHeadIconR, snakeHeadIconL,snakeHeadIconU,snakeHeadIconD;
    private ImageIcon currentSnakeHeadIcon;
    private final ImageIcon pizzaIcon;
    private final ImageIcon snakeBodyIcon;
    private final Color backgroundColor;

    public SnakeMap(int size) {
        snakeHeadIconR = new ImageIcon("icons\\snake_head_right.png");
        snakeHeadIconL = new ImageIcon("icons\\snake_head_left.png");
        snakeHeadIconU = new ImageIcon("icons\\snake_head_up.png");
        snakeHeadIconD = new ImageIcon("icons\\snake_head_down.png");
        currentSnakeHeadIcon = snakeHeadIconR;
        snakeBodyIcon = new ImageIcon("icons\\snake_body.png");
        pizzaIcon = new ImageIcon("icons\\pizza.png");
        backgroundColor = new Color(232, 255, 236);

        this.mapSize = size;

        this.setPreferredSize(new Dimension(900,900));
        this.setBackground(Color.BLUE);
        this.setLayout(null);

        JPanel map = new JPanel();
        map.setBounds(0,0,900,900);
        map.setLayout(new GridLayout(mapSize,mapSize,0,0));
        this.add(map, Integer.valueOf(0));

        JPanel secondaryScreen = new JPanel();
        secondaryScreen.setBounds(0,0,900,900);
        secondaryScreen.setLayout(new GridLayout(mapSize,mapSize));
        this.add(secondaryScreen, Integer.valueOf(1));

        fields = new SnakeMapField[mapSize][mapSize];
        for (int i = 0; i<mapSize; i++){
            for (int j = 0; j<mapSize; j++){
                if (i == 0 || i == mapSize-1 || j == 0 || j == mapSize-1){
                    fields[i][j] = new SnakeMapField(FieldType.BORDER);
                } else {
                    fields[i][j] = new SnakeMapField(FieldType.EMPTY);
                }
                fields[i][j].setBorder(BorderFactory.createLineBorder(Color.gray));
                fields[i][j].setOpaque(true);
                map.add(fields[i][j]);
            }
        }

        auxLabel = new JLabel();
        auxLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 90));
        auxLabel.setOpaque(false);
        auxLabel.setVerticalAlignment(SwingConstants.CENTER);
        auxLabel.setHorizontalAlignment(SwingConstants.CENTER);
        secondaryScreen.setLayout(new BorderLayout());
        secondaryScreen.setOpaque(false);
        secondaryScreen.add(auxLabel);
    }

    public void clean(){
        for (int i = 0; i<mapSize; i++){
            for (int j = 0; j<mapSize; j++){
                if (i == 0 || i == mapSize-1 || j == 0 || j == mapSize-1){
                    fields[i][j].setFieldType(FieldType.BORDER);
                } else {
                    fields[i][j].setFieldType(FieldType.EMPTY);
                }
            }
        }
    }

    public void setFieldType(int x, int y, FieldType type){
        fields[y][x].setFieldType(type);
    }

    public FieldType getFieldType(int x, int y){
        return fields[y][x].getFieldType();
    }

    public void setPopUpText(String text){
        auxLabel.setText(text);
    }

    public void repaint() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if(fields!=null) {
                    switch (fields[i][j].getFieldType()) {
                        case BORDER -> {
                            fields[i][j].setBackground(Color.darkGray);
                            fields[i][j].setIcon(null);
                        }
                        case EMPTY -> {
                            fields[i][j].setBackground(backgroundColor);
                            fields[i][j].setIcon(null);
                        }
                        case SNAKE_HEAD -> {
                            fields[i][j].setBackground(backgroundColor);
                            fields[i][j].setIcon(currentSnakeHeadIcon);
                            //fields[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                            //fields[i][j].setVerticalAlignment(SwingConstants.CENTER);
                        }
                        case SNAKE_BODY -> {
                            fields[i][j].setBackground(backgroundColor);
                            fields[i][j].setIcon(snakeBodyIcon);
                        }
                        case PIZZA -> {
                            fields[i][j].setBackground(backgroundColor);
                            fields[i][j].setIcon(pizzaIcon);
                        }
                    }
                }
            }
        }

    }
    public void update(Pizza pizza){
        int x,y;
        x = pizza.getX();
        y = pizza.getY();
        fields[y][x].setFieldType(FieldType.PIZZA);
    }

    public void updateSnakeIcon(String currentSnakeDirection){
        switch (currentSnakeDirection){
            case "right" -> currentSnakeHeadIcon = snakeHeadIconR;
            case "left" -> currentSnakeHeadIcon = snakeHeadIconL;
            case "up" -> currentSnakeHeadIcon = snakeHeadIconU;
            case "down" -> currentSnakeHeadIcon = snakeHeadIconD;
        }
    }

    public void update(Snake snake){
        int x,y;
        for (SnakePart temp: snake.getBodyParts()) {
            x = temp.getX();
            y = temp.getY();
            fields[y][x].setFieldType(FieldType.SNAKE_BODY);
        }
        x = snake.getHead().getX();
        y = snake.getHead().getY();
        fields[y][x].setFieldType(FieldType.SNAKE_HEAD);
    }

    public void update(Snake snake, Pizza pizza){
        int x,y;
        for (SnakePart temp: snake.getBodyParts()) {
            x = temp.getX();
            y = temp.getY();
            fields[y][x].setFieldType(FieldType.SNAKE_BODY);
        }
        x = snake.getHead().getX();
        y = snake.getHead().getY();
        fields[y][x].setFieldType(FieldType.SNAKE_HEAD);

        x = pizza.getX();
        y = pizza.getY();
        fields[y][x].setFieldType(FieldType.PIZZA);
    }

    public int getMapSize() {
        return mapSize;
    }

}
