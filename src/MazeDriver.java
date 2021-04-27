import maze.*;

public class MazeDriver
{
	public static void main(String args[])
	{
		Maze maze = null;
		try
		{
			maze = Maze.fromTxt("/home/csimage/GitRepos/comp16412/comp16412-coursework-2_w81310jh/resources/mazes/maze1.txt");
		}
		catch (InvalidMazeException e)
		{
			System.out.println("An error occured.");
		}
		// Tile tile = maze.getEntrance();
		// Maze.Coordinate coord = maze.getTileAtLocation(tile);
		// System.out.println(coord);
		// System.out.println(maze.getTileAtLocation(maze.getExit()));
		// System.out.println(maze.getTileAtLocation(maze.getAdjacentTile(tile, Maze.Direction.NORTH)));
		// System.out.println(maze.getAdjacentTile(tile, Maze.Direction.NORTH));
		// System.out.println(maze.getTileAtLocation(coord));
		// System.out.println(maze.getTiles());

		System.out.println(maze.toString());
	}
}
