import munit.FunSuite

class PrinterTest extends FunSuite {
  val moves: List[Move]      = List(Forward, Clockwise, Forward, Anticlockwise, Forward, Forward, Forward, Forward)
  val expectedResult: String = "Forward Clockwise Forward Anticlockwise Forward Forward Forward Forward"

  def printerCheck(name: String, instructions: String, expected: String)(implicit loc: munit.Location): Unit =
    test(name) {
      assertEquals(instructions, expected)
    }

  printerCheck(
    "createPrintOutput should take a list of moves and convert it to a string",
    Printer.createPrintOutput(moves),
    expectedResult
  )
  printerCheck(
    "tells you you've arrives already if there are no moves",
    Printer.createPrintOutput(List.empty),
    "Already at destination"
  )

}
