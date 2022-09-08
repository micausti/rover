object PathFinder {
  def shortestPath(grid: Grid, start: Coordinates, destination: Coordinates): List[List[Coordinates]] =
    /*Here I just include an arbitrary list of coordinates to get the program to run
    A real implementation of finding the shortest path might be achieved using Dijkstra or A* algorithm

    Assumptions:
    1. I assume that any output of a list of coordinates would include the starting point (e.g. (0,0))
    2. The coordinate list represents only one move at a time (e.g. from (0,0) to (0,1))
    3. In order to handle the case where coordinates go off the grid, they can be adjusted using the ''keepOnGrid' function in the Coordinate object
    4. Because of the potential for going off the grid, this function produces a List[List[Coordinates]] so that the lists can be translated into instructions separately and combined
     */
    List(List(Coordinates(0, 0), Coordinates(0, 1), Coordinates(1, 1), Coordinates(1, 2), Coordinates(2, 2), Coordinates(2, 3), Coordinates(3, 3)))

}
