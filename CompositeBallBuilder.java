package application;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CompositeBallBuilder implements BallBuilder{
    private String colour;
    private double xPosition;
    private double yPosition;
    private double xVelocity;
    private double yVelocity;
    private double strength;
    private double radius = 10.0;	// Default radius 10.0cm
    private Circle view;
    private List<Ball> balls = new ArrayList<Ball>();

    @Override
    public void setColour(JSONObject jsonBall) {
        String colour = (String)jsonBall.get("colour");
        this.colour = colour;
    }
    @Override
    public void setpositionX(JSONObject jsonBall) {
        Double positionX = (Double) ((JSONObject) jsonBall.get("position")).get("x");
        this.xPosition = positionX;

    }
    @Override
    public void setpositionY(JSONObject jsonBall) {
        Double positionY = (Double) ((JSONObject) jsonBall.get("position")).get("y");
        this.yPosition = positionY;

    }
    @Override
    public void setvelocityX(JSONObject jsonBall) {
        Double velocityX = (Double) ((JSONObject) jsonBall.get("velocity")).get("x");
        this.xVelocity = velocityX;

    }
    @Override
    public void setvelocityY(JSONObject jsonBall) {
        Double velocityY = (Double) ((JSONObject) jsonBall.get("velocity")).get("y");
        this.yVelocity = velocityY;

    }

    @Override
    public void setmass(JSONObject jsonBall) {
        return;
    }

    public void setStrength(JSONObject jsonBall) {
        Double str = (Double) jsonBall.get("strength");
        this.strength = str;
    }

    public void setBalls(JSONObject jsonBall){
        Configuration ballConf = ConfigurationProducer.getInstance().geConfiguration("BALL");
        JSONArray children = (JSONArray) jsonBall.get("balls");
        // reading from the array:

        Ball temp;
        // Create ball object for every ball
        for (Object obj : children) {
            JSONObject ball = (JSONObject) obj;
            temp = ballConf.getBall(jsonBall);

            balls.add(temp);

        }

    }




    @Override
    public void setView(JSONObject jsonBall) {
        this.view = new Circle(this.xPosition,this.yPosition,this.radius, Paint.valueOf(this.colour));

    }

    /**
     * Return the finished ball object
     */
    public Ball getResult() {
        return new BallComposite(this.colour, this.xPosition, this.yPosition, this.xVelocity, this.yVelocity, this.balls, this.strength,this.view);
    }
}
