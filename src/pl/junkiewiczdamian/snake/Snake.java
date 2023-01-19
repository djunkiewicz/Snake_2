package pl.junkiewiczdamian.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Snake implements KeyListener {
    private final SnakePart head;
    private final SnakeBody body;
    private String currentDirection;

    public Snake() {
        head = new SnakePart(10,11);
        body = new SnakeBody(head);
        currentDirection = "right";
    }

    public void move(){
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 38 -> setDirection("up");
            case 40 -> setDirection("down");
            case 37 -> setDirection("left");
            case 39 -> setDirection("right");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
