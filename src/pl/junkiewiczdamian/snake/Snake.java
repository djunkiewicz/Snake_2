package pl.junkiewiczdamian.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Snake implements KeyListener {
    private SnakePart head;
    private SnakeBody body;
    private String currentDirection;

    public Snake() {
        init();
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
    public void init(){
        head = new SnakePart(10,11);
        body = new SnakeBody(head);
        currentDirection = "right";
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w' -> setDirection("up");
            case 's' -> setDirection("down");
            case 'a' -> setDirection("left");
            case 'd' -> setDirection("right");
        }
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
