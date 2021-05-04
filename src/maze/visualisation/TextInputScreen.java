package maze.visualisation;

import maze.*;
import maze.routing.*;

import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

// import javafx.event.EventHandler;
// import javafx.event.ActionEvent;

/**
* Class that displays the plain text input screen in the application
* @author Joe Higgitt
* @version 1.0, 4th May 2021
*/
public class TextInputScreen
{
	private static VBox root;
	private static Label appTitle, screenTitle, info1Text, info2Text, errorText;
	private static Button submitButton, backButton;
	private static TextArea textBox;

	/**
	* Creates the plain text input screen
	* @param stage the main stage for the application
	* @return Returns a {@link javafx.scene.Scene} object which contains the screen's content
	*/
	public static Scene createScene(Stage stage)
	{
		root = Graphics.createVBox();

		appTitle = Graphics.createTitle("MazeSolver Pro", 40);
		screenTitle = Graphics.createTitle("Load Maze", 30);
		info1Text = Graphics.createText("Enter the maze into the text box");
		info2Text = Graphics.createText("Use the following symbols for different blocks\n# - Wall    . - Path    e - Entrance    x - Exit");
		errorText = Graphics.createText("");

		textBox = Graphics.createTextArea();
		

		submitButton = Graphics.createButton("Create Maze");
		submitButton.setOnAction(e -> load(stage));

		backButton = Graphics.createButton("Back");
		backButton.setOnAction(e ->
		{
			stage.setScene(MenuScreen.createScene(stage));
		});

		root.getChildren().addAll(appTitle, screenTitle, info1Text, textBox, info2Text, errorText, submitButton, backButton);
		return new Scene(root, stage.getWidth(), stage.getHeight());
	}

	private static void load(Stage stage)
	{
		Maze maze = null;
		try
		{
			maze = Maze.fromStr(textBox.getText());
		}
		catch (InvalidMazeException e)
		{
			errorText.setText(e.getMessage());
			// System.out.println("----------------------------------------");
			// e.printStackTrace();
			// System.out.println("----------------------------------------");
		}
		finally
		{
			if (maze == null)
			{
				return;
			}
			stage.setScene(MazeScreen.createScene(stage, new RouteFinder(maze)));
		}
	}
}
