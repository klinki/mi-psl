package gui

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

/**
 * Created by David on 22. 6. 2015.
 */
object MainWindow extends JFXApp {
  val drawer = new PieceDrawer
  print(drawer.getColor(model.pieces.A))
  print(drawer.getColor(model.pieces.A reverse))


  stage = new JFXApp.PrimaryStage {
    title.value = "IQ Puzzle"
    width = 600
    height = 450
    scene = new Scene() {
      getChildren add new GameBoard
      getChildren add new MainMenu
    }
  }
}
