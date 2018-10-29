package application;

import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;

public interface BallComponent{
    boolean isColliding(Ball ball2);

    void calculatePosition(Bounds tableBounds);

    
}
