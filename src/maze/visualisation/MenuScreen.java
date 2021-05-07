package maze.visualisation;

import javafx.application.Platform;

import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
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
	private static Label appTitle, screenTitle, infoText;
	private static Button fromTextButton, loadButton, quitButton;

	/**
	* Creates the menu screen
	* @param stage the main stage for the application
	* @return Returns a {@link javafx.scene.Scene} object which contains the screen's content
	*/
	public static Scene createScene(Stage stage)
	{
		return createScene(stage, "");
	}

	/**
	* Creates the menu screen with some information text
	* @param stage the main stage for the application
	* @param info the information wanting to be displayed
	* @return Returns a {@link javafx.scene.Scene} object which contains the screen's content
	*/
	public static Scene createScene(Stage stage, String info)
	{
		root = Graphics.createVBox();

		appTitle = Graphics.createTitle("MazeSolver Pro", 40);
		screenTitle = Graphics.createTitle("Menu", 30);
		infoText = Graphics.createText(info);

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

		root.getChildren().addAll(appTitle, screenTitle, fromTextButton, loadButton, quitButton, infoText);
		return new Scene(root, stage.getWidth(), stage.getHeight() - 26);
	}
}
