package gui

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

/**
 * Created by David on 22. 6. 2015.
 */
object MainWindow extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title.value = "Hello Stage"
    width = 600
    height = 450
    scene = new Scene() {
      fill = Color.LIGHTGREEN
      content = new Rectangle() {
        x = 25
        y = 40
        width = 100
        height = 100
      }
    }
  }
}
