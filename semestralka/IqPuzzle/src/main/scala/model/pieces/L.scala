package model.pieces

import model.{PieceType, Piece, ImmutablePiece}

/**
 * Created by David on 22. 6. 2015.
 */
case object L extends ImmutablePiece(Array(Array(0, 1, 0),
                                           Array(1, 1, 1),
                                           Array(0, 1, 0)), PieceType.L)
{
  Piece.registerPiece(this)
}