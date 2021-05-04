package maze.routing;

/**
* Exception that indicates there is no possible route out of a maze.
* @author Joe Higgitt
* @version 1.0, 4th May 2021
*/
public class NoRouteFoundException extends Exception
{
	/**
	* Constructs the excpetion
	*/
	public NoRouteFoundException()
	{
		super("No route could be found");
	}
}