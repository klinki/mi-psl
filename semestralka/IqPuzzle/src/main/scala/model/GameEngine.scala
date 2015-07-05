package model

import java.util.{TimerTask, Timer}

import model.GameDesk.Coordinates

/**
 * Created by David on 25. 6. 2015.
 */
class GameEngine
{
  var desk = new GameDesk
  val pieces = new Array[Piece](12)
  var moves = 0
  var elapsedTime = 0

  val timer = new Timer("Stopwatch Timer", true);

  timer.schedule(new TimerTask {
    override def run(): Unit = {
      elapsedTime += 1
    }
  }, 100)

  def insertPiece(piece: Piece, position: Coordinates) = {
    desk = desk.insertPiece(piece, position)
    moves += 1

    if (desk.isFull) {

    }
  }

  def removePiece(piece: Piece) = {
    desk = desk.removePiece(piece)
    moves += 1
  }
}

