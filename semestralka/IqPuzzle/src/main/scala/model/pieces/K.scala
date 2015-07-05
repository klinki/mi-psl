package model.pieces

import model.{PieceType, Piece, ImmutablePiece}

/**
 * Created by David on 22. 6. 2015.
 */
case object K extends ImmutablePiece(Array(Array(1, 1),
                                           Array(1, 1)), PieceType.K)
{
  Piece.registerPiece(this)
}