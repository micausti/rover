import munit.FunSuite

class PositionTest extends FunSuite {
  def keepOnGridCheck(name: String, currentValue: Int, gridMax: Int, expected: Int)(implicit loc: munit.Location): Unit =
    test(name) {
      assertEquals(Coordinates.keepOnGrid(currentValue, gridMax), expected)
    }

  keepOnGridCheck("value should be repositioned to the other side of the grid when value is negative", -1, 100, 99)
  keepOnGridCheck("value should be repositioned to the other side of the grid when the value is greater than grid max", 150, 100, 50)
  keepOnGridCheck("value should stay the same when it's in the bounds of the grid", 70, 100, 70)

  val initialPosition: Coordinates  = Coordinates(10, 10)
  val initialPosition2: Coordinates = Coordinates(10, 10)
}
