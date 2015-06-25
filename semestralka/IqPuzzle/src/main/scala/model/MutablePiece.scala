package model

import model.MutablePiece.{Horizontal, Vertical, Orientation}


/**
 * Created by David on 22. 6. 2015.
 */
abstract class MutablePiece(override val array: Array[Array[Int]], val orientation: Orientation)
  extends Piece(array)
{
  override def reverse: this.type = {
    /*
    match orientation {
      case Vertical => {
        array.foreach( row => arraySwap(row))

        // Prohodim 1. a posledni sloupec
      }
      case Horizontal => {
        arraySwap(array)
       // Prohodim 1. a posledni radek
      }
    }
  */
    new this.type (array, orientation)
  }

  def rotate = this

  def arraySwap[T](array: Array[T]) = {
    if (array.length > 2) {
      val temp = array(0)
      array(0) = array(array.length - 1)
      array(array.length - 1) = temp
    }
    array
  }

  def getAllVariants = {
     for (i <- 1 to 4)
       yield this rotate
       // yield this rotate reverse

  }

  def createInstance(array: Array[Array[Int]], orientation: Orientation): this.type
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
