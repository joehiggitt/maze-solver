import maze.*;
import maze.routing.*;
import javafx.scene.text.Font;

public class MazeDriver
{
	public static void main(String args[])
	{
		Maze maze = null;
		String maze1 = "e.###############\n#....####.....###\n#.##......###.##.\n#.####.##.###....\n#.####.##..#####.\n#..###.###.......\n##.###..#####.###\n##..###...###.###\n..#####.#..##.##x\n.#####..##....##.\n...#.............\n##...######..####";

		try
		{
			maze = Maze.fromTxt(maze1);
		}
		catch (InvalidMazeException e)
		{
			System.out.println("An error occured.");
			e.printStackTrace();
		}
		// RouteFinder route = RouteFinder.load("/home/csimage/GitRepos/comp16412/comp16412-coursework-2_w81310jh/resources/route1.obj");
		// while (!route.isFinished())
		// // for (int i = 0; i < 5; i++)
		// {
		// 	try
		// 	{
		// 		route.step();
		// 	}
		// 	catch (NoRouteFoundException e)
		// 	{
		// 		System.out.println("An error occured.");
		// 		e.printStackTrace();
		// 		break;
		// 	}
		// 	System.out.println(route);
		// 	System.out.println("\n----------------------------------------\n");
		// }
		// System.out.println(route.getRoute());


		for (String font: Font.getFamilies())
		{
			System.out.println(font);
		}
	}
}
