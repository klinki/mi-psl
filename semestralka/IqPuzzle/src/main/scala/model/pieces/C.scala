package model.pieces

import model.MutablePiece
import model.MutablePiece.{Orientation, Horizontal}

/**
 * Created by David on 22. 6. 2015.
 */
class C(override val array: Array[Array[Int]], override val orientation: Orientation)
  extends MutablePiece(array, orientation)
{
  override def createInstance(array: Array[Array[Int]], orientation: Orientation): C =
    new C(array, orientation)
}

case object C extends C(Array(Array(0, 1, 0, 0),
                                         Array(0, 1, 0, 0),
                                         Array(0, 1, 0, 0),
                                         Array(1, 1, 0, 0)), Horizontal)
