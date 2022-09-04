object MarsRover extends App {

  sealed trait Direction
  object North extends Direction
  object East  extends Direction
  object South extends Direction
  object West  extends Direction

  case class Grid(len: Int, height: Int)
  case class Coordinates(x: Int, y: Int)
  case class Position(coordinates: Coordinates, direction: Direction)

  object Position {
    def keepOnGrid(currentValue: Int, gridMax: Int): Int =
      currentValue match {
        case valid if 0 <= currentValue && currentValue <= gridMax => valid
        case l if currentValue < 0                                   => gridMax + currentValue
        case h if currentValue > gridMax                             => currentValue - gridMax
      }
  }

  sealed trait Move {
    def execute(currentPosition: Position): Position
  }

  case class Forward(grid: Grid) extends Move {
    override def execute(currentPosition: Position): Position = {
      val newCoordinates = currentPosition.direction match {
        case North => Coordinates(currentPosition.coordinates.x, currentPosition.coordinates.y + 1)
        case East  => Coordinates(currentPosition.coordinates.x + 1, currentPosition.coordinates.y)
        case South => Coordinates(currentPosition.coordinates.x, currentPosition.coordinates.y - 1)
        case West  => Coordinates(currentPosition.coordinates.x - 1, currentPosition.coordinates.y)
      }
      val gridCheckedCoordinates = gridCheck(newCoordinates)
      Position(Coordinates(gridCheckedCoordinates.x, gridCheckedCoordinates.y), currentPosition.direction)
    }

    def gridCheck(cd: Coordinates): Coordinates = {
      val x = Position.keepOnGrid(cd.x, grid.len)
      val y = Position.keepOnGrid(cd.y, grid.height)
      Coordinates(x, y)
    }
  }
  case class Clockwise(grid: Grid) extends Move {
    override def execute(currentPosition: Position): Position = {
      val newDirection = currentPosition.direction match {
        case North => East
        case East  => South
        case South => West
        case West  => North
      }
      Position(currentPosition.coordinates, newDirection)
    }
  }
  case class Anticlockwise(grid: Grid) extends Move {
    override def execute(currentPosition: Position): Position = {
      val newDirection = currentPosition.direction match {
        case North => West
        case East  => North
        case South => East
        case West  => South
      }
      Position(currentPosition.coordinates, newDirection)
    }
  }
}
