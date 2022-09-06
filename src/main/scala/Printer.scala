object Printer {
  def createPrintOutput(moves: List[Move]): String =
    moves.map(m => m.toString.dropRight(2)).mkString(" ")

}
