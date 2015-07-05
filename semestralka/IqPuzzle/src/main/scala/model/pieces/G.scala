package model.pieces

import model.{SymmetricPiece, MutablePiece}
import model.MutablePiece.{Vertical, Horizontal, Orientation}

/**
 * Created by David on 22. 6. 2015.
 */
class G(override val array: Array[Array[Int]], override val orientation: Orientation)
  extends SymmetricPiece(array, orientation)
{
  override def createInstance(array: Array[Array[Int]], orientation: Orientation): G =
    new G(array, orientation)
}

case object G extends G(Array(Array(0, 0, 1),
                              Array(0, 0, 1),
                              Array(1, 1, 1)), Vertical)
