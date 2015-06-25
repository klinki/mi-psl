package model

/**
 * Created by David on 22. 6. 2015.
 */
abstract class ImmutablePiece(override val array: Array[Array[Int]]) extends Piece(array)
{
  def rotate = this
  def reverse = this
  def getAllVariants = List(this)
}
