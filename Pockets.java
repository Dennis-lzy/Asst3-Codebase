package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pockets extends Ball {

    private Circle view;
    protected double xPosition;
    protected double yPosition;
    protected double radius;

    public Pockets(double xPosition, double yPosition, double radius){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.radius = radius;
        this.setView();
    }

    @Override
    public Circle getView() {
        return view;
    }


    public void setView() {
        this.view = new Circle(this.xPosition, this.yPosition, this.radius, Color.BLACK);
    }

    @Override
    public double getxPosition() {
        return xPosition;
    }

    @Override
    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    @Override
    public double getyPosition() {
        return yPosition;
    }

    @Override
    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public void setRadius(double radius) {
        this.radius = radius;
    }
}
