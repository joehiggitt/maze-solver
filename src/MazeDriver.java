import maze.*;

public class MazeDriver
{
	public static void main(String args[])
	{
		Maze maze = Maze.fromTxt("/home/csimage/GitRepos/comp16412/comp16412-coursework-2_w81310jh/resources/mazes/maze3.txt");
		Tile tile = maze.getEntrance();
		System.out.println(maze.getTileAtLocation(tile));
		System.out.println(maze.getTileAtLocation(maze.getExit()));
		System.out.println(maze.getAdjacentTile(tile, Maze.Direction.SOUTH));
		Maze.Coordinate coord = maze.getTileAtLocation(tile);
		System.out.println(maze.getTileAtLocation(coord));
		System.out.println(maze.getTiles());

		System.out.println(maze.toString());
	}
}
