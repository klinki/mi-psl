package model

/**
 * Created by David on 14. 6. 2015.
 */
class Piece(var array: Array[Array[Int]])
{
  def width = array.length
  def height = array(0).length

  def rotate = {
    // first row => last column
    var rotatedArray = array.transpose
  }

  def reverse = {

  }
}

object Piece
{

  def getAll = {
    val A = ((1, 1, 1),
             (1, 0, 0))

    val B = ((1, 1, 1),
             (1, 1, 0))

    val C = ((0, 1),
             (0, 1),
             (0, 1),
             (1, 1))

    val D = ((0, 1),
             (0, 1),
             (1, 1),
             (0, 1))


    val E = ((0, 1),
             (0, 1),
             (1, 1),
             (1, 0))

    val F = ((0, 1),
             (1, 1))

    val G = ((0, 0, 1),
             (0, 0, 1),
             (1, 1, 1))

    val H = ((0, 0, 1),
             (0, 1, 1),
             (1, 1, 0))

    val I = ((1, 0, 1),
             (1, 1, 1))

    val J = ((1),
             (1),
             (1),
             (1))

    val K = ((1, 1),
             (1, 1))

    val L = ((0, 1, 0),
             (1, 1, 1),
             (0, 1, 0))


  }
}
