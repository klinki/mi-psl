package model

import model.PieceType._
import model.GameDesk.Coordinates

/**
 * Created by David on 14. 6. 2015.
 */
abstract class Piece(val array: Array[Array[Int]],
                     val pieceType: PieceType,
                     val position: Option[Coordinates] = None,
                     val isLocked: Boolean = false)
{
  def width = array.length
  def height = array(0).length

  def rotate(): Piece
  def reverse(): Piece

  def getAllVariants: Seq[Piece]

  def getFromArray(array: Array[Array[Int]]) = {
    var matchingVariant: Option[Piece] = None
    getAllVariants foreach {
      variant => {
        if (variant.array eq array) {
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
  var registeredPiecesList: Set[Piece] = Set()

  def registerPiece(piece: Piece) = {
    registeredPiecesList = registeredPiecesList + piece
  }

  def registeredPieces = registeredPiecesList
}
