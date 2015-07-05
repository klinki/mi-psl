package model.pieces

import model.{PieceType, Piece, SymmetricPiece, MutablePiece}
import model.MutablePiece.{Vertical, Horizontal, Orientation}

/**
 * Created by David on 22. 6. 2015.
 */
case object G extends SymmetricPiece(Array(Array(0, 0, 1),
                                           Array(0, 0, 1),
                                           Array(1, 1, 1)), PieceType.G, Vertical)
{
  Piece.registerPiece(this)
}