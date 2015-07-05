package model.pieces

import model.{SymmetricPiece, MutablePiece}
import model.MutablePiece.{Orientation, Horizontal, Vertical}

/**
 * Created by David on 22. 6. 2015.
 */
class I(override val array: Array[Array[Int]], override val orientation: Orientation)
  extends SymmetricPiece(array, orientation)
{
  override def createInstance(array: Array[Array[Int]], orientation: Orientation): I =
    new I(array, orientation)
}

case object I extends I(Array(Array(1, 0, 1),
                              Array(1, 1, 1),
                              Array(0, 0, 0)), Horizontal)
