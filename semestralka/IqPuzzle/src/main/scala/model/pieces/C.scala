package model.pieces

import model.MutablePiece
import model.MutablePiece.Horizontal

/**
 * Created by David on 22. 6. 2015.
 */
case object C extends MutablePiece(Array(Array(0, 1, 0, 0),
                                         Array(0, 1, 0, 0),
                                         Array(0, 1, 0, 0),
                                         Array(1, 1, 0, 0)), Horizontal)
