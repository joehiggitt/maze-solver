package maze;

public class RaggedMazeException extends InvalidMazeException
{
	public RaggedMazeException()
	{
		super("Maze is not of rectangular dimensions.");
	}
}