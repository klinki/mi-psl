package model

import model.GameDesk.Coordinates

/**
 * Created by David on 14. 6. 2015.
 */
class GameDesk(val array: Array[Array[Int]] =
                Array.tabulate(GameDesk.Rows, GameDesk.Cols){(x, y) => 0},
               val pieces: Set[Piece] = Set(),
               val unalignedPices: Set[Piece] = Set())
extends ArrayPrinter
{
  def doesHaveDeadPiece = {
    val map = unalignedPices.map(canInsertPiece)
    ! map.reduceLeft(_ &&_)
  }

  def doesHaveDeadSpot = {
    ! getAllEmptyCoordinates.map(coordinate => {
      val deadSpot = unalignedPices.flatMap(_.getAllVariants).exists(canInsertPiece(_, coordinate))
      deadSpot
    }
    ).reduceLeft(_ && _)
  }

  def canInsertPiece(piece: Piece): Boolean = {
    getAllEmptyCoordinates.exists(coordinates =>
      piece.getAllVariants.exists(canInsertPiece(_, coordinates))
    )
  }

  // Rewrite this method: Use left-most, right-most, top-most and bottom-most coordinates
  def doesPieceFit(piece: Piece, position: Coordinates): Boolean = {
    (position.row + piece.height <= GameDesk.Rows
      && position.col + piece.width <= GameDesk.Cols)
  }

  def getCorners(array: Array[Array[Int]]) = {
    val rowCollection = array.map(_.map(_ != 0).reduceLeft(_ || _))
    val rowStart = rowCollection.indexWhere(_ == true)
    val rowEnd   = rowCollection.lastIndexWhere(_ == true)

    val colStart = array(rowStart).indexWhere(_ != 0)
    val colEnd = array(rowEnd).lastIndexWhere(_ != 0)
    ((rowStart, colStart), (rowEnd, colEnd))
  }

  def canInsertPiece(piece: Piece, position: Coordinates): Boolean = {
    if (! unalignedPices.contains(piece) || ! doesPieceFit(piece, position))
      false
    else {
      val slicedArray =
        piece.array.slice(piece.rowBoundaries._1, piece.rowBoundaries._2 + 1)
          .map(_.slice(piece.colBoundaries._1, piece.colBoundaries._2 + 1))

      val indeces = slicedArray.zipWithIndex.flatMap {
          case (rowArray, row) => rowArray.zipWithIndex.map {
            case (value, col) => {
              if (value != 0)
                Some((row, col))
              else
                None
            }
          }
        }.filter(_.isDefined).map(_.get)

      // starting point in game desk is position
      // starting point in piece is equal to left upper corner
      val startPoint = getCorners(slicedArray)._1

      val booleanArray = indeces.map( x => {
        val (row, col) = x
        val sol = array(position.row + row - startPoint._1)(position.col + col - startPoint._2) == 0
        sol
      })

      booleanArray.reduceLeft( _ && _)
    }
  }

  def insertPiece(piece: Piece, position: Coordinates): GameDesk = {
    if (!canInsertPiece(piece, position)) {
      throw new Exception()
    }

    val newDesk = array.map(_.clone)
    var rowNum = 0
    val desk = new model.GameDesk(Array.tabulate(model.GameDesk.Rows, model.GameDesk.Cols){(x, y) => 0},
      Set(),
      model.Piece.registeredPieces)
    val slicedArray =
      piece.array.slice(piece.rowBoundaries._1, piece.rowBoundaries._2 + 1)
        .map(_.slice(piece.colBoundaries._1, piece.colBoundaries._2 +   1))

    slicedArray.foreach(row => {
        var colNum = 0
        row.foreach(value => {
          newDesk(position.row + rowNum)(position.col + colNum) = value
          colNum += 1
        })
        rowNum += 1
    })

    val pieceToRemove = unalignedPices.find(_.pieceType == piece.pieceType).get

    new GameDesk(newDesk, pieces + piece, unalignedPices - pieceToRemove)
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
          array(piece.position.get.row + rowNum)(piece.position.get.col + colNum) = 0
        }
        colNum += 1
      })
      rowNum += 1
    })

    new GameDesk(array, pieces - piece)
  }

  def getEmptyCoordinates = getAllEmptyCoordinates headOption

  def getAllEmptyCoordinates: Seq[Coordinates] = {
    var emptyCoordinates: List[Coordinates] = List()

    array.zipWithIndex.foreach {
      case (row, x) => row.zipWithIndex.foreach {
        case (item, y) => if (item == 0) {
          emptyCoordinates = new Coordinates(x, y) :: emptyCoordinates
        }
      }
    }

    emptyCoordinates sortWith((x, y) => {
      if (x.row == y.row)
        x.col <= y.col
      else
        x.row < y.row
    })
  }

  def isFull = getEmptyCoordinates isEmpty
}

object GameDesk
{
  class Coordinates(val row: Int, val col: Int)
  {
    if (row < 0 || row > GameDesk.Rows) {
      throw new IndexOutOfBoundsException("")
    }

    if (col < 0 || col > GameDesk.Cols) {
      throw new IndexOutOfBoundsException("")
    }

    override def toString() =  "(" + row + ", " + col + ")"
  }

  val Rows = 5
  val Cols = 11
}
