import CoordinateComparison._
import cats.effect.IO
import scala.annotation.tailrec

case class RunningListOfMoves(direction: Direction, moves: List[Move])
class CoordinateTranslator {

  private def turnToFace(destination: Direction, currentDirection: Direction) = {
    destination match {
      case North => turnToFaceNorth(currentDirection)
      case East => turnToFaceEast(currentDirection)
      case South => turnToFaceSouth(currentDirection)
      case West => turnToFaceWest(currentDirection)
    }
  }
  private def turnToFaceNorth(currentDirection: Direction): List[Move] =
    currentDirection match {
      case North => List.empty
      case East  => List(Anticlockwise)
      case South => List(Anticlockwise, Anticlockwise)
      case West  => List(Clockwise)
    }

  private def turnToFaceEast(currentDirection: Direction): List[Move] =
    currentDirection match {
      case North => List(Clockwise)
      case East  => List.empty
      case South => List(Anticlockwise)
      case West  => List(Clockwise, Clockwise)
    }

  private def turnToFaceSouth(currentDirection: Direction): List[Move] =
    currentDirection match {
      case North => List(Clockwise, Clockwise)
      case East  => List(Clockwise)
      case South => List.empty
      case West  => List(Anticlockwise)
    }

  private def turnToFaceWest(currentDirection: Direction): List[Move] =
    currentDirection match {
      case North => List(Anticlockwise)
      case East  => List(Clockwise, Clockwise)
      case South => List(Clockwise)
      case West  => List.empty
    }

  @tailrec
  final def coordinatesToMoves(coordinates: List[Coordinates], moves: IO[RunningListOfMoves]): IO[RunningListOfMoves] =
    coordinates match {
      case Nil      => moves
      case x :: Nil => moves
      case x :: xs  => coordinatesToMoves(xs, compare(x, xs.head, moves))
    }

  trait Error

  case class ComparisonError(message: String) extends Error

  def compareValues(initial: Coordinates, destination: Coordinates): IO[CoordinateComparison] =
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


  def compare(initial: Coordinates, destination: Coordinates, runningListOfMoves: IO[RunningListOfMoves]): IO[RunningListOfMoves] = {
    for {
      listOfMoves <- runningListOfMoves
      initialMoves = listOfMoves.moves
      currentDirection = listOfMoves.direction
      compare <- compareValues(initial, destination)
      result = compare match {
        case XYMatch => RunningListOfMoves(North, List.empty)
        case IncrementXY =>
          RunningListOfMoves(North, initialMoves ++ turnToFace(North, currentDirection) ++ List(Forward, Clockwise, Forward))
        case IncrementY =>
          RunningListOfMoves(North, initialMoves ++ turnToFace(North, currentDirection) ++ List(Forward))
        case IncrementX =>
          RunningListOfMoves(East, initialMoves ++ turnToFace(East, currentDirection) ++ List(Forward))
        case DecrementXY =>
          RunningListOfMoves(South, initialMoves ++ turnToFace(South, currentDirection) ++ List(Forward, Clockwise, Forward))
        case DecrementY =>
          RunningListOfMoves(South, initialMoves ++ turnToFace(South, currentDirection) ++ List(Forward))
        case DecrementX =>
          RunningListOfMoves(West, initialMoves ++ turnToFace(West, currentDirection) ++ List(Forward))
      }
    } yield result
}
}
