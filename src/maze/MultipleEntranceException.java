package maze;

/**
* Exception that indicates there are too many entrances in the maze being created.
* @author Joe Higgitt
* @version 1.0, 4th May 2021
* @see maze.InvalidMazeException
*/
public class MultipleEntranceException extends InvalidMazeException
{
	/**
	* Constructs the excpetion
	*/
	public MultipleEntranceException()
	{
		super("Only one entrance can be added to a maze");
	}
}