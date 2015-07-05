package model

import model.GameDesk.Coordinates
import model.MutablePiece.{Horizontal, Vertical, Orientation}
import model.PieceType.PieceType


/**
 * Created by David on 22. 6. 2015.
 */
class MutablePiece(override val array: Array[Array[Int]],
                   override val pieceType: PieceType,
                            val orientation: Orientation,
                            override val position: Option[Coordinates] = None,
                            override val isLocked: Boolean = false)
  extends Piece(array, pieceType, position, isLocked)
{
  def reverse(): MutablePiece = new MutablePiece(
    MutablePiece.multiplyBySecondaryDiagonal(array), pieceType, orientation)

  def rotate(): MutablePiece = new MutablePiece(
    MutablePiece.multiplyBySecondaryDiagonal(array transpose), pieceType,
    orientation.rotate)

  def getAllVariants = {
    var piecesList: List[Piece]= List()
    var piece = this

    for (i <- 1 to 2) {
      piece = piece.reverse
      for (j <- 1 to 4) {
        piecesList = piece :: piecesList
        piece = piece rotate
      }
    }
    piecesList
  }
}

object MutablePiece
{
  abstract class Orientation{def rotate: Orientation}
  case object Horizontal extends Orientation { def rotate = Vertical }
  case object Vertical extends Orientation { def rotate = Horizontal }

  def multiplyBySecondaryDiagonal(array: Array[Array[Int]]):
    Array[Array[Int]] = {
    var currentRow = 0
    var currentCol = array.length - 1
    val secondaryDiagonal = Array.tabulate(array.length, array.length){
      (y, x) => {
        if (y == currentRow && x == currentCol) {
          currentRow += 1
          currentCol -= 1
          1
        }
        else
          0
      }
    }

    def mult[A](a: Array[Array[A]], b: Array[Array[A]])(implicit n: Numeric[A]) = {
      import n._
      for (row <- a)
        yield for(col <- b.transpose)
          yield row zip col map Function.tupled(_*_) reduceLeft (_+_)
    }

    mult(array, secondaryDiagonal).map(_.toList.toArray)
  }

}
