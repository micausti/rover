import MarsRover.grid

sealed trait Move {
  def execute(currentPosition: Position): Position
}

object Move {
  def gridCheck(cd: Coordinates, grid:Grid): Coordinates = {
    val x = Position.keepOnGrid(cd.x, grid.len)
    val y = Position.keepOnGrid(cd.y, grid.height)
    Coordinates(x, y)
  }
}

case class Forward() extends Move {
  override def execute(currentPosition: Position): Position = {
    val newCoordinates = currentPosition.direction match {
      case North => Coordinates(currentPosition.coordinates.x, currentPosition.coordinates.y + 1)
      case East => Coordinates(currentPosition.coordinates.x + 1, currentPosition.coordinates.y)
      case South => Coordinates(currentPosition.coordinates.x, currentPosition.coordinates.y - 1)
      case West => Coordinates(currentPosition.coordinates.x - 1, currentPosition.coordinates.y)
    }
    val gridCheckedCoordinates = Move.gridCheck(newCoordinates, grid)
    Position(Coordinates(gridCheckedCoordinates.x, gridCheckedCoordinates.y), currentPosition.direction)
  }


}

case class Clockwise() extends Move {
  override def execute(currentPosition: Position): Position = {
    val newDirection = currentPosition.direction match {
      case North => East
      case East => South
      case South => West
      case West => North
    }
    Position(currentPosition.coordinates, newDirection)
  }
}

case class Anticlockwise() extends Move {
  override def execute(currentPosition: Position): Position = {
    val newDirection = currentPosition.direction match {
      case North => West
      case East => North
      case South => East
      case West => South
    }
    Position(currentPosition.coordinates, newDirection)
  }
}
