package pl.junkiewiczdamian.snake;

import org.junit.Assert;
import org.junit.Test;
import pl.junkiewiczdamian.frame.SnakeMap;

import static org.junit.Assert.*;

public class SnakeTest {

    @Test
    public void expandBodyTest() {
        SnakeMap snakeMap = new SnakeMap(20);
        Snake snake = new Snake();
        int sizeBeforeExpansion = snake.getBodyParts().size();
        // the snake must move at least once before expansion
        // to get x_r and y_r (the coordinates of the new piece)
        snake.move(snakeMap);
        snake.expandBody();
        int sizeAfterExpansion = snake.getBodyParts().size();
        Assert.assertTrue(sizeAfterExpansion > sizeBeforeExpansion);
    }

    @Test
    public void setDirectionTest() {
        Snake snake = new Snake();
        snake.setDirection("up");
        Assert.assertEquals("up", snake.getCurrentDirection());

        snake.setDirection("down");
        Assert.assertEquals("down", snake.getCurrentDirection());

        snake.setDirection("left");
        Assert.assertEquals("left", snake.getCurrentDirection());

        snake.setDirection("right");
        Assert.assertEquals("right", snake.getCurrentDirection());
    }

    @Test
    public void movingLeftTest() {
        SnakeMap snakeMap = new SnakeMap(20);
        Snake snake = new Snake();
        int beforeMovingX, beforeMovingY, afterMovingX, afterMovingY;

        beforeMovingX = snake.getHead().getX();
        beforeMovingY = snake.getHead().getY();
        snake.setDirection("left");
        snake.move(snakeMap);
        afterMovingX = snake.getHead().getX();
        afterMovingY = snake.getHead().getY();
        Assert.assertTrue(afterMovingX < beforeMovingX);
        assertEquals(afterMovingY, beforeMovingY);
    }

    @Test
    public void movingRightTest() {
        SnakeMap snakeMap = new SnakeMap(20);
        Snake snake = new Snake();
        int beforeMovingX, beforeMovingY, afterMovingX, afterMovingY;

        beforeMovingX = snake.getHead().getX();
        beforeMovingY = snake.getHead().getY();
        snake.setDirection("right");
        snake.move(snakeMap);
        afterMovingX = snake.getHead().getX();
        afterMovingY = snake.getHead().getY();
        Assert.assertTrue(afterMovingX > beforeMovingX);
        assertEquals(afterMovingY, beforeMovingY);
    }

    @Test
    public void movingUpTest() {
        SnakeMap snakeMap = new SnakeMap(20);
        Snake snake = new Snake();
        int beforeMovingX, beforeMovingY, afterMovingX, afterMovingY;

        beforeMovingX = snake.getHead().getX();
        beforeMovingY = snake.getHead().getY();
        snake.setDirection("up");
        snake.move(snakeMap);
        afterMovingX = snake.getHead().getX();
        afterMovingY = snake.getHead().getY();
        assertEquals(afterMovingX, beforeMovingX);
        Assert.assertTrue(afterMovingY < beforeMovingY);
    }

    @Test
    public void movingDownTest() {
        SnakeMap snakeMap = new SnakeMap(20);
        Snake snake = new Snake();
        int beforeMovingX, beforeMovingY, afterMovingX, afterMovingY;

        beforeMovingX = snake.getHead().getX();
        beforeMovingY = snake.getHead().getY();
        snake.setDirection("down");
        snake.move(snakeMap);
        afterMovingX = snake.getHead().getX();
        afterMovingY = snake.getHead().getY();
        assertEquals(afterMovingX, beforeMovingX);
        Assert.assertTrue(afterMovingY > beforeMovingY);
    }

}