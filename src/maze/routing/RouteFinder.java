package maze.routing;

import maze.*;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import java.io.Serializable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InvalidClassException;

public class RouteFinder implements Serializable
{
	private Maze maze;
	private Stack<Tile> route;
	private boolean finished;
	private List<Tile> visited; // used to mark deadends

	public RouteFinder(Maze newMaze)
	{
		maze = newMaze;
		route = new Stack<>();
		finished = false;
		route.push(maze.getEntrance());
		visited = new ArrayList<>();
	}

	public Maze getMaze()
	{
		return maze;
	}

	public List<Tile> getRoute()
	{
		return new ArrayList<>(route);
	}

	public boolean isFinished()
	{
		return finished;
	}

	public static RouteFinder load(String filePath)
	{
		return load(new File(filePath));
	}

	public static RouteFinder load(File file)
	{
		String filePath = file.getAbsolutePath();
		RouteFinder route = null;
		try
		(
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))
		)
		{
			route = (RouteFinder) reader.readObject();
			System.out.println(filePath + " read successfully.");
		}
		catch (FileNotFoundException e)
		{
			System.out.println(filePath + " not found.");
		}
		catch (InvalidClassException e)
		{
			System.out.println(filePath + " is not compatible.");
		}
		catch (IOException e)
		{
			System.out.println("An error occured.");
			e.printStackTrace();
		}
		finally
		{
			return route;
		}
	}

	public void save(String filePath)
	{
		save(new File(filePath));
	}

	public void save(File file)
	{
		String filePath = file.getAbsolutePath();
		try
		(
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))
		)
		{
			writer.writeObject(this);
			System.out.println(filePath + " created successfully.");
		}
		catch (FileNotFoundException e)
		{
			System.out.println(filePath + " not found.");
		}
		catch (InvalidClassException e)
		{
			System.out.println(filePath + " is not compatible.");
		}
		catch (IOException e)
		{
			System.out.println("An error occured.");
			e.printStackTrace();
		}
		finally {}
	}

	// REQUIRES TESTING
	public boolean step() throws NoRouteFoundException
	{
		if (route.peek().getType() == Tile.Type.EXIT)
		{
			System.out.println("Maze already solved.");
			return true;
		}

		Tile currentTile = route.pop();
		List<Tile> adjacentTiles = new ArrayList<>();
		Maze.Direction[] directions = Maze.Direction.values();
		Tile tile = null;

		for (Maze.Direction direction: directions)
		{
			tile = maze.getAdjacentTile(currentTile, direction);
			if ((tile != null) && (tile.getType() != Tile.Type.WALL))
			{
				adjacentTiles.add(tile);
			}
		}

		Tile selectedTile = null;
		// Checks how many tiles are adjacent
		if (adjacentTiles.size() == 0)
		{
			// If there are no valid adjacent tiles, then an exception is thrown
			route.push(currentTile);
			throw new NoRouteFoundException();
		}
		else if (adjacentTiles.size() == 1)
		{
			selectedTile = adjacentTiles.get(0);
			// If the adjacent tile has already been visited (it's a dead end), then it backtracks
			if ((route.size() > 1) && (selectedTile == route.peek()))
			{
				visited.add(currentTile);
			}
			// If the current tile is the entrance, then it moves to the next tile
			else if (currentTile.getType() == Tile.Type.ENTRANCE)
			{
				route.push(currentTile);
				route.push(selectedTile);
			}
			// If the only previous tile is the entrance, then an exception is thrown
			else
			{
				route.push(currentTile);
				throw new NoRouteFoundException();
			}
		}
		else
		{
			// If there are multiple adjacent tiles, it uses a heuristic to estimate which is closest to exit
			int minHeuristic = 2147483647; // uses a large dummy value
			int heuristic = 0;
			selectedTile = null;
			for (Tile adjacentTile: adjacentTiles)
			{
				heuristic = maze.calculateHeuristic(adjacentTile);
				// If an adjacent tile has been marked as visited, then current tile is also marked as visited
				if (visited.contains(adjacentTile))
				{
					visited.add(currentTile);
				}
				else if (!route.contains(adjacentTile) && (heuristic < minHeuristic))
				{
					minHeuristic = heuristic;
					selectedTile = adjacentTile;
				}
			}
			route.push(currentTile);
			if (selectedTile != null)
			{
				route.push(selectedTile);
			}
			else
			{
				throw new NoRouteFoundException();
			}
		}
		if (route.peek().getType() == Tile.Type.EXIT)
		{
			finished = true;
			return true;
		}
		return false;
	}

	public String toString()
	{
		List<List<Tile>> tiles = maze.getTiles();
		List<String> mazeList = new ArrayList<>();
		// Adds row numbers
		for (int yNum = tiles.get(0).size() - 1; yNum >= 0; yNum--)
		{
			if (tiles.get(0).size() > 10)
			{
				if (yNum < 10)
				{
					mazeList.add(" " + yNum + " ");
				}
				else
				{
					mazeList.add(yNum + " ");
				}
			}
			else
			{
				mazeList.add(yNum + " ");
			}
		}
		// Adds maze objects to mazeList
		String tileStr = "";
		String spaces = "";
		for (int i = 0; i < tiles.size(); i++)
		{
			for (int j = tiles.get(i).size() - 1; j >= 0; j--)
			{
				if (route.contains(tiles.get(i).get(j)))
				{
					tileStr = "*";
				}
				else
				{
					tileStr = tiles.get(i).get(j).toString();
				}
				if (tiles.size() > 10)
				{
					spaces = "  ";
				}
				else
				{
					spaces = " ";
				}
				mazeList.set(tiles.get(i).size() - 1 - j, mazeList.get(tiles.get(i).size() - 1 - j) + spaces + tileStr);
			}
		}
		String mazeStr = "";
		for (int i = 0; i < mazeList.size(); i++)
		{
			mazeStr += mazeList.get(i) + "\n";
		}
		mazeStr += "\n " + spaces;
		// Adds collumn numbers
		for (int xNum = 0; xNum < tiles.size(); xNum++)
		{
			if (tiles.size() > 10)
			{
				if (xNum < 10)
				{
					spaces = "  ";
				}
				else
				{
					spaces = " ";
				}
			}
			else
			{
				spaces = " ";
			}
			mazeStr += spaces + xNum;
		}
		return mazeStr;
	}
}