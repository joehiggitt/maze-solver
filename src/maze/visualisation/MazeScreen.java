package maze.visualisation;

import maze.routing.*;

import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

// import javafx.event.EventHandler;
// import javafx.event.ActionEvent;

import javafx.scene.image.Image;

/**
* Class that displays the maze solving screen in the application.
* @author Joe Higgitt
* @version 1.0, 4th May 2021
*/
public class MazeScreen
{
	// private static Image entranceImage, exitImage, wallImage, pathImage, outerWallImage;

	private static VBox root;
	private static HBox buttons;
	private static Label appTitle, screenTitle;
	private static Button stepButton, saveButton, backButton;

	/**
	* Creates the maze solving screen
	* @param stage the main stage for the application
	* @param routeFinder the current route being found
	* @return Returns a {@link javafx.scene.Scene} object which contains the screen's content
	*/
	public static Scene createScene(Stage stage, RouteFinder routeFinder)
	{
		// entranceImage = new Image("/EntranceSquare.png");
		// exitImage = new Image("/ExitSquare.png");
		// wallImage = new Image("/WallSquare.png");
		// pathImage = new Image("/PathSquare.png");
		// outerWallImage = new Image("/OuterWallSquare.png");

		root = Graphics.createVBox();
		buttons = Graphics.createHBox();

		appTitle = Graphics.createTitle("MazeSolver Pro", 40);
		screenTitle = Graphics.createTitle("Your Maze", 30);
		// Label infoText = Graphics.createText("Enter the file path which the route file is stored:");


		System.out.println(routeFinder);


		stepButton = Graphics.createButton("Step");
		// openButton.setOnAction(e -> );

		saveButton = Graphics.createButton("Save");
		saveButton.setOnAction(e ->
		{
			stage.setScene(SaveScreen.createScene(stage, routeFinder));
		});

		backButton = Graphics.createButton("Menu");
		backButton.setOnAction(e ->
		{
			stage.setScene(MenuScreen.createScene(stage));
		});

		buttons.getChildren().addAll(saveButton, backButton);
		root.getChildren().addAll(appTitle, screenTitle,/* stuff, */ stepButton, buttons);
		return new Scene(root, stage.getWidth(), stage.getHeight());
	}
}
