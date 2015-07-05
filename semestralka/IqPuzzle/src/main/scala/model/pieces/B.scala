package model.pieces

import model.{PieceType, Piece, MutablePiece}
import model.MutablePiece.{Orientation, Horizontal}

/**
 * Created by David on 22. 6. 2015.
 */
case object B extends MutablePiece(Array(Array(1, 1, 1),
                                         Array(1, 1, 0),
                                         Array(0, 0, 0)), PieceType.B, Horizontal)
{
  Piece.registerPiece(this)
}
