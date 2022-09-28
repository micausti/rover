object PathFinder {
  def shortestPath(grid: Grid, start: Coordinates, destination: Coordinates): List[List[Coordinates]] = {
    /*Here I just include an arbitrary list of coordinates to get the program to run
    A real implementation of finding the shortest path might be achieved using Dijkstra or A* algorithm

    Assumptions:
    1. I assume that any output of a list of coordinates would include the starting point (e.g. (0,0))
    2. The coordinate list represents only one move at a time (e.g. from (0,0) to (0,1))
    3. In order to handle the case where coordinates go off the grid, they can be adjusted using the ''keepOnGrid' function in the Coordinate object
    4. Because of the potential for going off the grid, this function produces a List[List[Coordinates]] so that the lists can be translated into instructions separately and combined
     */
    List(List(Coordinates(0, 0), Coordinates(0, 1), Coordinates(1, 1), Coordinates(1, 2), Coordinates(2, 2), Coordinates(2, 3), Coordinates(3, 3)))

    val xSide = (destination.x - start.x).toDouble
    val ySide = (destination.y - start.y).toDouble
    math.hypot(xSide, ySide)

    //5 x 5
    //starting point 0,0, North
    //ending point 5,5, North

    //starting.x == ending.x && starting.y == ending.y - end condition
    def findPath(start: Coordinates, end: Coordinates, initialList: List[Coordinates]): List[Coordinates] = {
      val compare: Boolean =  start.x == end.x && start.y == end.y
      compare match {
        case true => initialList
        case false =>
          val newX = incrementX(start, end)
          findPath(newX, end, initialList ++ List(newX))
        }
      }


    //figure out which direction to go
        //is starting.x < ending.x  = need to face East
        //is starting.x > ending.x = need to face West
        //is starting.y < ending.y = North
        //is starting.y > ending.y = South

    def incrementX(start: Coordinates, end: Coordinates): Coordinates = {
      start match {
        case _ if start.x < end.x => Coordinates(start.x+1, start.y)
        case _ if start.x > end.x => Coordinates(start.x - 1, start.y)
      }

      def incrementY(start: Coordinates, end: Coordinates): Coordinates = {
        start match {
          case _ if start.y < end.y => Coordinates(start.x, start.y + 1)
          case _ if start.y > end.y => Coordinates(start.x, start.y - 1)
        }
    }

    //is there at least one x move to make
       //starting.x  + 1 =< ending.x
       //make the move

    //make one x move
  }

