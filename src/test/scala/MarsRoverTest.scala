import MarsRover._
import munit.FunSuite

class MarsRoverTest extends FunSuite {

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

  moveForwardCheck("forward-1", initialPosition1, Position(Coordinates(0, 1), North))
  moveForwardCheck("forward-2", initialPosition2, Position(Coordinates(0, 99), South))
  moveForwardCheck("forward-3", initialPosition3, Position(Coordinates(1, 0), East))
  moveForwardCheck("forward-4", initialPosition4, Position(Coordinates(99, 0), West))

  rotateClockwiseCheck("clockwise-1", initialPosition1, Position(Coordinates(0, 0), East))
  rotateClockwiseCheck("clockwise-2", initialPosition2, Position(Coordinates(0, 0), West))
  rotateClockwiseCheck("clockwise-3", initialPosition3, Position(Coordinates(0, 0), South))
  rotateClockwiseCheck("clockwise-4", initialPosition4, Position(Coordinates(0, 0), North))

  rotateAntiClockwiseCheck("anticlockwise-1", initialPosition1, Position(Coordinates(0, 0), West))
  rotateAntiClockwiseCheck("anticlockwise-2", initialPosition2, Position(Coordinates(0, 0), East))
  rotateAntiClockwiseCheck("anticlockwise-3", initialPosition3, Position(Coordinates(0, 0), North))
  rotateAntiClockwiseCheck("anticlockwise-4", initialPosition4, Position(Coordinates(0, 0), South))

  def keepOnGridCheck(name: String, currentValue: Int, gridMax: Int, expected: Int)(implicit loc: munit.Location): Unit =
    test(name) {
      assertEquals(Position.keepOnGrid(currentValue, gridMax), expected)
    }

  keepOnGridCheck("1", -1, 100, 99)
  keepOnGridCheck("2", 150, 100, 50)
  keepOnGridCheck("3", 70, 100, 70)
}

