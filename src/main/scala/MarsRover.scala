import cats.effect.{IO, IOApp, Resource}

object MarsRover extends IOApp.Simple {

  val grid = Grid(100, 100)
  val destination: Coordinates = Coordinates(50, 50)
  val startingPosition = Position(Coordinates(0, 0), North)
  def run: IO[Unit] =
    for {
      _ <- IO.println("Calculating moves to destination")
      moves = PathFinder.path(grid, destination, startingPosition)
      output = Printer.createPrintOutput(moves)
      _ = println(output)
    } yield ()

}






