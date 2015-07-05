package model.pieces

import model.MutablePiece.{Vertical, Orientation}
import model.SymmetricPiece


/**
 * Created by David on 22. 6. 2015.
 */
class F(override val array: Array[Array[Int]], override val orientation: Orientation)
  extends SymmetricPiece(array, orientation)
{
  override def createInstance(array: Array[Array[Int]], orientation: Orientation): F =
    new F(array, orientation)
}

case object F extends F(Array(Array(0, 1),
                              Array(1, 1)), Vertical)
