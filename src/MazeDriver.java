import maze.*;
import maze.routing.*;

public class MazeDriver
{
	public static void main(String args[])
	{
		Maze maze = null;
		String maze1 = "e.###############\n#....####.....###\n#.##......###.##.\n#.####.##.###....\n#.####.##..#####.\n#..###.###.......\n##.###..#####.###\n##..###...###.###\n..#####.#..##.##x\n.#####..##....##.\n...#.............\n##...######..####";

		try
		{
			maze = Maze.fromTxt("/home/csimage/GitRepos/comp16412/comp16412-coursework-2_w81310jh/resources/mazes/maze3.txt");
		}
		catch (InvalidMazeException e)
		{
			e.printStackTrace();
		}

		// try
		// {
		// 	Tile newEntrance = Tile.fromChar('e');
		// 	try
		// 	{
		// 		maze.setEntrance(newEntrance);
		// 	}
		// 	catch (InvalidMazeException e)
		// 	{
		// 		e.printStackTrace();
		// 	}
		// }
		// catch (InvalidMazeException e)
		// {
		// 	e.printStackTrace();
		// }

		

		RouteFinder route = new RouteFinder(maze);
		while (!route.isFinished())
		// for (int i = 0; i < 5; i++)
		{
			System.out.println("\n----------------------------------------------\n");
			System.out.println(route + "\n");
			try
			{
				route.step();
			}
			catch (NoRouteFoundException e)
			{
				System.out.println("An error occured.");
				e.printStackTrace();
				break;
			}
		}
		System.out.println(route.getRoute());
	}
}
