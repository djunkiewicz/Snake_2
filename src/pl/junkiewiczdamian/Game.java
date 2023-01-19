package pl.junkiewiczdamian;

import pl.junkiewiczdamian.frame.MyFrame;
import pl.junkiewiczdamian.snake.Snake;

public class Game implements Runnable{
    private Thread gameThread;
    private final MyFrame myFrame;
    private final Snake snake;

    public Game() {
        snake = new Snake();
        myFrame = new MyFrame("Snake 2.0");
        myFrame.addKeyListener(snake);
        myFrame.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            //////////////// START
            update();
            repaint();
            //////////////// END
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void update(){
        snake.move();
        myFrame.updateSnakeMap(snake);
    }

    public void repaint(){
        myFrame.repaintSnakeMap();
    }
}
