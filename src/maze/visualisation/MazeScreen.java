package maze.visualisation;

import maze.*;
import maze.routing.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.geometry.Pos;

/**
* Class that displays the maze solving screen in the application.
* @author Joe Higgitt
* @version 1.0, 4th May 2021
*/
public class MazeScreen
{
	private static RouteFinder routeFinder;
	private static boolean check;
	private static Image[] images;
	private static List<List<ImageView>> gridContent;

	private static VBox root;
	private static HBox buttons;
	private static GridPane mazeGrid;
	private static Label screenTitle, infoText;
	private static Button stepButton, saveButton, backButton;

	/**
	* Creates the maze solving screen
	* @param stage the main stage for the application
	* @param route the current route being found
	* @return Returns a {@link javafx.scene.Scene} object which contains the screen's content
	*/
	public static Scene createScene(Stage stage, RouteFinder route)
	{
		routeFinder = route;
		check = false;
		images = Graphics.getImages();

		root = Graphics.createVBox();
		buttons = Graphics.createHBox();

		screenTitle = Graphics.createTitle("Your Maze", 30);
		infoText = Graphics.createText("");


		mazeGrid = createGrid();
		mazeGrid.setAlignment(Pos.CENTER);


		stepButton = Graphics.createButton("Step");
		stepButton.setOnAction(e -> 
		{
			try
			{
				if (routeFinder.step())
				{
					if (check)
					{
						infoText.setText("Your maze has already been solved");
					}
					else
					{
						infoText.setText("Your maze has a solution!");
					}
					check = true;
				}
			}
			catch (NoRouteFoundException ex)
			{
				infoText.setText("Unfortunately, no route could be found in your maze");
				// System.out.println("An error occured.");
				// ex.printStackTrace();
			}
			updateGrid(mazeGrid);
		});

		saveButton = Graphics.createButton("Save");
		saveButton.setOnAction(e ->
		{
			stage.setScene(SaveScreen.createScene(stage, routeFinder));
		});

		backButton = Graphics.createButton("Menu");
		backButton.setOnAction(e ->
		{
			stage.setScene(MenuScreen.createScene(stage));
		});

		buttons.getChildren().addAll(saveButton, backButton);
		root.getChildren().addAll(screenTitle, mazeGrid,  infoText, stepButton, buttons);
		return new Scene(root, stage.getWidth(), stage.getHeight());
	}

