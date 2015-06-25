package gui

import scalafx.scene.control.{Label, Button}
import scalafx.scene.layout.VBox

/**
 * Created by David on 25. 6. 2015.
 */
class MainMenu extends VBox
{
  val startButton = new Button {
    text = "Start Game"
    prefHeight = 30
    prefWidth = 100
  }

  val newGameLabel = new Label {
    text = "Level: "
  }

  val timeLabel = new Label {
    text = "Time: "
  }

  children.add(startButton)
  children.add(newGameLabel)
  children.add(timeLabel)
}
