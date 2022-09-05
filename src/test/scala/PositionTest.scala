import munit.FunSuite

class PositionTest extends FunSuite {


  def keepOnGridCheck(name: String, currentValue: Int, gridMax: Int, expected: Int)(implicit loc: munit.Location): Unit =
    test(name) {
      assertEquals(Position.keepOnGrid(currentValue, gridMax), expected)
    }

  def distanceToDestinationCheck(name: String, starting: Position, ending: Position, expected: Int)(implicit loc: munit.Location): Unit =
    test(name) {
      assertEquals(Position.distanceToDestination(starting, ending), expected)
    }

  keepOnGridCheck("1", -1, 100, 99)
  keepOnGridCheck("2", 150, 100, 50)
  keepOnGridCheck("3", 70, 100, 70)

  val initialPosition: Position = Position(Coordinates(10, 10), North)
  val destination: Position = Position(Coordinates(20, 20), North)
  distanceToDestinationCheck("calculate direct distance to destination", initialPosition, destination, 14)
}
