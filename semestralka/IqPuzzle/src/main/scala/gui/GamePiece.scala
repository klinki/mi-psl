package gui

import javafx.scene.input.{MouseButton, MouseEvent}

import model.Piece
import model.pieces._

import scalafx.Includes._
import scalafx.geometry.Point2D
import scalafx.scene.Group
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

import javafx.event.EventHandler

class GamePiece(var piece: Piece, var location: Point2D) extends Group
{
  val PieceWidth = 32
  val PieceHeight = 32

  def draw() = {
    val startingPosition = location
    var currentPosition = location

    val group = new Group()

    piece.array.foreach(row => {
      row.foreach(block => {
        if (block == 1) {
          val rect = new Rectangle {
            x = currentPosition.x
            y = currentPosition.y
            fill = getColor(piece)
            width = PieceWidth
            height = PieceHeight
            stroke = Color.BLACK
          }

            children.add(rect)
        }
        currentPosition = new Point2D(currentPosition.x  + PieceWidth, currentPosition.y)
        }
      )
      currentPosition = new Point2D(startingPosition.x , currentPosition.y + PieceHeight)
    })


  }
          draw()
 // children add draw()
  // getChildren add draw()

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
    case _ => Color.BLACK
  }

  onMouseClicked = new EventHandler[MouseEvent] {
    override def handle(event: MouseEvent): Unit = {
      if (event.getButton eq MouseButton.PRIMARY) {
        piece = piece.rotate()
      } else if (event.getButton eq MouseButton.SECONDARY) {
        piece = piece.reverse()
      }
      children.clear()
      draw()
    }
  }
}
