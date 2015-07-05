package model

import model.GameDesk.Coordinates
import model.MutablePiece.{Horizontal, Vertical, Orientation}


/**
 * Created by David on 22. 6. 2015.
 */
abstract class MutablePiece(override val array: Array[Array[Int]],
                            val orientation: Orientation,
                            override val position: Option[Coordinates] = None,
                            override val isLocked: Boolean = false)
  extends Piece(array, position, isLocked)
{
  def reverseX = reverse(Horizontal)
  def reverseY = reverse(Vertical)

  def reverse(): MutablePiece =
    createInstance(multiplyBySecondaryDiagonal(array clone), orientation)

  def reverse(orientation: Orientation): MutablePiece = {
    createInstance(reverseArray(array, orientation), orientation)
  }

  def rotate(): MutablePiece =
    createInstance(multiplyBySecondaryDiagonal(array.clone.transpose), orientation.rotate)

  def arraySwap[T](array: Array[T]) = {
    if (array.length > 2) {
      val temp = array(0)
      array(0) = array(array.length - 1)
      array(array.length - 1) = temp
    }
    array
  }
  def reverseArray(array: Array[Array[Int]], orientation: Orientation): Array[Array[Int]] = {
    val newArray = array.clone()
    orientation match {
      case Vertical => newArray.foreach(arraySwap)
      // Prohodim 1. a posledni sloupec
      case Horizontal => arraySwap(newArray)
      // Prohodim 1. a posledni radek
    }
    newArray
  }

  def getAllVariants = {
    // TODO: There are 8 variants - 2 kind of orientation and 4 rotations
    var piecesList: List[Piece]= List()
    var piece = this

    for (i <- 1 to 4) {
      piecesList = piece :: piecesList
      piecesList = piece.reverse(Horizontal) :: piecesList
      piecesList = piece.reverse(Vertical) :: piecesList
      piece = piece rotate
    }
    piecesList
  }

  def createInstance(array: Array[Array[Int]], orientation: Orientation): MutablePiece = this

  def multiplyBySecondaryDiagonal(array: Array[Array[Int]]): Array[Array[Int]] = {
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

object MutablePiece
{
  abstract class Orientation
  {
    def rotate: Orientation
  }
  case object Horizontal extends Orientation
  {
    def rotate = Vertical
  }
  case object Vertical extends Orientation
  {
    def rotate = Horizontal
  }
}
