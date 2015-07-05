package gui

import javafx.event.EventHandler
import javafx.scene.input
import javafx.scene.input._

import com.sun.scenario.effect.impl.state.RenderState.EffectCoordinateSpace
import model.GameDesk
import model.GameDesk.Coordinates
import model.pieces.A
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
  class GameBoardRectangle(val coordinates: Coordinates) extends Rectangle
  {
    width = pieceWidth.doubleValue() + 32
    height = pieceWidth.doubleValue() + 32
    stroke = Color.BLACK

    var dragDelta: Point2D = new Point2D(0,0)

    onMousePressed = new EventHandler[input.MouseEvent] {
      override def handle(event: input.MouseEvent): Unit = {
        dragDelta = new Point2D(layoutX.toDouble - event.getSceneX,
          layoutY.toDouble - event.getSceneY)
          //event.setDragDetect(true)
          rect.setMouseTransparent(true)
      }
    }

    onMouseDragged = new EventHandler[input.MouseEvent] {
      override def handle(event: input.MouseEvent): Unit = {
        layoutX = event.getSceneX + dragDelta.x
        layoutY = event.getSceneY + dragDelta.y
       // println("OnMouseDragged")
        rect.setMouseTransparent(true)
        event.consume()
      }
    }

    val rect = this

    onDragDetected = new EventHandler[MouseEvent] {
      override def handle(event: MouseEvent): Unit = {
       // var db: Dragboard = rect.startDragAndDrop(TransferMode.LINK)
        var clibpoard = new ClipboardContent()
        //event.

        println("OnDragDetected")
        rect.startFullDrag()
      //  event.consume()
      }
    }

    onMouseDragEntered = new EventHandler[MouseDragEvent] {
      override def handle(event: MouseDragEvent): Unit = {
        println("onMouseDragEntered")
      }
    }

    onMouseDragExited = new EventHandler[MouseDragEvent] {
      override def handle(event: MouseDragEvent): Unit = {
        println("onMouseDragExited")
      }
    }

    onMouseDragOver = new EventHandler[MouseDragEvent] {
      override def handle(event: MouseDragEvent): Unit = {
        println("onMouseDragExited")

      }
    }

    onMouseDragReleased = new EventHandler[MouseDragEvent] {
      override def handle(event: MouseDragEvent): Unit = {
        println ("onMouseDragReleased")
      }
    }

    onDragDropped = new EventHandler[DragEvent] {
      override def handle(event: DragEvent): Unit = {
        println("OnDragDropped")
      }
    }

    onDragDone = new EventHandler[DragEvent] {
      override def handle(event: DragEvent): Unit = {
        println("OnDragDone")
      }
    }

    onDragOver = new EventHandler[DragEvent] {
      override def handle(event: DragEvent): Unit = {
        println("OnDragOver")
        //event.acceptTransferModes(TransferMode.LINK)
      }
    }

    onDragEntered = new EventHandler[DragEvent] {
      override def handle(event: DragEvent): Unit = {
        event.acceptTransferModes(TransferMode.LINK)
        println("OnDragEnetered")
      }
    }

  }

  prefWidth = 16*11
  prefHeight = 16*5

  background = new Background(Array(new BackgroundFill(Color.DARKCYAN,
    new CornerRadii(0),
    Insets(0))))

  val drawer = new PieceDrawer

  var allRectangles: List[Rectangle] = List()

  var pieceWidth = width / 15
  var pieceHeight = height / 5

  val startX = 0 // (width.value  - (pieceWidth.doubleValue() + 16) * GameDesk.Cols) / 2
  val startY = 0 //(height.value  - (pieceHeight.doubleValue() + 16) * GameDesk.Rows) / 2
 /*
  for (i <- 0 until GameDesk.Rows)
  {
    for (j <- 0 until GameDesk.Cols)
    {
      val rect = new GameBoardRectangle(new Coordinates(i, j)) {
        x = startX + j * (pieceWidth.doubleValue() + 32)
        y = startY + i * (pieceWidth.doubleValue() + 32)
        fill = {
          if (j % 2 == 0)
            if (i % 2 == 0) Color.LIGHTGREEN else Color.LIGHTBLUE
          else
          if (i % 2 == 0) Color.LIGHTBLUE else Color.LIGHTGREEN
        }
      }

      children.add(rect)
    }
  }
            */

  //children.addAll(allRectangles)
  def getCoordinates(gamePiece: GamePiece): Option[Coordinates] = {
    None
  }
}
