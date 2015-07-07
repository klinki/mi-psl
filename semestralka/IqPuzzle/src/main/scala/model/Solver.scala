package model

/**
 * Created by David on 22. 6. 2015.
 */
class Solver(val desk: GameDesk) {
  var solutions: List[GameDesk] = List()

  def isSolvable = {
    findFirstSolution
    solutions.nonEmpty
  }

  def findFirstSolution() = findAllSolutions(desk, true)

  def findAllSolutions(gameDesk: GameDesk, firstSolutionOnly: Boolean = false): Unit = {
    val position = gameDesk.getEmptyCoordinates

    if (position.isEmpty) {
      solutions = gameDesk :: solutions
    } else {
      gameDesk.unalignedPices.foreach {
        piece => piece.getAllVariants.foreach {
          pieceVariant => {
            if (!firstSolutionOnly || solutions.isEmpty) {
              if (gameDesk.canInsertPiece(pieceVariant, position.get)) {
                val newDesk = gameDesk.insertPiece(pieceVariant, position.get)

                if (! newDesk.doesHaveDeadPiece ) // && ! newDesk.doesHaveDeadSpot
                  findAllSolutions(newDesk, firstSolutionOnly)
              }
            }
          }
        }
      }
    }
  }
}
