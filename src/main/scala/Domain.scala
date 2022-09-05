
  sealed trait Direction
  object North extends Direction
  object East extends Direction
  object South extends Direction
  object West extends Direction

  case class Grid(len: Int, height: Int)
  case class Coordinates(x: Int, y: Int)


