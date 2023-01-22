package pl.junkiewiczdamian;

import pl.junkiewiczdamian.frame.MyFrame;
import pl.junkiewiczdamian.snake.Snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements Runnable, ActionListener {
    private Thread gameThread;
    private final MyFrame myFrame;
    private final Snake snake;
    private boolean runningGameCondition;
    private boolean shouldStartCounting;

    public Game() {
        snake = new Snake();
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
                    Thread.sleep(100);
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

    public void update() {
        snake.move();
        myFrame.updateSnakeMap(snake);
    }

    public void repaint() {
        myFrame.repaintSnakeMap();
    }
    public boolean checkCollision(){
        int x = snake.getHead().getX();
        int y = snake.getHead().getY();
        int size = myFrame.getSnakeMapSize();
        return x == 0 || x == size - 1 || y == 0 || y == size - 1;
    }

    public void startSequence(){
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
            runningGameCondition = true;
        }
        if (e.getSource() == myFrame.getMenuPanel().getPauseButton()){
            runningGameCondition = false;
            shouldStartCounting = true;
        }
        if (e.getSource() == myFrame.getMenuPanel().getRestartButton()){
            snake.init();
            myFrame.updateSnakeMap(snake);
            repaint();
            myFrame.getSnakeMap().setPopUpText(null);
            shouldStartCounting = true;
            runningGameCondition = true;
        }
    }
}
