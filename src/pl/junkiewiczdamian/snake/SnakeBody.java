package pl.junkiewiczdamian.snake;

import java.util.ArrayList;
import java.util.List;

public class SnakeBody {
    private final List<SnakePart> bodyParts;

    public SnakeBody(SnakePart head) {
        bodyParts = new ArrayList<>();
        int x = head.getX();
        int y = head.getY();
        for (int i = 0; i<5; i++){
            bodyParts.add(new SnakePart(x-1-i,y));
        }
    }

    public void moveTowards(SnakePart head){
        int x = head.getX();
        int y = head.getY();
        int size = bodyParts.size();

        for (int i = 0; i<size-1; i++){
            bodyParts.get(size-1-i).setPosition(bodyParts.get(size-2-i).getX(), bodyParts.get(size-2-i).getY());
        }
        bodyParts.get(0).setPosition(x,y);
    }

    public void expandBody(int x, int y){
        bodyParts.add(new SnakePart(x,y));
    }

    public List<SnakePart> getBodyParts() {
        return bodyParts;
    }


}
