import scala.annotation.tailrec

case class RunningListOfMoves(direction: Direction, moves: List[Move])

class CoordinateTranslator {

  private def turnToFaceNorth(currentDirection: Direction): List[Move] =
    currentDirection match {
      case North => List.empty
      case East  => List(Anticlockwise())
      case South => List(Anticlockwise(), Anticlockwise())
      case West  => List(Clockwise())
    }

  private def turnToFaceEast(currentDirection: Direction): List[Move] =
    currentDirection match {
      case North => List(Clockwise())
      case East  => List.empty
      case South => List(Anticlockwise())
      case West  => List(Clockwise(), Clockwise())
    }

  @tailrec
  final def coordinatesToMoves(coordinates: List[Coordinates], moves: RunningListOfMoves): RunningListOfMoves =
    coordinates match {
      case Nil      => moves
      case x :: Nil => moves
      case x :: xs  => coordinatesToMoves(xs, compare(x, xs.head, moves))
    }

  def compare(initial: Coordinates, destination: Coordinates, runningListOfMoves: RunningListOfMoves): RunningListOfMoves = {
    val xMatch = initial.x == destination.x
    val yMatch = initial.y == destination.y
    initial match {
      case arrived if xMatch && yMatch => RunningListOfMoves(North, List.empty)
      case moveUpAndOver if !xMatch && !yMatch =>
        RunningListOfMoves(North, runningListOfMoves.moves ++ turnToFaceNorth(runningListOfMoves.direction) ++ List(Forward(), Clockwise(), Forward()))
      case moveUp if xMatch && !yMatch =>
        RunningListOfMoves(North, runningListOfMoves.moves ++ turnToFaceNorth(runningListOfMoves.direction) ++ List(Forward()))
      case moveOver if !xMatch && yMatch =>
        RunningListOfMoves(East, runningListOfMoves.moves ++ turnToFaceEast(runningListOfMoves.direction) ++ List(Forward()))
      case _ => RunningListOfMoves(North, List.empty)

    }
  }

}
