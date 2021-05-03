package maze.visualisation;

import maze.routing.*;

import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class LoadScreen
{
	public static Scene createScene(Stage stage)
	{
		VBox root = Graphics.createVBox();

		Label appTitle = Graphics.createTitle("MazeSolver Pro", 40);
		Label screenTitle = Graphics.createTitle("Load Maze", 30);
		Label infoText = Graphics.createText("Enter the file path which the route file is stored:");
		Label errorText = Graphics.createText("");

		// TextField textField = new TextField("/");
		final FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Route File");
		Button openButton = Graphics.createButton("Open Route File...");
		openButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(final ActionEvent e)
			{
				File file = fileChooser.showOpenDialog(stage);
				if (file != null)
				{
					RouteFinder routeFinder = RouteFinder.load(file.getAbsolutePath());
					if (routeFinder != null)
					{
						stage.setScene(MazeScreen.createScene(stage, routeFinder));
					}
					else
					{
						// stage.setScene(ErrorScreen.createScene(stage));
						System.out.println("Error occured.");
					}
					
				}
			}
		});

		root.getChildren().addAll(appTitle, screenTitle, infoText, /*textField, */openButton, errorText);
		return new Scene(root, 600, 500);
	}
}
