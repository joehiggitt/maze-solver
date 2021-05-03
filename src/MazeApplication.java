// import maze.*;
// import maze.routing.*;
import maze.visualisation.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class MazeApplication extends Application
{
	@Override
	public void start(Stage stage)
	{
		stage.setScene(MenuScreen.createScene(stage));

		stage.setTitle("MazeSolver Pro");
		stage.setMinHeight(500);
		stage.setMinWidth(600);
		stage.show();
	}

	public static void main(String args[])
	{
		launch(args);
	}
}
