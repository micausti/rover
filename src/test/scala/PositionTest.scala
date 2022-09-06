import munit.FunSuite

class PositionTest extends FunSuite {


  def keepOnGridCheck(name: String, currentValue: Int, gridMax: Int, expected: Int)(implicit loc: munit.Location): Unit =
    test(name) {
      assertEquals(Position.keepOnGrid(currentValue, gridMax), expected)
    }

  def distanceToDestinationCheck(name: String, starting: Position, ending: Coordinates, expected: Int)(implicit loc: munit.Location): Unit =
    test(name) {
      assertEquals(Position.distanceToDestination(starting, ending), expected)
    }

  keepOnGridCheck("value should be repositioned to the other side of the grid when value is negative", -1, 100, 99)
  keepOnGridCheck("value should be repositioned to the other side of the grid when the value is greater than grid max", 150, 100, 50)
  keepOnGridCheck("value should stay the same when it's in the bounds of the grid", 70, 100, 70)


  val initialPosition: Position = Position(Coordinates(10, 10), North)
  val initialPosition2: Position = Position(Coordinates(10, 10), North)

  val destination: Coordinates = Coordinates(20, 20)
  val destination2: Coordinates = Coordinates(10, 20)
  distanceToDestinationCheck("distance to destination check should calculate direct distance to destination", initialPosition, destination, 14)
  distanceToDestinationCheck("distance to destination check should calculate the distance when one of the axes doesn't change", initialPosition2, destination2, 10)
}
