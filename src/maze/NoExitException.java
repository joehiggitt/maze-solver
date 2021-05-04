package maze;

/**
* Exception that indicates there are no exits in the maze being created.
* @author Joe Higgitt
* @version 1.0, 4th May 2021
* @see maze.InvalidMazeException
*/
public class NoExitException extends InvalidMazeException
{
	/**
	* Constructs the excpetion
	*/
	public NoExitException()
	{
		super("At least one exit is needed in the maze");
	}
}