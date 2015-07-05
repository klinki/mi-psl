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

  def reverse(): MutablePiece = reverse(Horizontal)

  def reverse(orientation: Orientation): MutablePiece = {
    createInstance(reverseArray(array, orientation), orientation)
  }

  def rotate(): this.type =
    createInstance(reverseArray(array.transpose, Vertical), orientation.rotate)

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

  def createInstance(array: Array[Array[Int]], orientation: Orientation): this.type = this
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
