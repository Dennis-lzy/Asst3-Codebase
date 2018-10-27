package application;

import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BallComposite extends Ball {
    private List<Ball> children = new ArrayList<Ball>();

    public BallComposite(String colour, double xPosition, double yPosition, double xVelocity, double yVelocity, double mass, Circle view) {
        super(colour, xPosition, yPosition, xVelocity, yVelocity, mass, view);
    }

    public void add(Ball ball){
        children.add(ball);
    }

    public void remove(Ball ball){
        children.remove(ball);
    }

    public Ball get(int i){
        return children.get(i);
    }

    @Override
    public void calculatePosition(Bounds tableBounds) {
        super.calculatePosition(tableBounds);

        Iterator ballIterator = children.iterator();
        while (ballIterator.hasNext()){
            Ball ball = (Ball) ballIterator.next();
            ball.calculatePosition(tableBounds);
        }
    }

    @Override
    public boolean isColliding(Ball ball2) {


        Iterator ballIterator = children.iterator();
        while (ballIterator.hasNext()){
            Ball ball = (Ball) ballIterator.next();
            ball.isColliding(ball2);
        }

        return super.isColliding(ball2);
    }
}
