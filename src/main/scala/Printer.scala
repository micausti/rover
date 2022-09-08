object Printer {
  def createPrintOutput(listOfMoves: List[Move]): String =
    if (listOfMoves.isEmpty) {
      "Already at destination"
    } else listOfMoves.map(_.toString).mkString(" ")

}
