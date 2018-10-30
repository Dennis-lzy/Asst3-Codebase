package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;



public class Cue extends Line {
    private DoubleProperty mouseX = new SimpleDoubleProperty();
    private DoubleProperty mouseY = new SimpleDoubleProperty();
    private Line powerLine = null;
    private Line cueStick = null;
    double power = 0;
    double MAXPOWER = 1400;
    private boolean dragActive = false;
    private CueBall cueball;
    private Pane root;
    double newX;
    double newY;
    Rotate rotate = new Rotate(45);

    private static Cue instance = new Cue();

    public static Cue getInstance(){
        return instance;
    }

    public Cue(){}


    public void setCueBall(CueBall cb, Pane r) {

        super.setStrokeWidth(5);
        super.setStartX(cb.getxPosition());
        super.setStartY(cb.getyPosition());
        super.setEndX(cb.getxPosition());
        super.setEndY(cb.getyPosition());
        cueball = cb;
        root = r;
    }


    //sets power based on how far you drag
    public void setPower(Line line){
        Point2D start = new Point2D(line.getStartX(), line.getStartY());
        Point2D end = new Point2D(line.getEndX(), line.getEndY());
        power = start.distance(end)*3;
        if(power>MAXPOWER){
            power=MAXPOWER;
        }
    }

    //shoots ball
    public void shoot(CueBall A, Line B) {

        double x1 = A.getxPosition();
        double x2 = B.getEndX();
        double y1 = A.getyPosition();
        double y2 = B.getEndY();

        double velX = -(x2-x1)/2;
        double velY = -(y2-y1)/2;

        A.setxVelocity(velX);
        A.setyVelocity(velY);

    }



    private void startDrag(CueBall node, Pane root) {

        powerLine = new Line();
        powerLine.setUserData(node);
        powerLine.setStartX(node.getxPosition());
        powerLine.setStartY(node.getyPosition());
        powerLine.endXProperty().bind(mouseX);
        powerLine.endYProperty().bind(mouseY);

        /**
         * colors in a cue stick
         */

        powerLine.setStrokeWidth(4);


//        cueStick = new Line(node.getxPosition() + (node.getRadius() + 5), node.getyPosition(), node.getxPosition() + (node.getRadius() + 5 + 400), node.getyPosition());
//
////
//        cueStick.setStrokeWidth(20);
//        Image img = new Image("cuestick.png");
//        cueStick.setStroke(new ImagePattern(img));
//        rotate.pivotXProperty().bind(node.getView().centerXProperty());
//        rotate.pivotYProperty().bind(node.getView().centerYProperty());
//        cueStick.getTransforms().add(rotate);
//
//        root.getChildren().add(cueStick);

        root.getChildren().add(powerLine);



    }

    private void stopDrag(Circle node) {
        dragActive = false;

        stopDrag(root);

    }

    private void stopDrag(Pane root) {
        dragActive = false;
        this.setPower(powerLine);
        this.shoot(cueball, powerLine);
        System.out.println(this.power);

        powerLine.endXProperty().unbind();
        powerLine.endYProperty().unbind();
        root.getChildren().remove(powerLine);
        root.getChildren().remove(cueStick);
        cueStick = null;
        powerLine = null;
    }

    public void attachHandlers() {

            root.setOnMouseMoved(e -> {
                mouseX.set(e.getSceneX());
                mouseY.set(e.getSceneY());
                newX = e.getSceneX();
                newY = e.getSceneY();
                rotate.setAngle(Math.toDegrees(Math.atan2(newY - cueball.getyPosition(), newX - cueball.getxPosition())));
            });

            root.setOnMouseDragged(e -> {
                mouseX.set(e.getSceneX());
                mouseY.set(e.getSceneY());
                newX = e.getSceneX();
                newY = e.getSceneY();
                rotate.setAngle(Math.toDegrees(Math.atan2(newY - cueball.getyPosition(), newX - cueball.getxPosition())));
            });

            root.setOnMousePressed(e -> {
                startDrag(cueball, root);
            });

            root.setOnMouseReleased(e -> {
                stopDrag(root);
            });
        }


    public void detachHandlers(){

        root.setOnMouseMoved(e -> {
            return;
        });

        root.setOnMouseDragged(e -> {
            return;
        });

        root.setOnMousePressed(e -> {
            return;
        });

        root.setOnMouseReleased(e -> {
            return;
        });
    }
}
