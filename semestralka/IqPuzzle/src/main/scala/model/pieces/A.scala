package model.pieces

import model.MutablePiece.{Orientation, Horizontal}
import model.{PieceType, MutablePiece, Piece}
import scalafx.geometry.Point2D

/**
 * Created by David on 22. 6. 2015.
 */
case object A extends MutablePiece(Array(Array(1, 1, 1),
                                         Array(1, 0, 0),
                                         Array(0, 0, 0)), PieceType.A, Horizontal)
{

}
