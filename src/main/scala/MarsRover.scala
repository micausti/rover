import cats.effect.{IO, IOApp}
import cats.implicits.toTraverseOps

object MarsRover extends IOApp.Simple {

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
