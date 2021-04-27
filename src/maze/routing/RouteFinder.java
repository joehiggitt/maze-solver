package maze.routing;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import java.io.Serializable;
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
	private List<Tile> visited;

	public RouteFinder(Maze newMaze)
	{
		maze = newMaze;
		route = new Stack<>();
		finished = false;
		route.push(maze.getEntrance());
	}

	public Maze getMaze()
	{
		return maze;
	}

	// REQUIRES TESTING
	public List<Tile> getRoute()
	{
		Stack<Tile> temp = route;
		List<Tile> tileList = ArrayList<>();
		for (int i = 0; i < route.size(); i++)
		{
			tileList.add(temp.pop());
		}
		return Collections.reverse(tileList);
	}

	public boolean isFinished()
	{
		return finished;
	}

	// REQUIRES TESTING
	public static RouteFinder load(String filePath)
	{
		RouteFinder route = null;
		try
		(
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream(filePath))
		)
		{
			route = reader.readObject();
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

	// REQUIRES TESTING
	public void save(String filePath)
	{
		try
		(
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filePath))
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

	// REQUIRES WORK
	public boolean step() throws NoRouteFoundException
	{
		if (route.peek().getType() == Tile.Type.EXIT)
		{
			return true;
		}

		Tile currentTile = route.pop();
		List<Tile> adjacentTiles = ArrayList<>();
		Maze.Direction[] directions = Maze.Direction.values();
		Tile tile;
		for (Maze.Direction direction: directions)
		{
			tile = maze.getAdjacentTile(currentTile, direction);
			if ((tile != null) && (tile.getType() != Tile.Type.WALL))
			{
				adjacentTiles.add(tile);
			}
		}

		if (adjacentTiles.size() == 0)
		{
			route.push(currentTile);
			throw new NoRouteFoundException;
		}
		else if (adjacentTiles.size() == 1)
		{
			tile = adjacentTiles.get(0);
			if(tile == route.peek())
			{
				if (route.size() == 0)
				{
					route.push(currentTile);
					throw new NoRouteFoundException;
				}
				visited.add(currentTile);
				if (selectedTile.getType() == Tile.Type.EXIT)
				{
					return true;
				}
				return false;
			}
			else
			{
				route.push(currentTile);
				route.push(tile);
				return false;
			}
		}
		else
		{
			int maxHeuristic = 0;
			Tile selectedTile = null;
			for (Tile tile: adjacentTiles)
			{
				if (visited.contains())
				{
					
				}
				heuristic = maze.getHeuristic(tile);
				if (heuristic > maxHeuristic)
				{
					maxHeuristic = heuristic;
					selectedTile = tile;
				}
			}
			route.push(currentTile);
			route.push(selectedTile);
			if (selectedTile.getType() == Tile.Type.EXIT)
			{
				return true;
			}
			return false;
		}
	}

	// REQUIRES WORK
	public String toString()
	{
		String mazeStr = maze.toString();
		return a;
	}
}