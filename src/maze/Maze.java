package maze;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InvalidClassException;

public class Maze
{
	private Tile entrance;
	private Tile exit;
	private List<List<Tile>> tiles;

	private Maze(List<List<Tile>> newTiles)
	{
		tiles = newTiles;
	}

	public static Maze fromTxt(String filePath)
	{
		List<List<String>> lines = new ArrayList<>();
		String line;
		try
		(
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
		)
		{
			while ((line = reader.readLine().trim()) != null)
			{
				if (line.equals("\n"))
				{
					break;
				}
				lines.add(Arrays.asList(line.split("")));
			}
			System.out.println(filePath + " read successfully.");
		}
		catch (FileNotFoundException e)
		{
			System.out.println(filePath + " not found.");
			return null;
		}
		catch (InvalidClassException e)
		{
			System.out.println(filePath + " is not compatible.");
			return null;
		}
		catch (IOException e)
		{
			System.out.println("An error occured in Maze.fromTxt() [1]");
			e.printStackTrace();
			return null;
		}
		finally
		{
			int size = lines.get(0).size();
			for (int i = 1; i < lines.size(); i++)
			{
				if (lines.get(i).size() != size)
				{
					System.out.println("An error occured in Maze.fromTxt() [2]");
					return null;
				}
			}

			// Creates tiles ArrayList and initilises maze
			List<List<Tile>> tileList = new ArrayList<>();
			for (int i = 0; i < lines.get(0).size(); i++)
			{
				tileList.add(new ArrayList<>());
			}
			for (int i = lines.size() - 1; i >= 0; i--)
			{
				for (int j = 0; j < lines.get(i).size(); j++)
				{
					Tile tile = Tile.fromChar(lines.get(i).get(j).charAt(0));
					tileList.get(j).add(tile);
				}
			}
			Maze maze = new Maze(tileList);

			for (int i = 0; i < tileList.size(); i++)
			{
				for (int j = tileList.get(i).size() - 1; j >= 0; j--)
				{
					Tile tile = tileList.get(i).get(j);
					if (tile.getType() == Tile.Type.ENTRANCE)
					{
						maze.setEntrance(tile);
						break;
					}
				}
				if (maze.getEntrance() != null)
				{
					break;
				}
			}
			for (int i = tileList.size() - 1; i >= 0; i--)
			{
				for (int j = 0; j < tileList.get(i).size(); j++)
				{
					Tile tile = tileList.get(i).get(j);
					if (tile.getType() == Tile.Type.EXIT)
					{
						maze.setExit(tile);
						break;
					}
				}
				if (maze.getExit() != null)
				{
					break;
				}
			}
			return maze;
		}
	}

	// REQUIRES TESTING
	public Tile getAdjacentTile(Tile tile, Direction dir)
	{
		Coordinate coord = getTileAtLocation(tile);
		int x = coord.getX();
		int y = coord.getY();
		switch (dir)
		{
			case NORTH:
				if (y < tiles.get(0).size())
				{
					return tiles.get(x).get(y);
				}
				return null;
			case SOUTH:
				if (y >= 0)
				{
					return tiles.get(x).get(y);
				}
				return null;
			case EAST:
				if (x < tiles.size())
				{
					return tiles.get(x).get(y);
				}
				return null;
			case WEST:
				if (x >= 0)
				{
					return tiles.get(x).get(y);
				}
				return null;
			default:
				System.out.println("An error occured in Maze.getAdjacentTile()");
				return null;
		}
	}

	public Tile getEntrance()
	{
		return entrance;
	}

	public Tile getExit()
	{
		return exit;
	}

	// REQUIRES TESTING
	public Tile getTileAtLocation(Coordinate coord)
	{
		int x = coord.getX();
		int y = coord.getY();
		if ((x < tiles.size()) && (x >= 0) && (y < tiles.get(0).size()) && (y >= 0))
		{
			return tiles.get(coord.getX()).get(coord.getY());
		}
		System.out.println("Tile not found [1]");
		return null;
	}

	public Coordinate getTileAtLocation(Tile tile)
	{
		for (int i = 0; i < tiles.size(); i++)
		{
			for (int j = 0; j < tiles.get(i).size(); j++)
			{
				if (tiles.get(i).get(j) == tile)
				{
					return new Coordinate(i, j);
				}
			}
		}
		System.out.println("Tile not found [2]");
		return null;
	}

	public List<List<Tile>> getTiles()
	{
		return tiles;
	}

	private void setEntrance(Tile newEntrance)
	{
		entrance = newEntrance;
	}

	private void setExit(Tile newExit)
	{
		exit = newExit;
	}

	public String toString()
	{
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
		String spaces = "";
		for (int i = 0; i < tiles.size(); i++)
		{
			for (int j = tiles.get(i).size() - 1; j >= 0; j--)
			{
				String tileStr = tiles.get(i).get(j).toString();
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


	public class Coordinate
	{
		private int x;
		private int y;

		public Coordinate(int newX, int newY)
		{
			x = newX;
			y = newY;
		}

		public int getX()
		{
			return x;
		}

		public int getY()
		{
			return y;
		}

		public String toString()
		{
			return "(" + x + ", " + y + ")";
		}
	}

	public enum Direction
	{
		NORTH,
		SOUTH,
		EAST,
		WEST
	}
}