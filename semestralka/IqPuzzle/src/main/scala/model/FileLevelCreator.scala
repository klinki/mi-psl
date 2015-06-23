package model

import scala.None
import scala.io.Source

/**
 * Created by David on 22. 6. 2015.
 */
class FileLevelCreator(val filename: String) extends LevelCreator
{
  def readFile() = {
    val charMap: Array[Array[Char]] = Array.tabulate(11, 5){(x,y) => 0}
    var i, j = 0
    Source.fromFile(filename).foreach(x => {
      if (x != '\n') {
        charMap(i)(j) = x
      } else {
        i += 1
        j = 0
      }
    })
    charMap
  }

  override def createLevel(): GameDesk = {
    val charMap = readFile()
    val piece = getPiece("a")
    new GameDesk
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
