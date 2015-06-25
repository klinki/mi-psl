package model

import javafx.geometry.Point2D

/**
 * Created by David on 14. 6. 2015.
 */
abstract class Piece(val array: Array[Array[Int]], val position: Point2D = new Point2D(0,0))
{
  def width = array.length
  def height = array(0).length

  def rotate: Piece
  def reverse: Piece

  def getAllVariants: Seq[Piece]
}
