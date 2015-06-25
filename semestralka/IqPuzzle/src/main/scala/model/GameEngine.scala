package model

/**
 * Created by David on 25. 6. 2015.
 */
class GameEngine
{
  val desk = new GameDesk
  val pieces = new Array[Piece](12)

  // val freePieces = new List[Piece]

  def loadLevel(levelCreator: LevelCreator) = levelCreator createLevel

}
