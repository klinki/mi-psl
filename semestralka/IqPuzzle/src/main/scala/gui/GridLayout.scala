package gui

import javafx.event.EventHandler
import javafx.scene.input
import javafx.scene.input.{MouseEvent, DragEvent}

import model.GameDesk
import model.GameDesk.Coordinates

import scalafx.beans.binding.{NumberBinding, Bindings}
import scalafx.geometry.{Insets, Point2D}
import scalafx.scene.input.MouseDragEvent
import scalafx.scene.layout.{CornerRadii, BackgroundFill, Background, GridPane}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.Group

/**
 * Created by David on 4. 7. 2015.
 */
class GridLayout extends GridPane
{
  class GameBoardRectangle(val coordinates: Coordinates) extends Rectangle {
    width = 32
    height = 32
    stroke = Color.BLACK

    var dragDelta: Point2D = new Point2D(0, 0)

    onMousePressed = new EventHandler[input.MouseEvent] {
      override def handle(event: input.MouseEvent): Unit = {
        println(event.getX + " " + event.getY)
      }
    }

    onDragOver = new EventHandler[DragEvent] {
      override def handle(event: DragEvent): Unit = {
        println(event.getX + " " + event.getY)

      }
    }

    onMouseDragEntered = new EventHandler[input.MouseDragEvent] {
      override def handle(event: input.MouseDragEvent): Unit = {
        println(event.getX + " " + event.getY)
      }
    }
  }
      /*
  prefWidth = 16*11
  prefHeight = 16*5
            */
  background = new Background(Array(new BackgroundFill(Color.LIGHTGREY,
    new CornerRadii(0),
    Insets(0))))

  val drawer = new PieceDrawer

  var allRectangles: List[Rectangle] = List()

  var pieceWidth = width / 15
  var pieceHeight = height / 5

  val startX = 0 // (width.value  - (pieceWidth.doubleValue() + 16) * GameDesk.Cols) / 2
  val startY = 0 //(height.value  - (pieceHeight.doubleValue() + 16) * GameDesk.Rows) / 2

  //var holderSize: NumberBinding  = new NumberBinding{}//= Bindings.min(height, width)
  for (i <- 0 until GameDesk.Rows)
  {
    for (j <- 0 until GameDesk.Cols)
    {
      val rect = new GameBoardRectangle(new Coordinates(i, j)) {
       // width bind holderSize.divide(Math.max(GameDesk.Rows, GameDesk.Cols))
       // height bind holderSize.divide(Math.max(GameDesk.Rows, GameDesk.Cols))
        fill = {
          if (j % 2 == 0)
            if (i % 2 == 0) Color.LIGHTGREEN else Color.LIGHTBLUE
          else
          if (i % 2 == 0) Color.LIGHTBLUE else Color.LIGHTGREEN
        }
      }

      val grid = this

      rect.onMouseClicked = new EventHandler[MouseEvent] {
        override def handle(event: MouseEvent): Unit = {
          println("Clicked: " + GridPane.getRowIndex(rect) + " " + GridPane.getColumnIndex(rect))
        }
      }

      add(rect, j, i)
    }
  }

  def getCoordinates(gamePiece: GamePiece): Option[Coordinates] = {
    None
  }
}
