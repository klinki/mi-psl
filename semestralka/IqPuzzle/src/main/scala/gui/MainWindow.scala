package gui

import gui.GameBoard

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.layout.BorderPane
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

/**
 * Created by David on 22. 6. 2015.
 */
object MainWindow extends JFXApp {

 // print(drawer.getColor(model.pieces.A reverse))


  stage = new JFXApp.PrimaryStage {
    title.value = "IQ Puzzle"
    width = 640
    height = 480
    minWidth = 385
    minHeight = 420
    scene = new Scene(new BorderPane {
      right = new MainMenu
      center = new BorderPane {
        top = new FreePiecesPanel
        center = new GridLayout
      }
      bottom = new GameBoard
    })
  }
}
