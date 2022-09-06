import scala.math.hypot

case class Position(coordinates: Coordinates, direction: Direction)

object Position {
  def keepOnGrid(currentValue: Int, gridMax: Int): Int =
    currentValue match {
      case valid if 0 <= currentValue && currentValue <= gridMax => valid
      case l if currentValue < 0                                 => gridMax + currentValue
      case h if currentValue > gridMax                           => currentValue - gridMax
    }

  def distanceToDestination(starting: Position, ending: Coordinates): Int = {
    val side1 = ending.x - starting.coordinates.x
    val side2 = ending.y - starting.coordinates.y

    hypot(side1.toDouble, side2.toDouble).toInt
  }
}
