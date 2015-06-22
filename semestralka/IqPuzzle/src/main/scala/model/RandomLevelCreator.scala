package model

import model.Difficulty.Difficulty

/**
 * Created by David on 22. 6. 2015.
 */
class RandomLevelCreator(var difficulty: Difficulty) extends LevelCreator
{


  override def createLevel(): GameDesk = {
    new GameDesk
  }
}
