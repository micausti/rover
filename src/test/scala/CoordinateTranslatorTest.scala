import cats.effect.IO
import munit.CatsEffectSuite

class CoordinateTranslatorTest extends CatsEffectSuite {

  val translator                      = new CoordinateTranslator
  val initial: IO[RunningListOfMoves] = IO(RunningListOfMoves(North, List.empty))

  def coordinateTranslatorTest(name: String, original: List[Coordinates], expected: List[Move])(implicit loc: munit.Location): Unit =
    test(name) {
      assertIO(translator.coordinatesToMoves(original, initial).map(_.moves), expected)
    }

  coordinateTranslatorTest("Already at destination", List(Coordinates(0, 0)), List.empty)

  coordinateTranslatorTest(
    "Over and Up",
    List(Coordinates(0, 0), Coordinates(0, 1), Coordinates(1, 1)),
    List(Forward, Clockwise, Forward)
  )
  coordinateTranslatorTest(
    "Up and Over",
    List(Coordinates(0, 0), Coordinates(1, 0), Coordinates(1, 1)),
    List(Clockwise, Forward, Anticlockwise, Forward)
  )

  coordinateTranslatorTest(
    "A longer set of moves",
    List(Coordinates(0, 0), Coordinates(1, 0), Coordinates(1, 1), Coordinates(2, 1), Coordinates(3, 1), Coordinates(4, 1)),
    List(Clockwise, Forward, Anticlockwise, Forward, Clockwise, Forward, Forward, Forward)
  )

}
