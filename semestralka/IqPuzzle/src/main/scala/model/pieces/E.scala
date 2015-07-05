package model.pieces

import model.{PieceType, Piece, MutablePiece}
import model.MutablePiece.{Orientation, Horizontal}

/**
 * Created by David on 22. 6. 2015.
 */
case object E extends MutablePiece(Array(Array(0, 1, 0, 0),
                                         Array(0, 1, 0, 0),
                                         Array(1, 1, 0, 0),
                                         Array(1, 0, 0, 0)), PieceType.E, Horizontal)
{
  Piece.registerPiece(this)
}
