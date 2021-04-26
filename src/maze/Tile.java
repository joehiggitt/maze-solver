package maze;

public class Tile
{
	private Type type;
	// private float heuristic;

	private Tile(Type newType)
	{
		type = newType;
		// if (!isNavigable())
		// {
		// 	heuristic = -1;
		// }
		// else
		// {
			
		// }
	}

	// REQUIRES TESTING
	protected static Tile fromChar(char symbol)
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
				System.out.println("An error occured in Tile.fromChar()");
				return null;
		}
	}

	public Type getType()
	{
		return type;
	}

	// REQUIRES TESTING
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

	// REQUIRES TESTING
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


	enum Type
	{
		CORRIDOR,
		ENTRANCE,
		EXIT,
		WALL
	}
}