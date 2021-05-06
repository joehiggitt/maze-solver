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

/**
* Class that creates a route finder object, which solves a maze step by step.
* @author Joe Higgitt
* @version 1.0, 4th May 2021
* @see maze.Maze
*/
public class RouteFinder implements Serializable
{
	private Maze maze;
	private Stack<Tile> route;
	private boolean finished;
	private List<Tile> visited; // used to mark deadends

	/**
	* Constructs a route finder object.
	* @param newMaze the {@link maze.Maze} too be solved
	*/
	public RouteFinder(Maze newMaze)
	{
		maze = newMaze;
		route = new Stack<>();
		route.push(maze.getEntrance());
		finished = false;
		visited = new ArrayList<>();
	}

	/**
	* Gets the current maze being solved.
	* @return Returns a {@link maze.Maze} object
	*/
	public Maze getMaze()
	{
		return maze;
	}

	/**
	* Gets the current route that's been taken.
	* @return Returns a {@link java.util.List} of {@link maze.Tile} objects
	*/
	public List<Tile> getRoute()
	{
		return new ArrayList<>(route);
	}

	/**
	* Determines if the maze has been solved yet.
	* @return Returns true if the maze has been solved, returns false otherwise
	*/
	public boolean isFinished()
	{
		return finished;
	}

	/**
	* Creates a RouteFinder object from a file.
	* @param filePath the file path of the file being converted
	* @return Returns a RouteFinder object
	*/
	public static RouteFinder load(String filePath)
	{
		return load(new File(filePath));
	}

	/**
	* Creates a RouteFinder object from a file.
	* @param file a {@link java.io.File} object to be converted
	* @return Returns a RouteFinder object
	*/
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
			// System.out.println(filePath + " read successfully.");
		}
		catch (FileNotFoundException e)
		{
			// System.out.println(filePath + " not found.");
		}
		catch (InvalidClassException e)
		{
			// System.out.println(filePath + " is not compatible.");
		}
		catch (IOException e)
		{
			// System.out.println("An error occured. [1]");
			// e.printStackTrace();
		}
		finally
		{
			return route;
		}
	}

	/**
	* Saves the current RouteFinder object in a file.
	* @param filePath the file path for the RouteFinder to be stored in
	*/
	public void save(String filePath)
	{
		save(new File(filePath));
	}

	/**
	* Saves the current RouteFinder object in a file.
	* @param file a {@link java.io.File} object for the RouteFinder to be stored in
	*/
	public void save(File file)
	{
		String filePath = file.getAbsolutePath();
		try
		(
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))
		)
		{
			writer.writeObject(this);
			// System.out.println(filePath + " created successfully.");
		}
		catch (FileNotFoundException e)
		{
			// System.out.println(filePath + " not found.");
		}
		catch (InvalidClassException e)
		{
			// System.out.println(filePath + " is not compatible.");
		}
		catch (IOException e)
		{
			// System.out.println("An error occured. [2]");
			// e.printStackTrace();
		}
		finally {}
	}

	// REQUIRES TESTING

	/**
	* Takes a step through the maze. Multiple uses of the method will either solve the maze or throw a {@link maze.routing.NoRouteFoundException}.
	* @return Returns true if the maze has been solved, returns false otherwise
	* @throws maze.routing.NoRouteFoundException Indicates that the current maze doesn't have a solution
	*/
	public boolean step() throws NoRouteFoundException
	{
		if (route.peek().getType() == Tile.Type.EXIT)
		{
			// System.out.println("Maze already solved.");
			return true;
		}

		Tile currentTile = route.pop();
		List<Tile> adjacentTiles = new ArrayList<>();
		Maze.Direction[] directions = Maze.Direction.values();
		Tile tile = null;

		for (Maze.Direction direction: directions)
		{
			tile = maze.getAdjacentTile(currentTile, direction);
			if ((tile != null) && tile.isNavigable())
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
			// If the current tile is the entrance, then it moves to the next tile
			if (currentTile.getType() == Tile.Type.ENTRANCE)
			{
				route.push(currentTile);
				route.push(selectedTile);
			}
			// If the adjacent tile has already been visited (it's a dead end), then it backtracks
			else if ((route.size() > 1) && (selectedTile == route.peek()))
			{
				visited.add(currentTile);
			}
			// If the previous two cases are false, then there is no solution and an exception is thrown
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
			int check = 0;
			for (Tile adjacentTile: adjacentTiles)
			{
				heuristic = maze.calculateHeuristic(adjacentTile);
				// If an adjacent tile has been marked as visited, then current tile is also marked as visited
				if (visited.contains(adjacentTile))
				{
					check++;
					if (check == adjacentTiles.size() - 1)
					{
						visited.add(currentTile);
					}
				}
				else if ((!route.contains(adjacentTile) || (route.contains(adjacentTile) && visited.contains(currentTile))) && (heuristic < minHeuristic))
				{
					minHeuristic = heuristic;
					selectedTile = adjacentTile;
				}
			}
			if (!visited.contains(currentTile))
			{
				route.push(currentTile);
			}
			else if (selectedTile == null)
			{
				for (Tile adjacentTile: adjacentTiles)
				{
					if (!visited.contains(adjacentTile))
					{
						selectedTile = adjacentTile;
						break;
					}
				}
			}
			if ((selectedTile != null) && !route.contains(selectedTile))
			{
				route.push(selectedTile);
			}
			// else
			// {
			// 	throw new NoRouteFoundException();
			// }
		}
		if (route.peek().getType() == Tile.Type.EXIT)
		{
			finished = true;
			return true;
		}
		return false;
	}

	/**
	* Gets the String representation of the curent route being taken through the maze.
	* @return Returns a {@link java.lang.String} object containing a visual representation of the route
	*/
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