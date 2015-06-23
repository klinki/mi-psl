package model.pieces

import model.MutablePiece
import model.MutablePiece.Horizontal

/**
 * Created by David on 22. 6. 2015.
 */
case object A extends MutablePiece(Array(Array(1, 1, 1),
                                         Array(1, 0, 0)), Horizontal)
{

}
