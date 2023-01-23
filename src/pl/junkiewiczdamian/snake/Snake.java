package pl.junkiewiczdamian.snake;

import pl.junkiewiczdamian.frame.FieldType;
import pl.junkiewiczdamian.frame.SnakeMap;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Snake implements KeyListener {
    private SnakePart head;
    private SnakeBody body;
    private String currentDirection;
    private char currentForbiddenKey1;
    private int currentForbiddenKey2;
    private int x_r;
    private int y_r;

    public Snake() {
        init();
    }

    public void move(SnakeMap snakeMap){
        int index = getBodyParts().size()-1;
        x_r = getBodyParts().get(index).getX();
        y_r = getBodyParts().get(index).getY();
        snakeMap.setFieldType(x_r,y_r, FieldType.EMPTY);
        body.moveTowards(head);
        int x = head.getX();
        int y = head.getY();
        switch (currentDirection){
            case "left" -> {
                head.setPosition(x-1,y);
            }
            case "right" -> {
                head.setPosition(x+1,y);
            }
            case "up" -> {
                head.setPosition(x,y-1);
            }
            case "down" -> {
                head.setPosition(x,y+1);
            }
        }
        snakeMap.updateSnakeIcon(currentDirection);

        snakeMap.setFieldType(head.getX(),head.getY(), FieldType.SNAKE_HEAD);
        x = getBodyParts().get(0).getX();
        y = getBodyParts().get(0).getY();
        snakeMap.setFieldType(x,y, FieldType.SNAKE_BODY);
    }

    public void expandBody(){
        body.expandBody(x_r,y_r);
    }

    public SnakePart getHead() {
        return head;
    }

    public List<SnakePart> getBodyParts() {
        return body.getBodyParts();
    }

    public void setDirection(String newDirection){
        currentDirection = newDirection;
    }
    public void init(){
        head = new SnakePart(10,11);
        body = new SnakeBody(head);
        currentDirection = "right";
        currentForbiddenKey1 = 'a';
        currentForbiddenKey2 = 37;
    }

    public void updateForbiddenMoves() {
        switch (currentDirection){
            case "up" -> {
                currentForbiddenKey1 = 's';
                currentForbiddenKey2 = 40;
            }
            case "down" -> {
                currentForbiddenKey1 = 'w';
                currentForbiddenKey2 = 38;
            }
            case "left" -> {
                currentForbiddenKey1 = 'd';
                currentForbiddenKey2 = 39;
            }
            case "right" -> {
                currentForbiddenKey1 = 'a';
                currentForbiddenKey2 = 37;
            }
        }
    }

    public String getCurrentDirection() {
        return currentDirection;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar()!=currentForbiddenKey1) {
            switch (e.getKeyChar()) {
                case 'w' -> {
                    setDirection("up");
                }
                case 's' -> {
                    setDirection("down");
                }
                case 'a' -> {
                    setDirection("left");
                }
                case 'd' -> {
                    setDirection("right");
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()!=currentForbiddenKey2) {
            switch (e.getKeyCode()) {
                case 38 -> {
                    setDirection("up");
                }
                case 40 -> {
                    setDirection("down");
                }
                case 37 -> {
                    setDirection("left");
                }
                case 39 -> {
                    setDirection("right");
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
