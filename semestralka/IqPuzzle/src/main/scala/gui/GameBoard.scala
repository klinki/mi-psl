package gui

import javafx.event.EventHandler
import javafx.scene.input

import model.GameDesk
import scalafx.geometry.{Point2D, Insets}
import scalafx.scene.layout._
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.shape.Line

/**
 * Created by David on 25. 6. 2015.
 */
class GameBoard extends Pane
{
  prefWidth = 16*11
  prefHeight = 16*5

  background = new Background(Array(new BackgroundFill(Color.DARKCYAN, new CornerRadii(0), Insets(0))))

  val drawer = new PieceDrawer
  children add drawer.draw(model.pieces.A)

  var allRectangles: List[Rectangle] = List()
  var allLines: List[Line] = List()

  var pieceWidth = width / 15
  var pieceHeight = height / 5

  for (i <- 0 until GameDesk.Rows)
  {
    for (j <- 0 until GameDesk.Cols)
    {
      val rect = new Rectangle {
        width = pieceWidth.doubleValue() + 16
        height = pieceWidth.doubleValue() + 16
        stroke = Color.BLACK
        x = j * (pieceWidth.doubleValue() + 16)
        y = i * (pieceWidth.doubleValue() + 16)
        fill = {
          if (j % 2 == 0)
            if (i % 2 == 0) Color.LIGHTGREEN else Color.LIGHTBLUE
          else
          if (i % 2 == 0) Color.LIGHTBLUE else Color.LIGHTGREEN
        }

        var dragDelta: Point2D = new Point2D(0,0)

        onMousePressed = new EventHandler[input.MouseEvent] {
          override def handle(event: input.MouseEvent): Unit = {
            dragDelta = new Point2D(layoutX.toDouble - event.getSceneX, layoutY.toDouble - event.getSceneY)
          }
        }

        onMouseDragged = new EventHandler[input.MouseEvent] {
          override def handle(event: input.MouseEvent): Unit = {
            layoutX = event.getSceneX + dragDelta.x
            layoutY = event.getSceneY + dragDelta.y
          }
        }
      }

      children.add(rect)
    }
  }

  //children.addAll(allRectangles)
}
