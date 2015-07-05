package model

import model.GameDesk.Coordinates

/**
 * Created by David on 14. 6. 2015.
 */
abstract class Piece(val array: Array[Array[Int]],
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
  var registeredPiecesList: List[Piece] = List()

  def registerPiece(piece: Piece) = {
    registeredPiecesList = piece :: registeredPiecesList
  }

  def registeredPieces = registeredPiecesList
}
