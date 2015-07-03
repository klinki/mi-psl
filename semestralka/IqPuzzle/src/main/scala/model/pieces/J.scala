package model.pieces

import model.MutablePiece
import model.MutablePiece.{Orientation, Vertical}

/**
 * Created by David on 22. 6. 2015.
 */
class J(override val array: Array[Array[Int]], override val orientation: Orientation)
  extends MutablePiece(array, orientation)
{
  override def rotate: J = new J(array.transpose, orientation.rotate)
  override def reverse: J = this
}

case object J extends J(Array(Array(0, 1, 0, 0),
                              Array(0, 1, 0, 0),
                              Array(0, 1, 0, 0),
                              Array(0, 1, 0, 0)), Vertical)
