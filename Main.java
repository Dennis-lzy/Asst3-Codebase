package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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

/**
 * This class has the responsibility to handle user input(mouse events),
 * constructs game, and calculate and update game data by delegating the task to game engine methods.
 *
 */
public class Main extends Application {
	public static String filePath = null;
	protected static boolean dragged = false;
	
	/**
	 * Create the content to be displayed in the stage. Delegates hitting cue ball and
	 * update on balls to respective Event Handlers and methods
	 * @param filePath: where to read file
	 * @return: built scene with all the information specified in filePath
	 */
	private Parent createContent(String filePath) {
		GameEngine config = new GameEngine(filePath);
		Pane root = new Pane();
		root.setPrefSize(config.getTable().getX(),config.getTable().getY());
        Caretaker caretaker = new Caretaker();
        Originator originator = new Originator();
        // adding start state
        originator.setState(config.getBalls());
        caretaker.addMemento( originator.saveToMemento() );
        caretaker.addMemento( originator.saveToMemento() );



        // Handles mouse-clicking
		EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if(config.getCueBall().atRest() && !config.getCueBall().isSelected()) { 	// If at rest and not selected already
					config.getCueBall().setSelected(true);
					// Create and display visual cue stick
					Rectangle rect = new Rectangle(e.getX(), e.getY(), 20,20);
					rect.setFill(Color.BROWN);
					root.getChildren().add(rect);
					// Allows dragging only when cueball selected
					rect.setOnMouseDragged(new EventHandler<MouseEvent>() {	
						@Override
						// On drag, visually relocate the rect and listen for mouse button release
						public void handle(MouseEvent event) {
							if(config.getCueBall().isSelected()) {
								rect.relocate(event.getSceneX(), event.getSceneY());
								boolean dragged = true;
								rect.setOnMouseReleased(new EventHandler<MouseEvent>(){
									@Override
									public void handle(MouseEvent event) {
										// If cueball is selected, and cuestick was dragged after selection, register a shot
										if(config.getCueBall().isSelected() && dragged) {
											config.getCueBall().registerShot(event.getSceneX(), event.getSceneY());
											config.getCueBall().setSelected(false);
											Main.dragged = false;
											root.getChildren().remove(rect);
										}
									}
								});
							}
						}
					});
				}
			}
		};

		// Adding table, balls, and pocket into display
		root.getChildren().add(config.getTable().getView());
		for(Ball i : config.getBalls()) {
			root.getChildren().add(i.getView());
			if(i.getColour().equals("white")) {
				i.getView().addEventHandler(MouseEvent.MOUSE_CLICKED, clickHandler);
			}
		}

        /**
         * Reset Button
         */
        Button button = new Button("Reset");
        button.setPrefSize(50, 50);

        root.getChildren().add(button);

		// Get the coundaries of the table
		Bounds tableBounds = root.getBoundsInLocal();
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {

				config.applyFriction();

				// Detect if there's any collision and if so change velocities accordingly
				config.updateCollision();
				// Move balls according to updated velocities
				config.moveBalls(tableBounds);

                /**
                 * When CueBall is at rest
                 */
                if(config.getCueBall().atRest()){
                    if(!(config.getBalls().equals(caretaker.getMemento()))) {
                        originator.setState(config.getBalls());
                        caretaker.addMemento(originator.saveToMemento());
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
			filePath = "application/config.json";
			launch(args);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("No filepath specified");
		}
		
	}
}