package maze;

/**
* Exception that indicates that the maze being created is not of rectangular dimensions.
* @author Joe Higgitt
* @version 1.0, 4th May 2021
* @see maze.InvalidMazeException
*/
public class RaggedMazeException extends InvalidMazeException
{
	/**
	* Constructs the excpetion
	*/
	public RaggedMazeException()
	{
		super("Maze is not of rectangular dimensions.");
	}
}