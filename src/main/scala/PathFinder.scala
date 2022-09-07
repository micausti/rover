object PathFinder {
  def shortestPath(): List[Coordinates] =
  //Here I just include an arbitrary list of cordinates to get the program to run
  //A real implementation of finding the shortest path might be achieved using Dijkstra or A* algorithm
    List(Coordinates(0, 0), Coordinates(0, 1), Coordinates(1, 1), Coordinates(1, 2), Coordinates(2, 2), Coordinates(2, 3), Coordinates(3, 3))

}
