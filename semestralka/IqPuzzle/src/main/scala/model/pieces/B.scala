package model.pieces

import model.MutablePiece
import model.MutablePiece.Horizontal

/**
 * Created by David on 22. 6. 2015.
 */
case object B extends MutablePiece(Array(Array(1, 1, 1),
                                         Array(1, 1, 0)), Horizontal)
