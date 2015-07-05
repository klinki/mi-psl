package model.pieces

import model.MutablePiece.{Horizontal, Vertical, Orientation}
import model.{PieceType, Piece, MutablePiece, SymmetricPiece}


/**
 * Created by David on 22. 6. 2015.
 */
case object F extends SymmetricPiece(Array(Array(0, 1),
                                           Array(1, 1)), PieceType.F, Vertical)
{
  Piece.registerPiece(this)
}