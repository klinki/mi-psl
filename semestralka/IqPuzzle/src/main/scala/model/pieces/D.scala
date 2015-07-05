package model.pieces

import model.MutablePiece
import model.MutablePiece.{Orientation, Horizontal}

/**
 * Created by David on 22. 6. 2015.
 */
class D(override val array: Array[Array[Int]], override val orientation: Orientation)
  extends MutablePiece(array, orientation)
{
  override def createInstance(array: Array[Array[Int]], orientation: Orientation): D =
    new D(array, orientation)
}

case object D extends D(Array(Array(0, 1, 0, 0),
                              Array(0, 1, 0, 0),
                              Array(1, 1, 0, 0),
                              Array(0, 1, 0, 0)), Horizontal)
