import model.GameDesk
import model.GameDesk.Coordinates

val desk = new GameDesk(Array.tabulate(GameDesk.Rows, GameDesk.Cols){(x, y) => 0},
  Set(),
  model.Piece.registeredPieces)
var newDesk = desk.insertPiece(model.pieces.A.reverse, new Coordinates(0, 0))
newDesk = newDesk.insertPiece(model.pieces.J.rotate, new Coordinates(0, 3))
