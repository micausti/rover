import munit.FunSuite

class MoveTest extends FunSuite{

  val grid: Grid = Grid(100, 100)

  val initialPosition1: Position = Position(Coordinates(0, 0), North)
  val initialPosition2: Position = Position(Coordinates(0, 0), South)
  val initialPosition3: Position = Position(Coordinates(0, 0), East)
  val initialPosition4: Position = Position(Coordinates(0, 0), West)

  val moveForward: Forward = Forward(grid)
  val rotateClockwise: Clockwise = Clockwise(grid)
  val rotateAntiClockwise: Anticlockwise = Anticlockwise(grid)

  def moveForwardCheck(name: String, original: Position, expected: Position)(implicit loc: munit.Location): Unit =
    test(name) {
      assertEquals(moveForward.execute(original), expected)
    }

  def rotateClockwiseCheck(name: String, original: Position, expected: Position)(implicit loc: munit.Location): Unit =
    test(name) {
      assertEquals(rotateClockwise.execute(original), expected)
    }

  def rotateAntiClockwiseCheck(name: String, original: Position, expected: Position)(implicit loc: munit.Location): Unit =
    test(name) {
      assertEquals(rotateAntiClockwise.execute(original), expected)
    }

  moveForwardCheck("move forward should increment the position by one y value when the rover is facing North", initialPosition1, Position(Coordinates(0, 1), North))
  moveForwardCheck("move forward should decrement the position by one y value when the rover is facing South", initialPosition2, Position(Coordinates(0, 99), South))
  moveForwardCheck("move forward should increment the position by one x value when the rover is facing East", initialPosition3, Position(Coordinates(1, 0), East))
  moveForwardCheck("move forward should decrement the position by one x value when the rover is facing West", initialPosition4, Position(Coordinates(99, 0), West))

  rotateClockwiseCheck("move clockwise should shift the direction to East when the starting direction is North", initialPosition1, Position(Coordinates(0, 0), East))
  rotateClockwiseCheck("move clockwise should shift the direction to West when the starting direction is South", initialPosition2, Position(Coordinates(0, 0), West))
  rotateClockwiseCheck("move clockwise should shift the direction to South when the starting direction is East", initialPosition3, Position(Coordinates(0, 0), South))
  rotateClockwiseCheck("move clockwise should shift the direction to North when the starting direction is West", initialPosition4, Position(Coordinates(0, 0), North))

  rotateAntiClockwiseCheck("move anticlockwise should shift the direction to West when the starting direction is North", initialPosition1, Position(Coordinates(0, 0), West))
  rotateAntiClockwiseCheck("move anticlockwise should shift the direction to East when the starting direction is South", initialPosition2, Position(Coordinates(0, 0), East))
  rotateAntiClockwiseCheck("move anticlockwise should shift the direction to North when the starting direction is East", initialPosition3, Position(Coordinates(0, 0), North))
  rotateAntiClockwiseCheck("move anticlockwise should shift the direction to South when the starting direction is West", initialPosition4, Position(Coordinates(0, 0), South))


}