	private static GridPane createGrid()
	{
		GridPane grid = new GridPane();
		List<List<Tile>> tiles = routeFinder.getMaze().getTiles();
		gridContent = new ArrayList<>();
		boolean entrancePath = false;
		boolean exitPath = false;
		for (int i = 0; i < tiles.size() + 2; i++)
		{
			gridContent.add(new ArrayList<>());
			if ((i > 0) && (i <= tiles.size()) && (!entrancePath) && (tiles.get(i - 1).get(0).getType() == Tile.Type.ENTRANCE))
			{
				gridContent.get(i).add(Graphics.createGridImage(images[1]));
				entrancePath = true;
			}
			else if ((i > 0) && (i <= tiles.size()) && (!exitPath) && (tiles.get(i - 1).get(0).getType() == Tile.Type.EXIT))
			{
				gridContent.get(i).add(Graphics.createGridImage(images[0]));
				exitPath = true;
			}
			else
			{
				gridContent.get(i).add(Graphics.createGridImage(images[7]));
			}
			grid.add(gridContent.get(i).get(0), i, 0);
		}
		for (int i = 0; i < tiles.size() + 2; i++)
		{
			for (int j = 0; j < tiles.get(0).size(); j++)
			{
				gridContent.get(i).add(null);
			}
			int j = tiles.get(0).size() + 1;
			if ((i > 0) && (i <= tiles.size()) && (!entrancePath) && (tiles.get(i - 1).get(0).getType() == Tile.Type.ENTRANCE))
			{
				gridContent.get(i).add(Graphics.createGridImage(images[1]));
				entrancePath = true;
			}
			else if ((i > 0) && (i <= tiles.size()) && (!exitPath) && (tiles.get(i - 1).get(0).getType() == Tile.Type.EXIT))
			{
				gridContent.get(i).add(Graphics.createGridImage(images[0]));
				exitPath = true;
			}
			else
			{
				gridContent.get(i).add(Graphics.createGridImage(images[7]));
			}
			grid.add(gridContent.get(i).get(j), i, j);
		}
		for (int j = 1; j < tiles.get(0).size() + 1; j++)
		{
			if ((!entrancePath) && (tiles.get(0).get(tiles.get(0).size() - j).getType() == Tile.Type.ENTRANCE))
			{
				gridContent.get(0).set(j, Graphics.createGridImage(images[1]));
				entrancePath = true;
			}
			else if ((!exitPath) && (tiles.get(0).get(tiles.get(0).size() - j).getType() == Tile.Type.EXIT))
			{
				gridContent.get(0).set(j, Graphics.createGridImage(images[0]));
				exitPath = true;
			}
			else
			{
				gridContent.get(0).set(j, Graphics.createGridImage(images[7]));
			}
			grid.add(gridContent.get(0).get(j), 0, j);
		}
		for (int j = 1; j < tiles.get(0).size() + 1; j++)
		{
			int i = tiles.size() + 1;
			if ((!entrancePath) && (tiles.get(i - 2).get(tiles.get(0).size() - j).getType() == Tile.Type.ENTRANCE))
			{
				gridContent.get(i).set(j, Graphics.createGridImage(images[1]));
				entrancePath = true;
			}
			else if ((!exitPath) && (tiles.get(i - 2).get(tiles.get(0).size() - j).getType() == Tile.Type.EXIT))
			{
				gridContent.get(i).set(j, Graphics.createGridImage(images[0]));
				exitPath = true;
			}
			else
			{
				gridContent.get(i).set(j, Graphics.createGridImage(images[7]));
			}
			grid.add(gridContent.get(i).get(j), i, j);
		}

		for (int i = 0; i < tiles.size(); i++)
		{
			for (int j = 0; j < tiles.get(i).size(); j++)
			{
				int x = i + 1;
				int y = tiles.get(i).size() - j;
				switch(tiles.get(i).get(j).getType())
				{
					case CORRIDOR:
						gridContent.get(x).set(y, Graphics.createGridImage(images[0]));
						break;
					case ENTRANCE:
						gridContent.get(x).set(y, Graphics.createGridImage(images[2]));
						break;
					case EXIT:
						gridContent.get(x).set(y, Graphics.createGridImage(images[4]));
						break;
					case WALL:
						gridContent.get(x).set(y, Graphics.createGridImage(images[6]));
						break;
					default:
					// 	System.out.println("An error occured in MazeScreen.createGrid()");
						break;
				}
				grid.add(gridContent.get(x).get(y), x, y);
			}
		}
		return updateGrid(grid);
	}

	private static GridPane updateGrid(GridPane grid)
	{
		Maze maze = routeFinder.getMaze();
		List<List<Tile>> tiles = maze.getTiles();
		List<Tile> route = routeFinder.getRoute();
		Tile tile = null;
		
		for (int i = 0; i < tiles.size(); i++)
		{
			for (int j = 0; j < tiles.get(i).size(); j++)
			{
				int x = i + 1;
				int y = tiles.get(0).size() - j;
				tile = tiles.get(i).get(j);
				if (tile.getType() != Tile.Type.WALL)
				{
					grid.getChildren().remove(gridContent.get(x).get(y));
					switch(tile.getType())
					{
						case CORRIDOR:
							if (route.contains(tile))
							{
								gridContent.get(x).set(y, Graphics.createGridImage(images[1]));
							}
							else
							{
								gridContent.get(x).set(y, Graphics.createGridImage(images[0]));
							}
							break;
						case ENTRANCE:
							if (route.contains(tile))
							{
								gridContent.get(x).set(y, Graphics.createGridImage(images[3]));
							}
							else
							{
								gridContent.get(x).set(y, Graphics.createGridImage(images[2]));
							}
							break;
						case EXIT:
							if (route.contains(tile))
							{
								gridContent.get(x).set(y, Graphics.createGridImage(images[5]));
							}
							else
							{
								gridContent.get(x).set(y, Graphics.createGridImage(images[4]));
							}
							break;
					}
					grid.add(gridContent.get(x).get(y), x, y);
				}
			}
		}
		return grid;
	}
}