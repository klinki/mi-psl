package model.pieces

import model.MutablePiece
import model.MutablePiece.{Orientation, Vertical}

/**
 * Created by David on 22. 6. 2015.
 */
case class J(override val array: Array[Array[Int]], override val orientation: Orientation)
  extends MutablePiece(array, orientation)
{
  def rotate = J(array.transpose, orientation.rotate)
  def reverse = this
}

case object J extends J(Array(Array(1),
                              Array(1),
                              Array(1),
                              Array(1)), Vertical)
