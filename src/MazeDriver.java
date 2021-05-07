import maze.*;
import maze.routing.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class MazeDriver
{
	public static void main(String[] args) throws IOException, URISyntaxException
	{
		// USED FOR TESTING PURPOSES, DOES NOT RUN ANYTHING USEFUL


		// String filePath = "EntranceSquare.png";

		// File file = new File(MazeDriver.class.getResource(filePath).toURI());
		// System.out.println("File Found: " + file.exists());


		Maze maze = null;
		// String maze1 = "e.###############\n#....####.....###\n#.##......###.##.\n#.####.##.###....\n#.####.##..#####.\n#..###.###.......\n##.###..#####.###\n##..###...###.###\n..#####.#..##.##x\n.#####..##....##.\n...#.............\n##...######..####";

		try
		{
			maze = Maze.fromTxt("/home/csimage/Downloads/Test.txt");
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
				System.out.println("No route found.");
				e.printStackTrace();
				break;
			}
		}
		System.out.println(route.getRoute());
	}
}
