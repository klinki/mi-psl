package model

/**
 * Created by David on 22. 6. 2015.
 */
class Solver(val desk: GameDesk) {

  class Solution(val desk: GameDesk) {
    def isCorrect = desk.isFull

    def isFinal = false // desk.getAllEmptyCoordinates.exists(coordinate =>)
  }

  var solutions: List[GameDesk] = List()

  def isSolvable = {
    findFirstSolution
    solutions.nonEmpty
  }

  def findFirstSolution = findAllSolutions(desk, true)

  def findAllSolutions(gameDesk: GameDesk, firstSolutionOnly: Boolean = false) = {
    val position = gameDesk.getEmptyCoordinates

    if (position.isEmpty) {
      solutions = desk :: solutions
    } else {
      gameDesk.pieces.foreach {
        piece => piece.getAllVariants.foreach {
          pieceVariant => {
            if (!firstSolutionOnly || solutions.isEmpty) {
              gameDesk.insertPiece(piece, position.get)
            }
          }
        }
      }
    }
  }
}
