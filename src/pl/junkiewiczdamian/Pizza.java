package pl.junkiewiczdamian;

import pl.junkiewiczdamian.frame.FieldType;
import pl.junkiewiczdamian.frame.SnakeMap;
import pl.junkiewiczdamian.snake.Snake;
import pl.junkiewiczdamian.snake.SnakePart;

import java.util.Random;

public class Pizza {
    private int x;
    private int y;
    private Random rand;

    public Pizza(){
        rand = new Random();
    }

    public void generateNewPosition(SnakeMap snakeMap){
        int size = snakeMap.getMapSize();
        boolean isPointOK = false;
        while(!isPointOK){
            int x = rand.nextInt(size-2)+1;
            int y = rand.nextInt(size-2)+1;
            if (snakeMap.getFieldType(x,y) != FieldType.SNAKE_BODY &&
                    snakeMap.getFieldType(x,y) != FieldType.SNAKE_HEAD) {
                this.setPosition(x,y);
                snakeMap.setFieldType(x,y,FieldType.PIZZA);
                isPointOK = true;
            }
        }
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
