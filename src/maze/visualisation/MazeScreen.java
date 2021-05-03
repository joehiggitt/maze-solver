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

public class MazeScreen
{
	// private static Image entranceImage, exitImage, wallImage, pathImage, outerWallImage;

	private static VBox root;
	private static HBox buttons;
	private static Label appTitle, screenTitle;
	private static Button stepButton, saveButton, backButton;

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
