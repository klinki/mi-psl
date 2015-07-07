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
    if (unalignedPices.isEmpty)
      false
    else {
      val map = unalignedPices.map(canInsertPiece)
      ! map.reduceLeft(_ &&_)
    }
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

  def getBoundaries(array: Array[Array[Int]]) = {
    val rowCollection = array.map(_.map(_ != 0).reduceLeft(_ || _))
    // TODO: ZIP
    val rowStart = rowCollection.indexWhere(_ == true)
    val rowEnd   = rowCollection.lastIndexWhere(_ == true)

    val colStart = array.map(_.indexWhere(_ != 0)).filter(_ >= 0).min
    val colEnd = array.map(_.lastIndexWhere(_ != 0)).filter(_ >= 0).max

    ((rowStart, rowEnd), (colStart, colEnd))
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
    if (! unalignedPices.exists(_.pieceType == piece.pieceType))
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

      val corners = getCorners(slicedArray)
      val boundaries = getBoundaries(slicedArray)

      // starting point in game desk is position
      // starting point in piece is equal to left upper corner
      var startPoint = (corners._1._1, corners._1._2)

      // If you cannot go down, select leftmost lower corner
      if (position.row + piece.height - 1 > GameDesk.Rows) {
        startPoint = (boundaries._1._2, startPoint._2)
      }

      // if you cannot go right, select rightmost coordinate
      if (position.col + piece.width - 1 > GameDesk.Cols) {
        startPoint = (startPoint._1, boundaries._2._2)
      }

      val booleanArray = indeces.map( x => {
        val (row, col) = x
        val arrayRow = position.row - (startPoint._1 - row)
        val arrayCol = position.col - (startPoint._2 - col)

        if (arrayRow < 0 || arrayCol < 0 || arrayRow >= GameDesk.Rows || arrayCol >= GameDesk.Cols )
          false
        else
          array(arrayRow)(arrayCol) == 0
      })

      booleanArray.reduceLeft( _ && _)
    }
  }

  def insertPiece(piece: Piece, position: Coordinates): GameDesk = {
    if (!canInsertPiece(piece, position)) {
      throw new Exception()
    }

    val newDesk = array.map(_.clone)

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

    val corners = getCorners(slicedArray)
    val boundaries = getBoundaries(slicedArray)

    // starting point in game desk is position
    // starting point in piece is equal to left upper corner
    var startPoint = (corners._1._1, corners._1._2)

    // If you cannot go down, select leftmost lower corner
    if (position.row + piece.height - 1 >= GameDesk.Rows) {
      startPoint = (boundaries._1._2, startPoint._2)
    }

    // if you cannot go right, select rightmost coordinate
    if (position.col + piece.width - 1 >= GameDesk.Cols) {
      startPoint = (startPoint._1, boundaries._2._2)
    }

    val booleanArray = indeces.foreach( x => {
      val (row, col) = x
      val arrayRow = position.row - (startPoint._1 - row)
      val arrayCol = position.col - (startPoint._2 - col)

      if (arrayRow < 0 || arrayCol < 0 || arrayRow >= GameDesk.Rows || arrayCol >= GameDesk.Cols )
        false
      else
        newDesk(arrayRow)(arrayCol) = slicedArray(row)(col)
    })

    val pieceToRemove = unalignedPices.find(_.pieceType == piece.pieceType).get

    piece.position = Some(position)

    new GameDesk(newDesk, pieces + piece, unalignedPices - pieceToRemove)
  }

  def removePiece(piece: Piece): GameDesk = {
    if (! pieces.exists(_.pieceType == piece.pieceType)) {
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
        x.col < y.col
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
