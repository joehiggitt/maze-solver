import maze.visualisation.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
* Class that runs the MazeSolver Pro application
* @author Joe Higgitt
* @version 1.0, 4th May 2021
<!-- *	@see	java.nio.file.Path -->
*/	
public class MazeApplication extends Application
{
	/**
	* Starts the application when called.
	* @param stage the stage which the application is opened into
	*/
	@Override
	public void start(Stage stage)
	{
		stage.setScene(MenuScreen.createScene(stage));

		stage.setTitle("MazeSolver Pro");
		stage.setMinHeight(500);
		stage.setMinWidth(600);
		stage.show();
	}

	/**
	* Starts the application when run.
	* @param args the arguments passed into the program
	*/
	public static void main(String[] args)
	{
		launch(args);
	}
}
