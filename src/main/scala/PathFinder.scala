import javax.print.attribute.standard.Destination

object PathFinder {
  def path(grid: Grid, destination: Coordinates, startingPosition: Position): List[Move] = {
    //determines list of moves to the shortest path
    val shortestDistance = Position.distanceToDestination(startingPosition, destination)
    List(Forward(), Clockwise(), Forward(), Anticlockwise())

  }
}
