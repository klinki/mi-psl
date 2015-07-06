package model

import model.GameDesk.Coordinates

/**
 * Created by David on 14. 6. 2015.
 */
class GameDesk(val desk: Array[Array[Int]] =
                Array.tabulate(GameDesk.Rows, GameDesk.Cols){(x, y) => 0},
               val pieces: Set[Piece] = Set(),
               val unalignedPices: Set[Piece] = Set())
{
  def canInsertPiece(piece: Piece): Boolean = {
    getAllEmptyCoordinates.exists(canInsertPiece(piece, _))
  }

  def doesPieceFit(piece: Piece, position: Coordinates): Boolean = {
    (position.x + piece.width < GameDesk.Cols
      && position.y + piece.height < GameDesk.Rows)
  }

  def canInsertPiece(piece: Piece, position: Coordinates): Boolean = {
    if (! unalignedPices.contains(piece) || ! doesPieceFit(piece, position))
      false
    else {
      var rowNum = -1
      piece.array.map(row => {
        rowNum += 1
        var colNum = -1
        row.map(value => {
          colNum += 1
          value == 0 || desk(position.x + rowNum)(position.y + colNum) == 0
        }).reduceLeft( _ & _)
      }).reduceLeft( _ & _)
    }
  }

  def insertPiece(piece: Piece, position: Coordinates): GameDesk = {
    if (!canInsertPiece(piece, position)) {
      throw new Exception()
    }

    val newDesk = desk clone
    var rowNum = 0

    piece.array.foreach(row => {
        var colNum = 0
        row.foreach(value => {
          newDesk(position.x + rowNum)(position.y + colNum) = value
          colNum += 1
        })
        rowNum += 1
    })

    new GameDesk(newDesk, pieces + piece, unalignedPices - piece)
  }

  def removePiece(piece: Piece): GameDesk = {
    if (! pieces.contains(piece)) {
      throw new Exception()
    }

    var rowNum = 0

    piece.array.foreach(row => {
      var colNum = 0
      row.foreach(value => {
        if (value != 0) {
          desk(piece.position.get.x + rowNum)(piece.position.get.y + colNum) = 0
        }
        colNum += 1
      })
      rowNum += 1
    })

    new GameDesk(desk, pieces - piece)
  }

  def getEmptyCoordinates = {
    var x = -1
    var y = -1

    y = desk.indexWhere { row => {
        x = row.indexWhere(_ == 0)
        x != -1
      }
    }

    if (x != -1 && y != -1)
      Some(new Coordinates(x, y))
    else
      None
  }

  def getAllEmptyCoordinates = {
    var emptyCoordinates: List[Coordinates] = List()
    desk.zipWithIndex.foreach {
      case (row, x) => row.zipWithIndex.foreach {
        case (item, y) => if (item == 0) {
          emptyCoordinates = new Coordinates(x, y) :: emptyCoordinates
        }
      }
    }

    emptyCoordinates
  }

  def isFull = getEmptyCoordinates isEmpty
}

object GameDesk
{
  class Coordinates(val x: Int, val y: Int)
  {
    if (x < 0 || x > GameDesk.Rows) {
      throw new IndexOutOfBoundsException("")
    }

    if (y < 0 || y > GameDesk.Cols) {
      throw new IndexOutOfBoundsException("")
    }
  }

  val Rows = 5
  val Cols = 11
}
