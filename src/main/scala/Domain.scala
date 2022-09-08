sealed trait Direction
object North extends Direction
object East  extends Direction
object South extends Direction
object West  extends Direction

sealed trait Move

case object Forward       extends Move
case object Clockwise     extends Move
case object Anticlockwise extends Move

case class Grid(len: Int, height: Int)
case class Coordinates(x: Int, y: Int)

object Coordinates {
  def keepOnGrid(currentValue: Int, gridMax: Int): Int =
    currentValue match {
      case valid if 0 <= currentValue && currentValue <= gridMax => valid
      case l if currentValue < 0                                 => gridMax + currentValue
      case h if currentValue > gridMax                           => currentValue - gridMax
    }
}

sealed trait CoordinateComparison

object CoordinateComparison {
  case object XYMatch extends CoordinateComparison

  case object IncrementXY extends CoordinateComparison

  case object IncrementX extends CoordinateComparison

  case object IncrementY extends CoordinateComparison

  case object DecrementXY extends CoordinateComparison

  case object DecrementX extends CoordinateComparison

  case object DecrementY extends CoordinateComparison
}
