package application;

import javafx.geometry.Point2D;
import javafx.util.Pair;

public class PhysicsEngine {

    // A quick usage example for the utility you may use to test it is working correctly
    public static void main(String[] args) {
        Point2D posA = new Point2D(10, 20);
        Point2D velA = new Point2D(2, -2);
        double massA = 1;

        Point2D posB = new Point2D(15, 15);
        Point2D velB = new Point2D(-2, 2);
        double massB = 1;

        Pair<Point2D, Point2D> results = calculateCollision(posA, velA, massA, posB, velB, massB);

        System.out.println("A: " + results.getKey());
        System.out.println("B: " + results.getValue());
    }

    /**
     * This is an updated collision calculation function for 2 balls colliding in 2D space. You may use it however
     * you wish for your assignment.
     *
     * This uses the optimised physics algorithm discussed here:
     * http://www.gamasutra.com/view/feature/3015/pool_hall_lessons_fast_accurate_.php?page=3
     * which has been converted into Java/JavaFX
     *
     * @param positionA The coordinates of the centre of ball A
     * @param velocityA The delta x,y vector of ball A (how much it moves per tick)
     * @param massA The mass of ball A (for the moment this should always be the same as ball B)
     * @param positionB The coordinates of the centre of ball B
     * @param velocityB The delta x,y vector of ball B (how much it moves per tick)
     * @param massB The mass of ball B (for the moment this should always be the same as ball A)
     *
     * @return A Pair<Point2D, Point2D> in which the first (key) Point2D is the new delta x,y vector for ball A, and the second
     *         (value) Point2D is the new delta x,y vector for ball B.
     */
    public static Pair<Point2D, Point2D> calculateCollision(Point2D positionA, Point2D velocityA, double massA, Point2D positionB, Point2D velocityB, double massB) {

        // Find the angle of the collision - basically where is ball B relative to ball A. We aren't concerned with
        // distance here, so we reduce it to unit (1) size with normalize() - this allows for arbitrary radii
        Point2D collisionVector = positionA.subtract(positionB);
        collisionVector = collisionVector.normalize();

        // Here we determine how 'direct' or 'glancing' the collision was for each ball
        double vA = collisionVector.dotProduct(velocityA);
        double vB = collisionVector.dotProduct(velocityB);

        // If you don't detect the collision at just the right time, balls might collide again before they leave
        // each others' collision detection area, and bounce twice. This stops these secondary collisions by detecting
        // whether a ball has already begun moving away from its pair, and returns the original velocities
        if (vB <= 0 && vA >= 0) {
            return new Pair<>(velocityA, velocityB);
        }

        // This is the optimisation function described in the gamasutra link. Rather than handling the full quadratic
        // (which as we have discovered allowed for sneaky typos) this is a much simpler - and faster - way of obtaining
        // the same results.
        double optimizedP = (2.0 * (vA - vB)) / (massA + massB);

        // Now we apply that calculated function to the pair of balls to obtain their final velocities
        Point2D velAPrime = velocityA.subtract(collisionVector.multiply(optimizedP).multiply(massB));
        Point2D velBPrime = velocityB.add(collisionVector.multiply(optimizedP).multiply(massA));

        return new Pair<>(velAPrime, velBPrime);
    }


}