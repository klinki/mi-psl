package model

import model.PieceType.PieceType

/**
 * Created by David on 22. 6. 2015.
 */
abstract class ImmutablePiece(override val array: Array[Array[Int]],
                               override val pieceType: PieceType)
  extends Piece(array, pieceType)
{
  def rotate() = this
  def reverse() = this
  def getAllVariants = List(this)
}
