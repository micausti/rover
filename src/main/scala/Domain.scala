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

sealed trait CoordinateComparison {
  val destinationDirection: Direction
  val movesAfterDirectionChange: List[Move]
}

object CoordinateComparison {
  case object XYMatch extends CoordinateComparison {
    override val destinationDirection: Direction       = North
    override val movesAfterDirectionChange: List[Move] = List.empty
  }
  case object IncrementXY extends CoordinateComparison {
    override val destinationDirection: Direction       = North
    override val movesAfterDirectionChange: List[Move] = List(Forward, Clockwise, Forward)
  }
  case object IncrementX extends CoordinateComparison {
    override val destinationDirection: Direction       = East
    override val movesAfterDirectionChange: List[Move] = List(Forward)
  }
  case object IncrementY extends CoordinateComparison {
    override val destinationDirection: Direction       = North
    override val movesAfterDirectionChange: List[Move] = List(Forward)
  }
  case object DecrementXY extends CoordinateComparison {
    override val destinationDirection: Direction       = South
    override val movesAfterDirectionChange: List[Move] = List(Forward, Clockwise, Forward)
  }
  case object DecrementX extends CoordinateComparison {
    override val destinationDirection: Direction       = West
    override val movesAfterDirectionChange: List[Move] = List(Forward)
  }
  case object DecrementY extends CoordinateComparison {
    override val destinationDirection: Direction       = South
    override val movesAfterDirectionChange: List[Move] = List(Forward)
  }
}
