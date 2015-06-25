package gui

import model.Piece
import model.pieces._

import scalafx.geometry.Point2D
import scalafx.scene.Scene
import scalafx.scene.Group
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

/**
 * Created by David on 25. 6. 2015.
 */
class PieceDrawer
{
  val PieceWidth = 16
  val PieceHeight = 16

  def draw(piece: Piece) = {
    val startingPosition = new Point2D(0.0, 0.0)
    var currentPosition = new Point2D(startingPosition)

    val group = new Group

    piece.array.foreach(row => {
      row.foreach(block => {
          if (block == 1) {
            group children = new Rectangle {
              x = currentPosition.x
              y = currentPosition.y
              fill = getColor(piece)
            }
          }
          currentPosition = new Point2D(currentPosition.x, currentPosition.y + PieceHeight)
        }
      )
      currentPosition = new Point2D(currentPosition.x + PieceWidth, currentPosition.y)
    })
  }

  def getColor(piece: Piece) = piece match {
    case A => Color.ORANGE
    case B => Color.RED
    case C => Color.DARKBLUE
    case D => Color.LIGHTPINK
    case E => Color.DARKGREEN
    case F => Color.GREY
    case G => Color.LIGHTBLUE
    case H => Color.PINK
    case I => Color.YELLOW
    case J => Color.PURPLE
    case K => Color.LIGHTGREEN
    case L => Color.LIGHTGREY
    case _ => throw new Exception
  }
}
