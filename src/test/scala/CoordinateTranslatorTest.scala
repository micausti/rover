import munit.FunSuite

class CoordinateTranslatorTest extends FunSuite{

  val translator = new CoordinateTranslator
  val coordinatesToDestination = List(Coordinates(0, 0))
  val coordinatesToDestination2 = List(Coordinates(0, 0), Coordinates(0, 1), Coordinates(1, 1), Coordinates(1, 2), Coordinates(2, 2), Coordinates(2, 3), Coordinates(3, 3))
  val initialRunningListOfMoves = RunningListOfMoves(North, Some(List.empty))
  val expectedRunningListOfMoves = RunningListOfMoves(North, Some(List(Forward(), Clockwise(), Forward(), Anticlockwise(), Forward(), Clockwise())))

  assertEquals(translator.coordinatesToMoves(coordinatesToDestination, initialRunningListOfMoves), expectedRunningListOfMoves)

}
