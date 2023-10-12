# Maze Solver

A Java project that can solve a maze using the A* path finding algorithm. The user can upload a `.txt` representation of the maze (see the files in `resources/mazes/` for examples of this), which are displayed graphically. The user can then step through the solution to the maze. The solution for the maze can be saved for later use.

## Usage

To compile and run the project, use the following commands:

- Linux or MacOS
    ```
    $ ./javac.sh src/MazeApplication.java  
    $ ./java.sh MazeApplication
    ```

- Windows
    ```
    > ./javac.bat src/MazeApplication.java  
    > ./java.bat MazeApplication
    ```

### Running Tests

The following shell files can be used to run the tests:

- Linux or MacOS
    ```
    $ ./run_tests.sh
    ```
- Windows
    ```
    > ./run_tests.bat
    ```
