package model

import model.Piece.Orientation

/**
 * Created by David on 22. 6. 2015.
 */
abstract class ImmutablePiece(override var array: Array[Array[Int]]) extends Piece(array)
{
  def rotate = this
  def reverse = this
}
