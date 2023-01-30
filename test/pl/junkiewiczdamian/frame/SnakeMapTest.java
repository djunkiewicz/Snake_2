package pl.junkiewiczdamian.frame;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SnakeMapTest {

    @Test
    public void initialTest() {
        int x = 20;
        int y = 30;
        int z = 40;
        SnakeMap snakeMap1 = new SnakeMap(x);
        SnakeMap snakeMap2 = new SnakeMap(y);
        SnakeMap snakeMap3 = new SnakeMap(z);
        Assert.assertNotNull(snakeMap1);
        Assert.assertNotNull(snakeMap2);
        Assert.assertNotNull(snakeMap3);
        Assert.assertEquals(x,snakeMap1.getMapSize());
        Assert.assertEquals(y,snakeMap2.getMapSize());
        Assert.assertEquals(z,snakeMap3.getMapSize());
    }

    @Test
    public void isBorderCorrect() {
        int size = 20;
        SnakeMap snakeMap = new SnakeMap(size);
        FieldType  topLeftCorner = snakeMap.getFieldType(0,0);
        FieldType  bottomLeftCorner = snakeMap.getFieldType(0,size-1);
        FieldType  topRightCorner = snakeMap.getFieldType(size-1,0);
        FieldType  bottomRightCorner = snakeMap.getFieldType(size-1,size-1);

        Assert.assertEquals(FieldType.BORDER, topLeftCorner);
        Assert.assertEquals(FieldType.BORDER, bottomLeftCorner);
        Assert.assertEquals(FieldType.BORDER, topRightCorner);
        Assert.assertEquals(FieldType.BORDER, bottomRightCorner);
    }

}