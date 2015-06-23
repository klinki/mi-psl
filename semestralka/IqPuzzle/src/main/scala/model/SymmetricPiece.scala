package model

import model.MutablePiece.Orientation

/**
 * Created by David on 23. 6. 2015.
 */
class SymmetricPiece(override val array: Array[Array[Int]], override val orientation: Orientation)
  extends MutablePiece(array, orientation)
{
  def rotate = new I(array.transpose, orientation.rotate)
  def reverse = rotate rotate
}
