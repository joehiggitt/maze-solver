package maze.visualisation;

import maze.*;
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

/**
* Class that displays the load screen in the application
* @author Joe Higgitt
* @version 1.0, 4th May 2021
*/
public class LoadScreen
{
	private static VBox root;
	private static Label appTitle, screenTitle, infoText, errorText;
	private static Button openButton, backButton;
	private static final FileChooser fileChooser = new FileChooser();

	/**
	* Creates the load screen
	* @param stage the main stage for the application
	* @return Returns a {@link javafx.scene.Scene} object which contains the screen's content
	*/
	public static Scene createScene(Stage stage)
	{
		root = Graphics.createVBox();

		appTitle = Graphics.createTitle("MazeSolver Pro", 40);
		screenTitle = Graphics.createTitle("Load Maze", 30);
		infoText = Graphics.createText("Open either a maze text file (.txt) or a route object file (.obj)");
		errorText = Graphics.createText("");
		
		fileChooser.setTitle("Open File");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

		openButton = Graphics.createButton("Open File...");
		openButton.setOnAction(e -> load(stage));

		backButton = Graphics.createButton("Back");
		backButton.setOnAction(e ->
		{
			stage.setScene(MenuScreen.createScene(stage));
		});

		root.getChildren().addAll(appTitle, screenTitle, infoText, openButton, errorText, backButton);
		return new Scene(root, stage.getWidth(), stage.getHeight());
	}

	private static void load(Stage stage)
	{
		File file = fileChooser.showOpenDialog(stage);
		RouteFinder routeFinder = null;
		if (file == null)
		{
			// System.out.println("An error occured. []");
			return;
		}
		String fileExt = file.getAbsolutePath().substring(file.getAbsolutePath().length() - 4);
		if (fileExt.equals(".txt"))
		{
			Maze maze = null;
			try
			{
				maze = Maze.fromTxt(file);
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
				routeFinder = new RouteFinder(maze);
			}
		}
		else if (fileExt.equals(".obj"))
		{
			routeFinder = RouteFinder.load(file);
		}
		else
		{
			errorText.setText("File type not supported.");
			System.out.println("An error occured. [2]");
			return;
		}
		if (routeFinder == null)
		{
			// stage.setScene(ErrorScreen.createScene(stage));
			errorText.setText("File not compatible.");
			System.out.println("An error occured. [3]");
			return;
		}
		stage.setScene(MazeScreen.createScene(stage, routeFinder));
	}
}
