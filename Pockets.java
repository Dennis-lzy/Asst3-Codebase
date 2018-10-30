package application;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

/**
 * This class defines Pocket object types
 */

public class Pockets extends Ball {

    private Circle view;
    protected double xPosition;
    protected double yPosition;
    protected double radius;

    /**
     * Constructs a Pocket object
     * @param xPosition
     * @param yPosition
     * @param radius
     */
    public Pockets(double xPosition, double yPosition, double radius){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.radius = radius;
        this.setView();
    }

    /**
     * Returns the view of the ball
     * @return view: of the ball
     */
    @Override
    public Circle getView() {
        return view;
    }

    /**
     * Sets the view of the pocket
     */
    public void setView() {
        this.view = new Circle(this.xPosition, this.yPosition, this.radius, Color.BLACK);
    }


    /**
     * Removes balls that have been sunk
     * @param root
     * @param ball
     * @param balls
     */
    public void removeSunkBalls(Pane root, Ball ball, List<Ball> balls){
        root.getChildren().remove(ball.getView());
        balls.remove(ball);
    }

    /**
     * Detects if pocket contains a ball
     * @param ball
     * @return
     */
    public boolean containsBall(Ball ball) {
        // Create Node to represent location of the balls in the next frame and check if colliion
        // will happen in next frame
        double x = this.getView().getCenterX() + this.getxVelocity() / 60;
        double y = this.getView().getCenterY() + this.getyVelocity() / 60;
        double x2 = ball.getView().getCenterX() + ball.getxVelocity() / 60;
        double y2 = ball.getView().getCenterY() + ball.getyVelocity() / 60;
        Circle temp = new Circle(x, y, this.getView().getRadius());
        Circle temp2 = new Circle(x2, y2, ball.getView().getRadius());
        // Detect collision based on if boundaries are touching each other
        Bounds bounds = temp.getBoundsInParent();
        Bounds bounds2 = temp2.getBoundsInParent();
        if(bounds.contains(bounds2))
            return true;
        else
            return false;
    }
}
