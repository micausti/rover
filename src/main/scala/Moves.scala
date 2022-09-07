import cats.free.Free
import cats.free.Free.liftF
import cats.arrow.FunctionK
import cats.{Id, ~>}
import scala.collection.mutable

sealed trait MovesA[A]
case class Forward[T](start: Coordinates, end: Coordinates) extends MovesA[T]
case class Clockwise[T](start: Direction, end: Direction) extends MovesA[T]
case class Anticlockwise[T](start: Direction, end: Direction) extends MovesA[T]

type Moves[A] = Free[MovesA, A]

def forward[T](start: Coordinates, end: Coordinates): Moves[Unit] = {
  liftF[MovesA, Unit](Forward(start, end))
}

def clockwise[T](start: Direction, end: Direction): Moves[Unit] = {
  liftF[MovesA, Unit](Clockwise(start, end))
}

def anticlockwise[T](start: Direction, end: Direction): Moves[Unit] = {
  liftF[MovesA, Unit](Anticlockwise(start, end))
}

def program: Moves[List[Position]] =
  for {
    _ <- forward(Coordinates(0, 0), Coordinates(0,1))
    _ <- clockwise(North, East)
    _ <- forward(Coordinates(0, 1), Coordinates(1,1))
    _ <- anticlockwise(East, North)
  } yield ()



// the program will crash if a type is incorrectly specified.
def impureCompiler: MovesA ~> List[MovesA[Position]]  =
  new (MovesA ~> List[MovesA[Position]]) {

    // a very simple (and imprecise) key-value
    val instructions = List.empty

    def apply[A](fa: MovesA[A]): List[MovesA[Position]] =
      fa match {
        case Forward(start, end) =>
          println(s"forward to $end")
          instructions ++ List(end)
        case Clockwise(start, end) =>
          println(s"clockwise to $end")
        case Anticlockwise(start, end) =>
          println(s"anticlockwise to ($end)")
      }
  }
