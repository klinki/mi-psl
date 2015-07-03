package model.pieces

import model.MutablePiece.{Orientation, Horizontal}
import model.{MutablePiece, Piece}
import scalafx.geometry.Point2D

/**
 * Created by David on 22. 6. 2015.
 */
class A(override val array: Array[Array[Int]], override val orientation: Orientation)
  extends MutablePiece(array, orientation)
{
  override def createInstance(array: Array[Array[Int]], orientation: Orientation): MutablePiece =
    new A(array, orientation)
}

case object A extends A(Array(Array(1, 1, 1),
                              Array(1, 0, 0),
                              Array(0, 0, 0)), Horizontal)
