package model.pieces

import model.{PieceType, Piece, MutablePiece}
import model.MutablePiece.{Orientation, Vertical}

/**
 * Created by David on 22. 6. 2015.
 */
case object J extends MutablePiece(Array(Array(0, 1, 0, 0),
                                         Array(0, 1, 0, 0),
                                         Array(0, 1, 0, 0),
                                         Array(0, 1, 0, 0)), PieceType.J, Vertical)
{
  Piece.registerPiece(this)
}