package model

/**
 * Created by David on 6. 7. 2015.
 */
trait ArrayPrinter
{
  val array: Array[Array[Int]]

  override def toString: String = {
    val builder = new StringBuilder()
    array.foreach(row => {
      row.foreach(char => {
        builder.append(char)
      })
      builder.append('\n')
    })
    builder toString
  }
}
