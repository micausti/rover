import CoordinateComparison._
import cats.effect.IO
import scala.annotation.tailrec

case class RunningListOfMoves(direction: Direction, moves: List[Move])
class CoordinateTranslator {

  @tailrec
  final def coordinatesToMoves(coordinates: List[Coordinates], moves: IO[RunningListOfMoves]): IO[RunningListOfMoves] =
    coordinates match {
      case Nil      => moves
      case x :: Nil => moves
      case x :: xs  => coordinatesToMoves(xs, compare(x, xs.head, moves))
    }

  private def turn(currentDirection: Direction, destination: Direction): IO[List[Move]] =
    (destination, currentDirection) match {
      case same if (destination == currentDirection)                     => IO(List.empty)
      case (North, East) | (East, South) | (South, West) | (West, North) => IO(List(Anticlockwise))
      case (North, South)                                                => IO(List(Anticlockwise, Anticlockwise))
      case (North, West) | (East, North) | (South, East) | (West, South) => IO(List(Clockwise))
      case (East, West)                                                  => IO(List(Clockwise, Anticlockwise))
      case (South, North) | (West, East)                                 => IO(List(Clockwise, Clockwise))
      case _                                                             => IO.raiseError(new Throwable("Invalid move state"))
    }

  private def compareValues(initial: Coordinates, destination: Coordinates): IO[CoordinateComparison] =
    initial match {
      case xAndYMatch if initial.x == destination.x && initial.y == destination.y             => IO(XYMatch)
      case incrementXAndY if initial.x + 1 == destination.x && initial.y + 1 == destination.y => IO(IncrementXY)
      case incrementX if initial.x + 1 == destination.x && initial.y == destination.y         => IO(IncrementX)
      case incrementY if initial.x == destination.x && initial.y + 1 == destination.y         => IO(IncrementY)
      case decrementXAndY if initial.x - 1 == destination.x && initial.y - 1 == destination.y => IO(DecrementXY)
      case decrementX if initial.x - 1 == destination.x && initial.y == destination.y         => IO(DecrementX)
      case decrementY if initial.x == destination.x && initial.y - 1 == destination.y         => IO(DecrementY)
      case _                                                                                  => IO.raiseError(new Throwable("Coordinates should not be more than one move apart"))
    }

  private def compare(initial: Coordinates, destination: Coordinates, runningListOfMoves: IO[RunningListOfMoves]): IO[RunningListOfMoves] =
    for {
      listOfMoves          <- runningListOfMoves
      initialMoves         = listOfMoves.moves
      currentDirection     = listOfMoves.direction
      compare              <- compareValues(initial, destination)
      destinationDirection = compare.destinationDirection
      turnMoves            <- turn(currentDirection, destinationDirection)
    } yield RunningListOfMoves(destinationDirection, initialMoves ++ turnMoves ++ compare.movesAfterDirectionChange)
}
