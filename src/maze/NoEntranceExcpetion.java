package maze;

public class NoEntranceExcpetion extends InvalidMazeException
{
	public NoEntranceExcpetion()
	{
		super("At least one entrance is needed in the maze");
	}
}