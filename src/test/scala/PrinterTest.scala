import cats.effect.IO
import munit.CatsEffectSuite

class PrinterTest extends CatsEffectSuite {

  val grid                   = Grid(100, 100)
  val instructionsToDestination: IO[Unit] = Printer.print(List(Forward(), Clockwise(), Forward(), Anticlockwise(), Forward(), Forward(), Forward(), Forward()))
  val expectedResult: Unit = println("Forward", "Clockwise", "Forward", "Anticlockwise")


  def printerCheck(name: String, instructions: IO[Unit], expected: Unit)(implicit loc: munit.Location): Unit =
    test(name) {
      assertIO(instructions, expectedResult)
    }

  printerCheck("someName", instructionsToDestination, expectedResult)

}
