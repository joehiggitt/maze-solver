package maze.visualisation;

import maze.routing.*;

import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

// import javafx.event.EventHandler;
// import javafx.event.ActionEvent;

import javafx.scene.image.Image;

public class MazeScreen
{
	private Image entranceImage = new Image("/EntranceSquare.png");
	private Image exitImage = new Image("/ExitSquare.png");
	private Image wallImage = new Image("/WallSquare.png");
	private Image pathImage = new Image("/PathSquare.png");
	private Image outerWallImage = new Image("/OuterWallSquare.png");

	public static Scene createScene(Stage stage, RouteFinder routeFinder)
	{
		VBox root = Graphics.createVBox();

		Label appTitle = Graphics.createTitle("MazeSolver Pro", 40);
		Label screenTitle = Graphics.createTitle("Your Maze", 30);
		// Label infoText = Graphics.createText("Enter the file path which the route file is stored:");

		

		root.getChildren().addAll();
		return new Scene(root, 600, 500);
	}
}
