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

    public SnakeMap(int size) {
        this.mapSize = size;

        this.setPreferredSize(new Dimension(900,900));
        this.setBackground(Color.BLUE);
        this.setLayout(null);

        JPanel map = new JPanel();
        map.setBounds(0,0,900,900);
        map.setLayout(new GridLayout(mapSize,mapSize));
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
                fields[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
                        }
                        case EMPTY -> {
                            fields[i][j].setBackground(Color.gray);
                        }
                        case SNAKE_HEAD -> {
                            fields[i][j].setBackground(Color.blue);
                        }
                        case SNAKE_BODY -> {
                            fields[i][j].setBackground(Color.yellow);
                        }
                        case PIZZA -> {
                            fields[i][j].setBackground(Color.red);
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
