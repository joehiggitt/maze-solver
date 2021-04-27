package maze.routing;

public class NoRouteFoundException extends Exception
{
	public NoRouteFoundException()
	{
		super("No route could be found");
	}
}