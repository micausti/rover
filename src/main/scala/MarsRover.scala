import cats.effect.{IO, IOApp}

object MarsRover extends IOApp.Simple {

  //start with a grid, starting point, and destination
  //calculate the shortest path from the starting point to the destination and produce a list of coordinates to represent the path
  //in the simplest case, the shortest path is the direct path between the start and end point
  //for more complex scenarios, there may be obstacles in the way; something like dijkstra's or a* algorithm could be used to determine the shortest path
  //once the shortest path is known, that needs to be translated into a list of moves which tell the rover how to get from each position to the next
  //the allowed moves are forward, clockwise, and anticlockwise
  //the list of moves is printed to the console

  val grid: Grid               = Grid(100, 100)
  val start: Coordinates       = Coordinates(0, 0)
  val destination: Coordinates = Coordinates(50, 50)
  def run: IO[Unit] =
    for {
      _           <- IO.println("Calculating moves to destination")
      translator  = new CoordinateTranslator
      coordinates = PathFinder.shortestPath(grid, start, destination)
      moves       = translator.coordinatesToMoves(coordinates, RunningListOfMoves(North, List.empty))
      output      = Printer.createPrintOutput(moves)
      _           = println(output)
    } yield ()

}
