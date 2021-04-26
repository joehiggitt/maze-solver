package maze

import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;

public class Maze
{
	private Tile entrance;
	private Tile exit;
	private ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();

	// REQUIRES WORK
	private Maze()
	{

	}

	// REQUIRES WORK
	public static Tile fromTxt(String x)
	{
		
		return a;
	}

	// REQUIRES WORK
	public Tile getAdjacentTile(Tile x, Direction y)
	{
		
		return a;
	}

	public Tile getEntrance()
	{
		return entrance;
	}

	public Tile getExit()
	{
		return exit;
	}

	// REQUIRES WORK
	public Tile getTileAtLocation(Coordinate x)
	{

		return a;
	}

	// REQUIRES WORK
	public Coordinate getTileLocation(Tile x)
	{

		return a;
	}

	public List<List<Tile>> getTiles()
	{
		return tiles;
	}

	public void setEntrance(Tile newEntrance)
	{
		entrance = newEntrance;
	}

	public void setExit(Tile newExit)
	{
		exit = newExit;
	}

	// REQUIRES WORK
	public String toString()
	{

		return a;
	}


	class Coordinate
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

		// REQUIRES WORK
		public String toString()
		{

			return a;
		}
	}

	enum Direction
	{
		NORTH,
		SOUTH,
		EAST,
		WEST
	}
}