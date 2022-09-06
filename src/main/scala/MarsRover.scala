import cats.effect.{IO, IOApp, Resource}

object MarsRover extends IOApp.Simple {

  val grid = Grid(100, 100)
  val destination: Coordinates = Coordinates(50, 50)
  def run: IO[Unit] =
    for {
      _ <- IO.println("Hello World")
      moves = PathFinder.path(grid, destination)
      _ <- Printer.print(moves)
    } yield ()

}






