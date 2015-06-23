package model.pieces

import model.{SymmetricPiece, MutablePiece}
import model.MutablePiece.{Vertical, Horizontal, Orientation}

/**
 * Created by David on 22. 6. 2015.
 */
case object F extends SymmetricPiece(Array(Array(0, 1),
                                           Array(1, 1)), Horizontal)
