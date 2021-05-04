package maze.visualisation;

import maze.*;
import maze.routing.*;

import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/**
* Class that displays the save screen in the application
* @author Joe Higgitt
* @version 1.0, 4th May 2021
*/
public class SaveScreen
{
	private static VBox root;
	private static HBox buttons;
	private static Label appTitle, screenTitle, infoText, errorText;
	private static Button saveButton, backButton, menuButton;
	private static final FileChooser fileChooser = new FileChooser();

	/**
	* Creates the save screen
	* @param stage the main stage for the application
	* @param routeFinder the route being saved
	* @return Returns a {@link javafx.scene.Scene} object which contains the screen's content
	*/
	public static Scene createScene(Stage stage, RouteFinder routeFinder)
	{
		root = Graphics.createVBox();
		buttons = Graphics.createHBox();

		appTitle = Graphics.createTitle("MazeSolver Pro", 40);
		screenTitle = Graphics.createTitle("Save Route", 30);
		infoText = Graphics.createText("Save your current route progress as a route object file (.obj)");
		errorText = Graphics.createText("");

		fileChooser.setTitle("Save Route");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Object files (*.obj)", "*.obj"));

		saveButton = Graphics.createButton("Save File...");
		saveButton.setOnAction(e -> save(stage, routeFinder));

		backButton = Graphics.createButton("Back");
		backButton.setOnAction(e ->
		{
			stage.setScene(MazeScreen.createScene(stage, routeFinder));
		});

		menuButton = Graphics.createButton("Menu");
		menuButton.setOnAction(e ->
		{
			stage.setScene(MenuScreen.createScene(stage));
		});

		buttons.getChildren().addAll(backButton, menuButton);
		root.getChildren().addAll(appTitle, screenTitle, infoText, saveButton, errorText, buttons);
		return new Scene(root, stage.getWidth(), stage.getHeight());
	}

	private static void save(Stage stage, RouteFinder routeFinder)
	{
		File file = null;
		file = fileChooser.showSaveDialog(stage);
		if (file == null)
		{
			return;
		}
		routeFinder.save(file);
		stage.setScene(MenuScreen.createScene(stage));
	}
}
