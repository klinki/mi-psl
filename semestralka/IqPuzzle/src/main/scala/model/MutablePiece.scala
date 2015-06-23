package model

import model.MutablePiece.{Horizontal, Vertical, Orientation}


/**
 * Created by David on 22. 6. 2015.
 */
abstract class MutablePiece(override val array: Array[Array[Int]], val orientation: Orientation)
  extends Piece(array)
{
  def reverse {
    match orientation {
      case Vertical => {
        array.foreach( row => arraySwap(row))
        array
        // Prohodim 1. a posledni sloupec
      }
      case Horizontal => {
        arraySwap(array)
       // Prohodim 1. a posledni radek
      }
    }

    MutablePiece(array, orientation)
  }

  def arraySwap[T](array: Array[T]) = {
    if (array.length > 2) {
      val temp = array(0)
      array(0) = array(array.length - 1)
      array(array.length - 1) = temp
    }
    array
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
