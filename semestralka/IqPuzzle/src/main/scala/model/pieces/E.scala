package model.pieces

import model.MutablePiece
import model.MutablePiece.Horizontal

/**
 * Created by David on 22. 6. 2015.
 */
case object E extends MutablePiece(Array(Array(0, 1),
                                         Array(0, 1),
                                         Array(1, 1),
                                         Array(1, 0)), Horizontal)
{

}
