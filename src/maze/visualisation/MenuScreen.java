package maze.visualisation;

import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class MenuScreen
{
	public static Scene createScene(Stage stage)
	{
		VBox root = Graphics.createVBox();

		Label appTitle = Graphics.createTitle("MazeSolver Pro", 40);
		Label screenTitle = Graphics.createTitle("Menu", 30);

		Button fromTextButton = Graphics.createButton("Upload Maze From Plain Text");
		// fromTextButton.setOnAction(e ->
		// {
		// 	stage.setScene(TextEnterScreen.createScene());
		// });

		// Button loadButton = new Button("Load Maze From File");

		Button loadButton = Graphics.createButton("Load Route From File");
		loadButton.setOnAction(e ->
		{
			stage.setScene(LoadScreen.createScene(stage));
		});

		root.getChildren().addAll(appTitle, screenTitle, fromTextButton, loadButton);
		return new Scene(root, 600, 500);
	}
}
