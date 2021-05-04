package maze;

/**
* Exception that indicates there are no entrances in the maze being created.
* @author Joe Higgitt
* @version 1.0, 4th May 2021
* @see maze.InvalidMazeException
*/
public class NoEntranceException extends InvalidMazeException
{
	/**
	* Constructs the excpetion
	*/
	public NoEntranceException()
	{
		super("At least one entrance is needed in the maze");
	}
}