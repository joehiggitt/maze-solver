import maze.*;
import maze.routing.*;

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
			e.printStackTrace();
		}
		RouteFinder route = RouteFinder.load("/home/csimage/GitRepos/comp16412/comp16412-coursework-2_w81310jh/resources/route1.obj");
		while (!route.isFinished())
		// for (int i = 0; i < 5; i++)
		{
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
			System.out.println(route);
			System.out.println("\n----------------------------------------\n");
		}
		System.out.println(route.getRoute());
	}
}
