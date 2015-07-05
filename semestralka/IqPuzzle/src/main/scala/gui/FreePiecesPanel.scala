package gui

import java.util.function.Consumer
import javafx.event.EventHandler
import javafx.scene
import javafx.scene.input.MouseEvent

import scalafx.geometry.Point2D
import scalafx.scene.Node
import scalafx.scene.input.DragEvent
import scalafx.scene.layout.FlowPane

/**
 * Created by David on 4. 7. 2015.
 */
class FreePiecesPanel extends FlowPane(5, 10)
{
  minHeight = 7 * 32

  children add new GamePiece(model.pieces.A, new Point2D(0 ,0))
  children add new GamePiece(model.pieces.B, new Point2D(100 ,0))
  children add new GamePiece(model.pieces.C, new Point2D(170 ,0))
  children add new GamePiece(model.pieces.D, new Point2D(240 ,0))
  children add new GamePiece(model.pieces.E, new Point2D(310 ,0))
  children add new GamePiece(model.pieces.F, new Point2D(380 ,0))
  children add new GamePiece(model.pieces.G, new Point2D(450 ,0))
  children add new GamePiece(model.pieces.H, new Point2D(560 ,0))
  children add new GamePiece(model.pieces.I, new Point2D(660 ,0))
  children add new GamePiece(model.pieces.J, new Point2D(740 ,0))
  children add new GamePiece(model.pieces.K, new Point2D(810 ,0))
  children add new GamePiece(model.pieces.L, new Point2D(880 ,0))

  val self = this
   /*
  children.forEach(new Consumer[scene.Node] {
    override def accept(node: scene.Node): Unit = {
      node.setOnDragDetected(new EventHandler[MouseEvent] {
        override def handle(event: MouseEvent): Unit = {
          self.children.remove(this)
        }
      })
    }
  })
  */
}
