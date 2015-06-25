package model.pieces

import model.{SymmetricPiece, MutablePiece}
import model.MutablePiece.{Orientation, Horizontal, Vertical}

/**
 * Created by David on 22. 6. 2015.
 */
case object I extends SymmetricPiece(Array(Array(1, 0, 1),
                                           Array(1, 1, 1),
                                           Array(0, 0, 0)), Horizontal)
