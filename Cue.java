package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Cue extends Line {
    private DoubleProperty mouseX = new SimpleDoubleProperty();
    private DoubleProperty mouseY = new SimpleDoubleProperty();
    private Line currentLine = null;
    double power = 0;
    double MAXPOWER = 1400;
    private boolean dragActive = false;
    private CueBall cueball;
    private Pane root;

    public Cue(CueBall cb, Pane r) {
        super();
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

        currentLine = new Line();
        currentLine.setUserData(node);
        currentLine.setStartX(node.getxPosition());
        currentLine.setStartY(node.getyPosition());
        currentLine.endXProperty().bind(mouseX);
        currentLine.endYProperty().bind(mouseY);

        root.getChildren().add(currentLine);
    }

    private void stopDrag(Circle node) {
        dragActive = false;

        stopDrag(root);

    }

    private void stopDrag(Pane root) {
        dragActive = false;
        this.setPower(currentLine);
        this.shoot(cueball,currentLine);
        System.out.println(this.power);

        currentLine.endXProperty().unbind();
        currentLine.endYProperty().unbind();
        root.getChildren().remove(currentLine);

        currentLine = null;
    }

    public void attachHandlers() {

            root.setOnMouseMoved(e -> {
                mouseX.set(e.getSceneX());
                mouseY.set(e.getSceneY());
            });

            root.setOnMouseDragged(e -> {
                mouseX.set(e.getSceneX());
                mouseY.set(e.getSceneY());
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
