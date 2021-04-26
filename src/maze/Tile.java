package maze

public class Tile
{
	private Type type;

	private Tile(Type newType)
	{
		type = newType;
	}

	// REQUIRES WORK
	protected static Tile fromChar(char x)
	{

		return a;
	}

	public Type getType()
	{
		return type;
	}

	// REQUIRES WORK
	public boolean isNavigable()
	{

		return a;
	}

	// REQUIRES WORK
	public String toString()
	{

		return a;
	}


	enum Type
	{
		CORRIDOR,
		ENTRANCE,
		EXIT,
		WALL
	}
}