package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

/**
 * This class has the responsibility to handle user input(mouse events),
 * constructs game, and calculate and update game data by delegating the task to game engine methods.
 *
 */
public class Main extends Application {
	public static String filePath = null;
	private boolean stateSaved = false;

	
	/**
	 * Create the content to be displayed in the stage. Delegates hitting cue ball and
	 * update on balls to respective Event Handlers and methods
	 * @param filePath: where to read file
	 * @return: built scene with all the information specified in filePath
	 */
	private Parent createContent(String filePath) {
        /**
         * Initialises the Game engine, Pane, ballArea, table
         */
		GameEngine config = new GameEngine(filePath);
		Pane root = new Pane();
        Rectangle ballArea;
		Table table = config.getTable();

        /**
         * Sets Pane layout size
         */
		root.setPrefSize(table.getX(),table.getY());

        /**
         * Gets instance of Cue Singleton and sets the Cue ball
         */
		Cue cue = Cue.getInstance();
        cue.setCueBall(config.getCueBall(), root);

        /**
         * Revert Button Implementation
         */
        Button revertButton = new Button("Revert");
        revertButton.setOnAction(actionEvent ->  {
            for(Ball b : config.getBalls()){
                b.reset();
            }
        });


        /**
         * Restart Button Implementation
         */
        Button restartButton = new Button("Restart");
        restartButton.setLayoutX(600);
        restartButton.setOnAction(actionEvent ->  {

            root.getChildren().clear();

            config.clearAll();

            config.readConfig(filePath);

            root.getChildren().add(config.getTable().getView());
            for(Pockets p : config.getPockets()){
                root.getChildren().add(p.getView());
            }

            for(Ball i : config.getBalls()) {
                root.getChildren().add(i.getView());


            }
            cue.setCueBall(config.getCueBall(), root);
            root.getChildren().add(revertButton);
            root.getChildren().add(restartButton);
        });


        /**
         * Old Mouse Event handler for Cue
         */
        // Handles mouse-clicking
		EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {


                /**
                 * Replaced by Cue Class
                 */
//				if(config.getCueBall().atRest() && !config.getCueBall().isSelected()) { 	// If at rest and not selected already
//					config.getCueBall().setSelected(true);
//					System.out.println("test");
//					// Create and display visual cue stick
//					Rectangle rect = new Rectangle(e.getX(), e.getY(), 20,20);
//					rect.setFill(Color.BROWN);
//					root.getChildren().add(rect);
//					// Allows dragging only when cueball selected
//					rect.setOnMouseDragged(new EventHandler<MouseEvent>() {
//						@Override
//						// On drag, visually relocate the rect and listen for mouse revertButton release
//						public void handle(MouseEvent event) {
//							if(config.getCueBall().isSelected()) {
//								rect.relocate(event.getSceneX(), event.getSceneY());
//								boolean dragged = true;
//								rect.setOnMouseReleased(new EventHandler<MouseEvent>(){
//									@Override
//									public void handle(MouseEvent event) {
//										// If cueball is selected, and cuestick was dragged after selection, register a shot
//										if(config.getCueBall().isSelected() && dragged) {
//											config.getCueBall().registerShot(event.getSceneX(), event.getSceneY());
//											ballHit = true;
//											config.getCueBall().setSelected(false);
//											Main.dragged = false;
//											root.getChildren().remove(rect);
//											root.getChildren().remove(revertButton);
//
//										}
//									}
//
//								});
//							}
//						}
//					});
//				}
			}
		};


        /**
         * Adding buttons, table, balls, and pocket into display
         */
		root.getChildren().add(config.getTable().getView());
		for(Pockets p : config.getPockets()){
			root.getChildren().add(p.getView());
		}

		for(Ball i : config.getBalls()) {
			root.getChildren().add(i.getView());

			if(i.getColour().equals("white")) {
				i.getView().addEventHandler(MouseEvent.MOUSE_CLICKED, clickHandler);
			}
		}

		root.getChildren().add(revertButton);
		root.getChildren().add(restartButton);


        /**
         * Gets Table Boundaries
         */

        if (table.getImg()==null){
            ballArea = new Rectangle(table.getX(),table.getY());
        } else {
            long offX = table.getOffsetX();
            long offY = table.getOffsetY();
            ballArea = new Rectangle(offX, offY, table.getX()-offX,table.getY()-offY);
        }

        Bounds tableBounds = ballArea.getBoundsInLocal();

        /**
         *  Game Loop
         */
        AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {

                if(config.getBalls() == null){
                    return;
                } else {
				config.applyFriction();
				config.updateCollision();

                    /**
                     * Save state if cueball is at rest and State has not been already saved
                     */
                 if(config.getCueBall().atRest() && config.getCueBall().isSunk() == false){
                     //System.out.println(stateSaved);
                     if(stateSaved == false){
                         for(Ball b: config.getBalls()){
                             b.getCaretaker().add(b.saveStateToMemento());
                             System.out.println(b);
                         }
                         stateSaved = true;
                     }
                 };





				/**
				 * Remove balls from pockets if the balls are contained within
				 */
				config.checkPockets(root);

                    /**
                     * Move balls according to updated velocities
                     */
				config.moveBalls(tableBounds);

                    /**
                     * Attach mouse handlers for cue on ball stop
                     */

				if(config.getCueBall().atRest() && config.getCueBall().isSunk() == false){


				    cue.attachHandlers();
                } else {
				    cue.detachHandlers();
				    stateSaved = false;

                }





                }

			}
		};



		timer.start();

		return root;
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			Scene scene = new Scene(createContent(filePath));
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Display graphic user interface and delegate handling responsibilities to respective methods
	 * @param args: file path
	 */
	public static void main(String[] args) {
		try {
			filePath = "application/example.json";
			launch(args);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("No filepath specified");
		}
		
	}
}