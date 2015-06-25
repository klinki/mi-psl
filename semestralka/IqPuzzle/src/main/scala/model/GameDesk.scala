package model

/**
 * Created by David on 14. 6. 2015.
 */
class GameDesk
{
  val Rows = 5
  val Cols = 11

  val desk: Array[Array[Int]] = Array.tabulate(11, 5){(x, y) => 0}
  val pieces: Array[Piece] = new Array[Piece](12)

  def canInsertPiece(piece: Piece, x: Int, y: Int) = {


    false
  }

  def insertPiece(piece: Piece, pointX: Int, pointY: Int) = {
    if (canInsertPiece(piece, pointX, pointY)) {
      var rowNum = 0

      piece.array.foreach(row => {
        var colNum = 0
        row.foreach(value => {
          desk(pointX + rowNum)(pointY + colNum) = value
          colNum += 1
        })
        rowNum += 1
      })
    }
  }

  def removePiece(piece: Piece, pointX: Int, pointY: Int) = {

  }
}
