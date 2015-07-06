package model

import model.PieceType.PieceType
import model.GameDesk.Coordinates
import model.pieces._

/**
 * Created by David on 14. 6. 2015.
 */
abstract class Piece(val array: Array[Array[Int]],
                     val pieceType: PieceType,
                     val position: Option[Coordinates] = None,
                     val isLocked: Boolean = false)
{
  def width = array.transpose.map(_.map(_ != 0).reduceLeft(_ || _)).count(_ == true)
  def height = array.map(_.map(_ != 0).reduceLeft(_ || _)).count(_ == true)

  def rotate(): Piece
  def reverse(): Piece

  val (leftUpperCorner, rightLowerCorner) = getStartingPosition
    /*
  def leftUpperCorner = getStartingPosition._1
  def rightLowerCorner = getStartingPosition._2
                 */

  def getStartingPosition = {
    val rowCollection = array.map(_.map(_ != 0).reduceLeft(_ || _))
    val colCollection = array.map(_.indexWhere(_ != 0))
    /*
      val rowStart = array.indexWhere(_.map(_ != 0).reduceLeft(_ || _))
      val colStart = array.map(_.indexWhere(_ != 0)).min

      var rowEnd = array.lastIndexWhere(_.map(_ != 0).reduceLeft(_ || _))
      var colEnd = array.map(_.indexWhere(_ != 0)).max
    */
    val rowStart = rowCollection.indexWhere(_ == true)
    val rowEnd   = rowCollection.lastIndexWhere(_ == true)

    val colStart = colCollection.min
    val colEnd = colCollection.max

    ((rowStart, colStart), (rowEnd, colEnd))
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

  override def toString: String = {
    val builder = new StringBuilder()
    array.foreach(row => {
      row.foreach(char => {
        builder.append(char)
      })
      builder.append('\n')
    })
    builder toString
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
