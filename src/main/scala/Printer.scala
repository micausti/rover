object Printer {
  def createPrintOutput(listOfMoves: RunningListOfMoves): String = {
    listOfMoves.moves.fold("Already at destination")(_.map(move => move.toString.dropRight(2)).mkString(" "))
  }

}
