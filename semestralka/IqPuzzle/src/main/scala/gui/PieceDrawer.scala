package gui

import model.GameDesk.Coordinates
import model.Piece
import model.pieces._

import scalafx.geometry.Point2D
import scalafx.scene.Group
import scalafx.scene.paint.Color
import scalafx.scene.Node
import scalafx.scene.shape.{Shape, Rectangle}

/**
 * Created by David on 25. 6. 2015.
 */
class PieceDrawer
{
  val PieceWidth = 16
  val PieceHeight = 16

  def draw(piece: Piece) = {
    val startingPosition = piece.position.get
    var currentPosition = startingPosition

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
          currentPosition = new Coordinates(currentPosition.x, currentPosition.y + PieceHeight)
        }
      )
      currentPosition = new Coordinates(currentPosition.x + PieceWidth, currentPosition.y)
    })

    group
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
