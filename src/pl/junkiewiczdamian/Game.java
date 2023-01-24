package pl.junkiewiczdamian;

import pl.junkiewiczdamian.frame.MyFrame;
import pl.junkiewiczdamian.snake.Snake;
import pl.junkiewiczdamian.snake.SnakePart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements Runnable, ActionListener {
    private Thread gameThread;
    private final MyFrame myFrame;
    private final Snake snake;
    private final Pizza pizza;
    private boolean runningGameCondition;
    private boolean shouldStartCounting;
    private int speed;
    private int points;

    public Game() {
        snake = new Snake();
        pizza = new Pizza();
        speed = 200;
        points = 0;
        shouldStartCounting = true;
        myFrame = new MyFrame("Snake 2.0");
        myFrame.addKeyListener(snake);
        myFrame.setFocusable(true);
        myFrame.getMenuPanel().getStartButton().addActionListener(this);
        myFrame.getMenuPanel().getPauseButton().addActionListener(this);
        myFrame.getMenuPanel().getRestartButton().addActionListener(this);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        myFrame.updateSnakeMap(snake);
        pizza.generateNewPosition(myFrame.getSnakeMap());
        repaint();

        while (gameThread != null) {
            while (runningGameCondition) {
                if (shouldStartCounting){
                    myFrame.setFocusable(false);
                    startSequence();
                    myFrame.setFocusable(true);
                }
                myFrame.requestFocusInWindow();
                //////////////// START
                update();
                if (checkCollision()) {
                    runningGameCondition = false;
                    myFrame.getSnakeMap().setPopUpText("Game Over");
                } else {
                    repaint();
                }
                //////////////// END
                try {
                    Thread.sleep(speed);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void update() {
        snake.move(myFrame.getSnakeMap());
        snake.updateForbiddenMoves();
        checkPizza();
    }

    private void checkPizza(){
        if (pizza.getX() == snake.getHead().getX() && pizza.getY() == snake.getHead().getY()){
            snake.expandBody();
            myFrame.getSnakeMap().update(snake);
            myFrame.repaintSnakeMap();
            pizza.generateNewPosition(myFrame.getSnakeMap());
            updatePoints();
            increaseSpeed();
        }
    }
    private void updatePoints(){
        points+=10;
        myFrame.getMenuPanel().setPoints(points);
    }

    private void increaseSpeed(){
        if (points<=100){
            speed -= 6;
        } else if (points <= 150) {
            speed -= 4;
        } else if (points <= 350) speed = 110;
        else if (points <= 450) speed = 100;
        else if (points <= 550) speed = 90;
        else if (points <= 750) speed = 85;
        else if (points <= 850) speed = 75;
        else if (points <= 950) speed = 70;
        else speed = 65;
    }

    private void repaint() {
        myFrame.repaintSnakeMap();
    }
    private boolean checkCollision(){
        boolean collision = false;
        int x = snake.getHead().getX();
        int y = snake.getHead().getY();
        int size = myFrame.getSnakeMapSize();
        for (SnakePart temp: snake.getBodyParts()) {
            if (temp.getX() == x && temp.getY() == y) {
                collision = true;
                break;
            }
        }
        if (x == 0 || x == size - 1 || y == 0 || y == size - 1){
            collision = true;
        }
        return collision;
    }

    private void startSequence(){
        if (!checkCollision()){
            runningGameCondition = true;
        }
        myFrame.getSnakeMap().setPopUpText("3");
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        myFrame.getSnakeMap().setPopUpText("2");
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        myFrame.getSnakeMap().setPopUpText("1");
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        myFrame.getSnakeMap().setPopUpText(null);
        shouldStartCounting = false;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myFrame.getMenuPanel().getStartButton()){
            if (!checkCollision()){
                runningGameCondition = true;
            }
        }
        if (e.getSource() == myFrame.getMenuPanel().getPauseButton()){
            runningGameCondition = false;
            shouldStartCounting = true;
        }
        if (e.getSource() == myFrame.getMenuPanel().getRestartButton()){
            myFrame.getSnakeMap().clean();
            snake.init();
            myFrame.updateSnakeMap(snake);
            myFrame.getSnakeMap().updateSnakeIcon(snake.getCurrentDirection());
            pizza.generateNewPosition(myFrame.getSnakeMap());
            repaint();
            myFrame.getSnakeMap().setPopUpText(null);
            points = 0;
            speed = 200;
            myFrame.getMenuPanel().setPoints(points);
            shouldStartCounting = true;
            runningGameCondition = true;
        }
    }
}
