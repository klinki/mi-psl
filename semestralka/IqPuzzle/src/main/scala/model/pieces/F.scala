package model.pieces

import model.MutablePiece.{Horizontal, Vertical, Orientation}
import model.{MutablePiece, SymmetricPiece}


/**
 * Created by David on 22. 6. 2015.
 */
class F(override val array: Array[Array[Int]], override val orientation: Orientation)
  extends SymmetricPiece(array, orientation)
{
  override def createInstance(array: Array[Array[Int]], orientation: Orientation): F =
    new F(array clone, orientation)
}

case object F extends F(Array(Array(0, 1),
                              Array(1, 1)), Vertical)
