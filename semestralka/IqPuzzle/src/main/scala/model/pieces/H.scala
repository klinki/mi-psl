package model.pieces

import model.{SymmetricPiece, MutablePiece}
import model.MutablePiece.{Vertical, Horizontal, Orientation}

/**
 * Created by David on 22. 6. 2015.
 */
class H(override val array: Array[Array[Int]], override val orientation: Orientation)
  extends SymmetricPiece(array, orientation)
{
  override def createInstance(array: Array[Array[Int]], orientation: Orientation): H =
    new H(array, orientation)
}

case object H extends H(Array(Array(0, 0, 1),
                              Array(0, 1, 1),
                              Array(1, 1, 0)), Horizontal)
