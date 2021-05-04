package maze.visualisation;

import javafx.application.Platform;

import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

/**
* Class that displays the menu screen in the application
* @author Joe Higgitt
* @version 1.0, 4th May 2021
*/
public class MenuScreen
{
	private static VBox root;
	private static HBox buttons;
	private static Label appTitle, screenTitle;
	private static Button fromTextButton, loadButton, quitButton;

	/**
	* Creates the menu screen
	* @param stage the main stage for the application
	* @return Returns a {@link javafx.scene.Scene} object which contains the screen's content
	*/
	public static Scene createScene(Stage stage)
	{
		root = Graphics.createVBox();
		buttons = Graphics.createHBox();

		appTitle = Graphics.createTitle("MazeSolver Pro", 40);
		screenTitle = Graphics.createTitle("Menu", 30);

		fromTextButton = Graphics.createButton("Upload From Plain Text");
		fromTextButton.setOnAction(e ->
		{
			stage.setScene(TextInputScreen.createScene(stage));
		});

		loadButton = Graphics.createButton("Load From File");
		loadButton.setOnAction(e ->
		{
			stage.setScene(LoadScreen.createScene(stage));
		});

		quitButton = Graphics.createButton("Quit");
		quitButton.setOnAction(e -> Platform.exit());

		buttons.getChildren().addAll(fromTextButton, loadButton, quitButton);
		root.getChildren().addAll(appTitle, screenTitle, buttons);
		return new Scene(root, stage.getWidth(), stage.getHeight());
	}
}
