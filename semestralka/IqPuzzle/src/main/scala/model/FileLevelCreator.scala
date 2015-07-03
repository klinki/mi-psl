package model

import java.util.NoSuchElementException

import scala.None
import scala.io.Source

/**
 * Created by David on 22. 6. 2015.
 */
class FileLevelCreator(val filename: String) extends LevelCreator
{
  val charMap = Array.tabulate(11, 5){(x,y) => 0}
  var piecesList = List[Piece]()

  def readFile() = {
    var i, j = 0
    Source.fromFile(filename).foreach(x => {
      if (x != '\n') {
        charMap(i)(j) = x toLower
      } else {
        i += 1
        j = 0
      }
    })
    charMap
  }

  override def createLevel(): GameDesk = {
    readFile()

    for (x <- 'a' to 'l') {
      try {
        val array = getPieceArray(x) map( row =>  row map(item => {
          if (item == x) 1 else 0
        }))

        Piece.registeredPieces foreach(piece => {
          val result = piece.getFromArray(array)
          if (result isDefined) {
            piecesList = result.get :: piecesList
          }
        })
      }
    }

    val piece = getPiece("a")
    new GameDesk
  }

  def getPieceArray(letter: Char) = {
    var firstCol = -1
    var lastCol = -1

    val firstRow = charMap.indexWhere { row => {
      firstCol = row.indexWhere(item => item == letter)
      firstCol >= 0
      }
    }

    val lastRow = charMap.lastIndexWhere { row => {
      lastCol = row.lastIndexWhere(item => item == letter)
      lastCol >= 0
      }
    }

    if (firstRow == -1 || firstCol == -1) {
      throw new NoSuchElementException("Piece not found in array")
    }

    charMap.slice(firstRow, lastRow).map(_ slice(firstCol, lastCol))
  }

  def getPiece(input: String) = {
    input toLowerCase match {
      case "a"  => Some(pieces.A)
      case "b"  => Some(pieces.B)
      case "c"  => Some(pieces.C)
      case "d"  => Some(pieces.D)
      case "e"  => Some(pieces.E)
      case "f"  => Some(pieces.F)
      case "g"  => Some(pieces.G)
      case "h"  => Some(pieces.H)
      case "i"  => Some(pieces.I)
      case "j"  => Some(pieces.J)
      case "k"  => Some(pieces.L)
      case "l"  => Some(pieces.L)
      case _    => None
    }
  }
}
