package model

import model.PieceType.PieceType
import model.GameDesk.Coordinates
import model.pieces._

/**
 * Created by David on 14. 6. 2015.
 */
abstract class Piece(val array: Array[Array[Int]],
                     val pieceType: PieceType,
                     var position: Option[Coordinates] = None,
                     val isLocked: Boolean = false) extends ArrayPrinter
{
  def width = array.transpose.map(_.map(_ != 0).reduceLeft(_ || _)).count(_ == true)
  def height = array.map(_.map(_ != 0).reduceLeft(_ || _)).count(_ == true)

  def rotate(): Piece
  def reverse(): Piece

  val (rowBoundaries, colBoundaries) = getBoundaries
  val (leftUpperCorner, rightLowerCorner) = getCorners

  def getBoundaries = {
    val rowCollection = array.map(_.map(_ != 0).reduceLeft(_ || _))
    // TODO: ZIP
    val rowStart = rowCollection.indexWhere(_ == true)
    val rowEnd   = rowCollection.lastIndexWhere(_ == true)

    val colStart = array.map(_.indexWhere(_ != 0)).filter(_ >= 0).min
    val colEnd = array.map(_.lastIndexWhere(_ != 0)).filter(_ >= 0).max

    ((rowStart, rowEnd), (colStart, colEnd))
  }

  def getCorners = {
    val colStart = array(rowBoundaries._1).indexWhere(_ != 0)
    val colEnd = array(rowBoundaries._2).lastIndexWhere(_ != 0)
    ((rowBoundaries._1, colStart), (rowBoundaries._2, colEnd))
  }

  def getAllVariants: Seq[Piece]

  def getFromArray(array: Array[Array[Int]]) = {
    var matchingVariant: Option[Piece] = None
    getAllVariants foreach {
      variant => {
        if (variant.array sameElements array) {
          matchingVariant = Some(variant)
        }
      }
    }

    matchingVariant
  }
}

object Piece
{
  var registeredPiecesList: Set[Piece] = Set[Piece]()

  def registerPiece(piece: Piece) = {
    registeredPiecesList = registeredPiecesList + piece
  }

  def registeredPieces = registeredPiecesList


  Piece.registerPiece(A)
  Piece.registerPiece(B)
  Piece.registerPiece(C)
  Piece.registerPiece(D)
  Piece.registerPiece(E)
  Piece.registerPiece(F)
  Piece.registerPiece(G)
  Piece.registerPiece(H)
  Piece.registerPiece(I)
  Piece.registerPiece(J)
  Piece.registerPiece(K)
  Piece.registerPiece(L)
}
