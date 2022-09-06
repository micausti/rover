import cats.effect.IO

object Printer {
  def print(moves: List[Move]): IO[Unit]= {
    IO(moves.foreach(println))
  }

}
