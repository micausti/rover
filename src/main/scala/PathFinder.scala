object PathFinder {
  def shortestPath(grid: Grid, start: Coordinates, destination: Coordinates): List[Coordinates] = {
    //Here I just include an arbitrary list of coordinates to get the program to run
    //A real implementation of finding the shortest path might be achieved using Dijkstra or A* algorithm

    //Assumptions:
    //1. I assume that any output of a list of coordinates would include Coordinates(0,0) as the starting point
    //2. The coordinate list represents only one move at a time (e.g. one 'forward' move)
    //3. I also created a 'keepOnGrid' function in the Coordinate object that would be used to adjust any coordinates that had gone off the grid

    List(Coordinates(0, 0), Coordinates(0, 1), Coordinates(1, 1), Coordinates(1, 2), Coordinates(2, 2), Coordinates(2, 3), Coordinates(3, 3))
  }

}
