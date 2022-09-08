import cats.effect.{IO, IOApp}
import cats.implicits.toTraverseOps

object MarsRover extends IOApp.Simple {

  /*start with a grid, starting point, and destination
  calculate the shortest path from the starting point to the destination and produce a list of coordinates to represent the path
  once the shortest path is known, that needs to be translated into a list of moves which tell the rover how to get from each position to the next
  the allowed moves are forward, clockwise, and anticlockwise
  the list of moves is printed to the console*/

  val grid: Grid               = Grid(100, 100)
  val start: Coordinates       = Coordinates(0, 0)
  val destination: Coordinates = Coordinates(3, 3)
  def run: IO[Unit] =
    for {
      _              <- IO.println(s"Calculating moves from starting point $start to destination $destination")
      translator     = new CoordinateTranslator
      allCoordinates = PathFinder.shortestPath(grid, start, destination)
      allMoves       <- allCoordinates.traverse(ls => translator.coordinatesToMoves(ls, IO(RunningListOfMoves(North, List.empty))).map(_.moves))
      moves          = Printer.createPrintOutput(allMoves.flatten)
      _              = println(moves)
    } yield ()

}
