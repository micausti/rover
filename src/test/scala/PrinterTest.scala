import munit.FunSuite

class PrinterTest extends FunSuite {
  val moves = List(Forward(), Clockwise(), Forward(), Anticlockwise(), Forward(), Forward(), Forward(), Forward())
  val instructionsToDestination: String =
    Printer.createPrintOutput(RunningListOfMoves(North, moves))
  val expectedResult: String = "Forward Clockwise Forward Anticlockwise Forward Forward Forward Forward"

  def printerCheck(name: String, instructions: String, expected: String)(implicit loc: munit.Location): Unit =
    test(name) {
      assertEquals(instructions, expected)
    }

  printerCheck("createPrintOutput should take a list of moves and convert it to a string", instructionsToDestination, expectedResult)

}
