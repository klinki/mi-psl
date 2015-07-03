package model

import model.MutablePiece.Orientation

/**
 * Created by David on 23. 6. 2015.
 */
class SymmetricPiece(override val array: Array[Array[Int]], override val orientation: Orientation)
  extends MutablePiece(array, orientation)
{
  override def rotate: MutablePiece = new SymmetricPiece(array.transpose, orientation.rotate)
  override def reverse: MutablePiece = rotate rotate
}
