package maze.visualisation;

import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class TextEnterScreen
{
	public static Scene createScene(Stage stage)
	{
		VBox root = Graphics.createVBox();

		Label appTitle = Graphics.createTitle("MazeSolver Pro", 40);
		Label screenTitle = Graphics.createTitle("Load Maze", 30);
		Label infoText = Graphics.createText("Enter the maze into the text box:");

		TextArea textBox = new TextArea("");

		Button submitButton = Graphics.createButton("Open Route File...");
		submitButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(final ActionEvent e)
			{
				
			}
		});

		root.getChildren().addAll(appTitle, screenTitle, infoText, textBox, submitButton);
		return new Scene(root, 600, 500);
	}
}
