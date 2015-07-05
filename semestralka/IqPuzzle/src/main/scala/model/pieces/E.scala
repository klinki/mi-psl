package model.pieces

import model.MutablePiece
import model.MutablePiece.{Orientation, Horizontal}

/**
 * Created by David on 22. 6. 2015.
 */
class E(override val array: Array[Array[Int]], override val orientation: Orientation)
  extends MutablePiece(array, orientation)
{
  override def createInstance(array: Array[Array[Int]], orientation: Orientation): E =
    new E(array, orientation)
}

case object E extends E(Array(Array(0, 1, 0, 0),
                              Array(0, 1, 0, 0),
                              Array(1, 1, 0, 0),
                              Array(1, 0, 0, 0)), Horizontal)
{

}
