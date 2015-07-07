import model.pieces._
import model.GameDesk
import model.GameDesk.Coordinates
import model.Solver

val desk = new GameDesk(Array.tabulate(GameDesk.Rows, GameDesk.Cols){(x, y) => 0},
  Set(),
  model.Piece.registeredPieces)
var newDesk = desk.insertPiece(D.reverse, new Coordinates(0, 0))
newDesk = newDesk.insertPiece(A, new Coordinates(0, 1))
println(newDesk)
newDesk = newDesk.insertPiece(E.rotate.reverse, new Coordinates(0, 4))
println(newDesk)
newDesk = newDesk.insertPiece(K, new Coordinates(0, 6))
println(newDesk)
newDesk = newDesk.insertPiece(L, new Coordinates(1, 5))
println(newDesk)
newDesk = newDesk.insertPiece(B.reverse.rotate.rotate, new Coordinates(2, 2))
println(newDesk)
newDesk = newDesk.insertPiece(F, new Coordinates(3, 1))
println(newDesk)
newDesk = newDesk.insertPiece(J.rotate, new Coordinates(4, 2))
println(newDesk)
val solver = new Solver(newDesk)

// newDesk = newDesk.insertPiece(model.pieces.G.rotate.rotate.rotate, new Coordinates(0, 8))
//newDesk = newDesk.insertPiece(model.pieces.I.rotate.reverse, new Coordinates(1, 8))
//newDesk = newDesk.insertPiece(model.pieces.H.rotate.rotate, new Coordinates(2, 7))

// newDesk = newDesk.insertPiece(model.pieces.C.rotate.reverse, new Coordinates(3, 10))

solver.findFirstSolution()
