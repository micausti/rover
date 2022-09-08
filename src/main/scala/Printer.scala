object Printer {
  def createPrintOutput(listOfMoves: RunningListOfMoves): String =
    if (listOfMoves.moves.isEmpty) {
      "Already at destination"
    } else listOfMoves.moves.map(_.toString).mkString(" ")

}
