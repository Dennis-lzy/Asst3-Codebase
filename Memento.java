package application;

public class Memento {
    private double xPosition;
    private double yPosition;

    public Memento(double x, double y){
        this.xPosition = x;
        this.yPosition = y;
    }

    public double getXPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }
}
