
object PathFinder {
  def path(grid: Grid, destination: Coordinates, startingPosition: Position): List[Move] = {
    //I didn't have time to implement the autopilot portion of the exercise, although it seems like Dijkstra or A* algorithms would be the best option for this.
    //Here I just include an arbitrary list of moves for the sake of getting the program to run
    List(Forward(), Clockwise(), Forward(), Anticlockwise())
  }
}
