package application;

import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;

/**
 *Declares the common interface for all Ball-related Objects in the tree.
 */

public interface BallComponent{
    boolean isColliding(Ball ball2);

    void calculatePosition(Bounds tableBounds);

    
}
