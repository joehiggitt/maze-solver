package maze;

/**
* Exception that indicates there are too many exits in the maze being created.
* @author Joe Higgitt
* @version 1.0, 4th May 2021
* @see maze.InvalidMazeException
*/
public class MultipleExitException extends InvalidMazeException
{
	/**
	* Constructs the excpetion
	*/
	public MultipleExitException()
	{
		super("Only one exit can be added to a maze");
	}
}