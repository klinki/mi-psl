package model

import model.MutablePiece.Orientation
import model.PieceType.PieceType

/**
 * Created by David on 23. 6. 2015.
 */
abstract class SymmetricPiece(override val array: Array[Array[Int]],
                              override val pieceType: PieceType,
                              override val orientation: Orientation)
  extends MutablePiece(array, pieceType, orientation)
{
  override def reverse(): MutablePiece = rotate rotate

  override def getAllVariants = {
    var piecesList: List[Piece] = List()
    var piece: Piece = this

    for (i <- 1 to 4) {
      piecesList = piece :: piecesList
      piece = piece.rotate
    }

    piecesList
  }
}
