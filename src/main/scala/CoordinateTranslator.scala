import CoordinateComparison._
import enumeratum._

import scala.annotation.tailrec

case class RunningListOfMoves(direction: Direction, moves: List[Move])
class CoordinateTranslator {
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
  final def coordinatesToMoves(coordinates: List[Coordinates], moves: RunningListOfMoves): RunningListOfMoves =
    coordinates match {
      case Nil      => moves
      case x :: Nil => moves
      case x :: xs  => coordinatesToMoves(xs, compare(x, xs.head, moves))
    }

  trait Error

  case class ComparisonError(message: String) extends Error

  def compareValues(initial: Coordinates, destination: Coordinates): CoordinateComparison =
    initial match {
      case xAndYMatch if initial.x == destination.x && initial.y == destination.y             => XYMatch
      case incrementXAndY if initial.x + 1 == destination.x && initial.y + 1 == destination.y => IncrementXY
      case incrementX if initial.x + 1 == destination.x && initial.y == destination.y         => IncrementX
      case incrementY if initial.x == destination.x && initial.y + 1 == destination.y         => IncrementY
      case decrementXAndY if initial.x - 1 == destination.x && initial.y - 1 == destination.y => DecrementXY
      case decrementX if initial.x - 1 == destination.x && initial.y == destination.y         => DecrementX
      case decrementY if initial.x == destination.x && initial.y - 1 == destination.y         => DecrementY
    }

  def compare(initial: Coordinates, destination: Coordinates, runningListOfMoves: RunningListOfMoves): RunningListOfMoves =
    compareValues(initial, destination) match {
      case XYMatch => RunningListOfMoves(North, List.empty)
      case IncrementXY =>
        RunningListOfMoves(North, runningListOfMoves.moves ++ turnToFaceNorth(runningListOfMoves.direction) ++ List(Forward, Clockwise, Forward))
      case IncrementY =>
        RunningListOfMoves(North, runningListOfMoves.moves ++ turnToFaceNorth(runningListOfMoves.direction) ++ List(Forward))
      case IncrementX =>
        RunningListOfMoves(East, runningListOfMoves.moves ++ turnToFaceEast(runningListOfMoves.direction) ++ List(Forward))
      case DecrementXY =>
        RunningListOfMoves(South, runningListOfMoves.moves ++ turnToFaceSouth(runningListOfMoves.direction) ++ List(Forward, Clockwise, Forward))
      case DecrementY =>
        RunningListOfMoves(South, runningListOfMoves.moves ++ turnToFaceSouth(runningListOfMoves.direction) ++ List(Forward))
      case DecrementX =>
        RunningListOfMoves(West, runningListOfMoves.moves ++ turnToFaceWest(runningListOfMoves.direction) ++ List(Forward))
    }
}
