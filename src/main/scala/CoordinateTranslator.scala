import scala.annotation.tailrec

case class RunningListOfMoves(direction: Direction, moves: Option[List[Move]])

class CoordinateTranslator {

  def turnToFaceNorth(currentDirection: Direction): Option[List[Move]] =
    currentDirection match {
      case North => Some(List.empty)
      case East  => Some(List(Anticlockwise()))
      case South => Some(List(Anticlockwise(), Anticlockwise()))
      case West  => Some(List(Clockwise()))
    }

  def turnToFaceEast(currentDirection: Direction): Option[List[Move]] =
    currentDirection match {
      case North => Some(List(Clockwise()))
      case East  => Some(List.empty)
      case South => Some(List(Anticlockwise()))
      case West  => Some(List(Clockwise(), Clockwise()))
    }

  @tailrec
  final def coordinatesToMoves(coordinates: List[Coordinates], moves: RunningListOfMoves): RunningListOfMoves =
    coordinates match {
      case Nil      => moves
      case x :: Nil => moves
      case x :: xs  => coordinatesToMoves(xs, compare(moves.direction, x, xs.head))
    }

  def compare(currentDirection: Direction, initial: Coordinates, destination: Coordinates): RunningListOfMoves = {
    val xMatch = initial.x == destination.x
    val yMatch = initial.y == destination.y
    initial match {
      case arrived if xMatch && yMatch => RunningListOfMoves(North, Some(List.empty))
      case moveUpAndOver if !xMatch && !yMatch =>
        RunningListOfMoves(North, combineLists(turnToFaceNorth(currentDirection), Some(List(Forward(), Clockwise(), Forward()))))
      case moveUp if xMatch && !yMatch =>
        RunningListOfMoves(North, combineLists(turnToFaceNorth(currentDirection), Some(List(Forward()))))
      case moveOver if !xMatch && yMatch =>
        RunningListOfMoves(East, combineLists(turnToFaceEast(currentDirection), Some(List(Forward()))))
      case _ => RunningListOfMoves(North, Some(List.empty))

    }
  }

  def combineLists(first: Option[List[Move]], second: Option[List[Move]]): Option[List[Move]] =
    Option((first ++ second).flatten.toList).filter(_.nonEmpty)
}
