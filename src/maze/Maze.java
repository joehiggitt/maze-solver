package maze;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import java.io.Serializable;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InvalidClassException;

import java.lang.Math;

/**
* Class that creates a maze object which can then be solved.
* @author Joe Higgitt
* @version 1.0, 4th May 2021
*/
public class Maze implements Serializable
{
	private Tile entrance;
	private Tile exit;
	private List<List<Tile>> tiles;

	private Maze()
	{
		tiles = new ArrayList<>();
	}

	private Maze(List<List<Tile>> newTiles)
	{
		tiles = newTiles;
	}

	private static Maze convert(List<List<String>> lines) throws InvalidMazeException
	{
		int size = lines.get(0).size();
		for (int i = 1; i < lines.size(); i++)
		{
			if (lines.get(i).size() != size)
			{
				throw new RaggedMazeException();
			}
		}

		int entrances = 0;
		int exits = 0;
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
				char chr = lines.get(i).get(j).charAt(0);
				Tile tile = Tile.fromChar(chr);
				tileList.get(j).add(tile);
				if (chr == 'e')
				{
					entrances++;
				}
				else if (chr == 'x')
				{
					exits++;
				}
			}
		}

		if (entrances == 0)
		{
			throw new NoEntranceException();
		}
		else if (entrances > 1)
		{
			throw new MultipleEntranceException();
		}
		else if (exits == 0)
		{
			throw new NoExitException();
		}
		else if (exits > 1)
		{
			throw new MultipleExitException();
		}

		Maze maze = new Maze(tileList);

		for (int i = 0; i < tileList.size(); i++)
		{
			for (int j = tileList.get(i).size() - 1; j >= 0; j--)
			{
				Tile tile = tileList.get(i).get(j);
				if (tile.getType() == Tile.Type.ENTRANCE)
				{
					if ((i == 0) || (j == 0) || (i == tileList.size() - 1) || (j == tileList.get(0).size() - 1))
					{
						maze.setEntrance(tile);
						break;
					}
					else
					{
						throw new InvalidMazeException("Entrance must be at the edge of maze.");
					}
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
					if ((i == 0) || (j == 0) || (i == tileList.size() - 1) || (j == tileList.get(0).size() - 1))
					{
						maze.setExit(tile);
						break;
					}
					else
					{
						throw new InvalidMazeException("Exit must be at the edge of maze.");
					}
				}
			}
			if (maze.getExit() != null)
			{
				break;
			}
		}
		return maze;
	}

	/**
	* Creates a Maze object from a {@link java.lang.String} representation of the maze.
	* @param mazeStr the String representation of the maze
	* @return Returns a Maze object which stores the maze
	* @throws maze.InvalidMazeException Indicates that maze trying to be converted to an object is invalid
	*/
	public static Maze fromStr(String mazeStr) throws InvalidMazeException
	{
		List<List<String>> lines = new ArrayList<>();
		String[] mazeArray = mazeStr.split("\n");
		for (int i = 0; i < mazeArray.length; i++)
		{
			lines.add(Arrays.asList(mazeArray[i].split("")));
		}
		return convert(lines);
	}

	/**
	* Creates a maze object from a file.
	* @param filePath the file path of the file being converted
	* @return Returns a maze object
	* @throws maze.InvalidMazeException Indicates that maze trying to be converted to an object is invalid
	*/
	public static Maze fromTxt(String filePath) throws InvalidMazeException
	{
		return fromTxt(new File(filePath));
	}

	/**
	* Creates a Maze object from a file.
	* @param file a {@link java.io.File} object to be converted
	* @return Returns a Maze object
	* @throws maze.InvalidMazeException Indicates that maze trying to be converted to an object is invalid
	*/
	public static Maze fromTxt(File file) throws InvalidMazeException
	{
		List<List<String>> lines = new ArrayList<>();
		String line;
		String filePath = file.getAbsolutePath();
		try
		(
			BufferedReader reader = new BufferedReader(new FileReader(file));
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
			// System.out.println(filePath + " read successfully.");
		}
		catch (FileNotFoundException e)
		{
			// System.out.println(filePath + " not found.");
			return null;
		}
		catch (InvalidClassException e)
		{
			// System.out.println(filePath + " is not compatible.");
			return null;
		}
		catch (IOException e)
		{
			// System.out.println("An error occured in Maze.fromTxt() [1]");
			// e.printStackTrace();
			return null;
		}
		finally
		{
			return convert(lines);
		}
	}

	/**
	* Gets the adjacent tile to the current tile in a certain direction.
	* @param tile the current tile
	* @param dir the direction of the adjacent tile wanted
	* @return Returns a {@link maze.Tile} object for the adjacent tile as long as the adjacent tile is within the grid, returns null otherwise
	*/
	public Tile getAdjacentTile(Tile tile, Direction dir)
	{
		Coordinate coord = getTileAtLocation(tile);
		int x = coord.getX();
		int y = coord.getY();
		switch (dir)
		{
			case NORTH:
				if (y < tiles.get(0).size() - 1)
				{
					return tiles.get(x).get(y + 1);
				}
				// System.out.println("Can't go north");
				return null;
			case SOUTH:
				if (y > 0)
				{
					return tiles.get(x).get(y - 1);
				}
				// System.out.println("Can't go south");
				return null;
			case EAST:
				if (x < tiles.size() - 1)
				{
					return tiles.get(x + 1).get(y);
				}
				// System.out.println("Can't go east");
				return null;
			case WEST:
				if (x > 0)
				{
					return tiles.get(x - 1).get(y);
				}
				// System.out.println("Can't go west");
				return null;
			default:
				// System.out.println("An error occured in Maze.getAdjacentTile()");
				return null;
		}
	}

	/**
	* Gets the entrance of the current maze.
	* @return Returns a {@link maze.Tile} object of the entrance
	*/
	public Tile getEntrance()
	{
		return entrance;
	}

	/**
	* Gets the exit of the current maze.
	* @return Returns a {@link maze.Tile} object of the exit
	*/
	public Tile getExit()
	{
		return exit;
	}

	/**
	* Gets the tile at a given coordinate.
	* @param coord the coordinates of the tile wanted
	* @return Returns a {@link maze.Tile} object
	*/
	public Tile getTileAtLocation(Coordinate coord)
	{
		int x = coord.getX();
		int y = coord.getY();
		if ((x < tiles.size()) && (x >= 0) && (y < tiles.get(0).size()) && (y >= 0))
		{
			return tiles.get(x).get(y);
		}
		// System.out.println("Tile not found [1]");
		return null;
	}

	/**
	* Gets the coordinates of a given tile.
	* @param tile the tile whose coordinates are wanted
	* @return Returns a {@link maze.Maze.Coordinate} object
	*/
	public Coordinate getTileAtLocation(Tile tile)
	{
		for (int x = 0; x < tiles.size(); x++)
		{
			for (int y = 0; y < tiles.get(x).size(); y++)
			{
				if (tiles.get(x).get(y) == tile)
				{
					return new Coordinate(x, y);
				}
			}
		}
		// System.out.println("Tile not found [2]");
		return null;
	}

	/**
	* Gets the current maze tiles.
	* @return Returns a {@link java.util.List} of {@link maze.Tile} objects
	*/
	public List<List<Tile>> getTiles()
	{
		return tiles;
	}

	private void setEntrance(Tile newEntrance) throws InvalidMazeException
	{
		if (newEntrance.getType() != Tile.Type.ENTRANCE)
		{
			throw new InvalidMazeException("Tile of type " + newEntrance.getType() + " tried to become entrance");
		}
		else if (getTileAtLocation(newEntrance) == null)
		{
			throw new InvalidMazeException("Tile not in maze");
		}
		else if (entrance != null)
		{
			throw new MultipleEntranceException();
		}
		entrance = newEntrance;
	}

	private void setExit(Tile newExit) throws InvalidMazeException
	{
		if (exit != null)
		{
			throw new MultipleExitException();
		}
		else if (newExit.getType() != Tile.Type.EXIT)
		{
			throw new InvalidMazeException("Tile of type " + newExit.getType() + " tried to become exit");
		}
		else if (getTileAtLocation(newExit) == null)
		{
			throw new InvalidMazeException("Tile not in maze");
		}
		exit = newExit;
	}

	/**
	* Gets the String representation of the maze.
	* @return Returns a {@link java.lang.String} object containing a visual representation of the maze
	*/
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
				int pos = tiles.get(i).size() - 1 - j;
				mazeList.set(pos, mazeList.get(pos) + spaces + tileStr);
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


	/**
	* Calculates the heuristic between the current tile and the exit.
	* @param tile the current tile
	* @return Returns an int representing the 'block' distance to the exit
	*/
	public int calculateHeuristic(Tile tile)
	{
		Tile.Type type = tile.getType();
		if (type == Tile.Type.WALL)
		{
			return -1;
		}
		if (type == Tile.Type.EXIT)
		{
			return 0;
		}
		int tileX = getTileAtLocation(tile).getX();
		int tileY = getTileAtLocation(tile).getY();
		int exitX = getTileAtLocation(exit).getX();
		int exitY = getTileAtLocation(exit).getY();
		return Math.abs(exitX - tileX) + Math.abs(exitY - tileY);
		// int xSquared = (int) Math.pow(exitX - tileX, 2);
		// int ySquared = (int) Math.pow(exitY - tileY, 2);
		// return (int) Math.round(Math.sqrt(xSquared + ySquared));
	}


	/**
	* Class that defines a coordinate object which can represent a tile in the maze.
	* @author Joe Higgitt
	* @version 1.0, 4th May 2021
	* @see maze.Maze
	*/
	public class Coordinate
	{
		private int x;
		private int y;

		/**
		* Constructs a new coordinate object
		* @param newX the x coordinate
		* @param newY the y coordinate
		*/
		public Coordinate(int newX, int newY)
		{
			x = newX;
			y = newY;
		}

		/**
		* Gets the x value of the coordinate.
		* @return Returns an int representing the x coordinate
		*/
		public int getX()
		{
			return x;
		}

		/**
		* Gets the y value of the coordinate.
		* @return Returns an int representing the y coordinate
		*/
		public int getY()
		{
			return y;
		}

		/**
		* Gets a String representation of the coordinate.
		* @return Returns an {@link java.lang.String} object representing the current coordinates
		*/
		public String toString()
		{
			return "(" + x + ", " + y + ")";
		}
	}

	/**
	* Enumerate that defines a direction which can be taken in the maze.
	* @author Joe Higgitt
	* @version 1.0, 4th May 2021
	* @see maze.Maze
	*/
	public enum Direction
	{
		NORTH,
		SOUTH,
		EAST,
		WEST
	}
}