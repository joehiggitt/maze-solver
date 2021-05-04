package maze;

import java.io.Serializable;

/**
* Class that creates a tile object which can be used to create a maze.
* @author Joe Higgitt
* @version 1.0, 4th May 2021
* @see maze.Maze
*/
public class Tile implements Serializable
{
	private Type type;
	// private float heuristic;

	private Tile(Type newType)
	{
		type = newType;
	}

	/**
	* Creates a new tile object from a char.
	* @param symbol the char representation of the new tile's type
	* @return Returns a Tile object
	* @throws maze.InvalidCharacterException Indicates that character trying to be converted to a tile is invalid
	*/
	protected static Tile fromChar(char symbol) throws InvalidCharacterException
	{
		switch (symbol)
		{
			case '.':
				return new Tile(Type.CORRIDOR);
			case 'e':
				return new Tile(Type.ENTRANCE);
			case 'x':
				return new Tile(Type.EXIT);
			case '#':
				return new Tile(Type.WALL);
			default:
				throw new InvalidCharacterException();
		}
	}

	/**
	* Gets the type of a tile.
	* @return Returns a {@link maze.Tile.Type} object representing the type of the tile
	*/
	public Type getType()
	{
		return type;
	}

	/**
	* Gets whether an tile is navigable.
	* @return Returns true if a tile is navigable, returns false otherwise
	*/
	public boolean isNavigable()
	{
		switch (type)
		{
			case CORRIDOR:
				return true;
			case ENTRANCE:
				return true;
			case EXIT:
				return true;
			case WALL:
				return false;
			default:
				System.out.println("An error occured in Tile.isNavigable()");
				return false;
		}
	}

	/**
	* Gets the String representation of the tile.
	* @return Returns a {@link java.lang.String} object containing a visual representation of the tile
	*/
	public String toString()
	{
		switch (type)
		{
			case CORRIDOR:
				return ".";
			case ENTRANCE:
				return "e";
			case EXIT:
				return "x";
			case WALL:
				return "#";
			default:
				System.out.println("An error occured in Tile.toString()");
				return "⚠";
		}
	}


	/**
	* Enumerate that defines a type which a tile can take.
	* @author Joe Higgitt
	* @version 1.0, 4th May 2021
	* @see maze.Tile
	*/
	public enum Type
	{
		CORRIDOR,
		ENTRANCE,
		EXIT,
		WALL
	}
}