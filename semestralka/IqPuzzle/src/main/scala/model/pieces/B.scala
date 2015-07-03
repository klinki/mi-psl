package model.pieces

import model.Piece
import model.MutablePiece
import model.MutablePiece.{Orientation, Horizontal}

/**
 * Created by David on 22. 6. 2015.
 */
class B(override val array: Array[Array[Int]], override val orientation: Orientation)
  extends MutablePiece(array, orientation)
{
  override def createInstance(array: Array[Array[Int]], orientation: Orientation): B = new B(array, orientation)
}


case object B extends B(Array(Array(1, 1, 1),
                              Array(1, 1, 0),
                              Array(0, 0, 0)), Horizontal)
