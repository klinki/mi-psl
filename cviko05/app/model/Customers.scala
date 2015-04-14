package model

import anorm._
import play.api.db.DB

/**
 * Created by klingdav on 3/31/15.
 */
case class Customer(name: String, age: Int)

object CustomersDAO {
  val c = Customer("Jan Noval", 25)
  var customers = Map(c.name -> c)

  def getAll = customers.values

  def addCustomer(customer: Customer): Unit = {
    customers = customers + (customer.name -> c)
  }

  def add(c: Customer): Unit = {
    DB.withConnection { implicit connection =>
      val insertCustomer = SQL("INSERT INTO CUSTOMERS VALUES({name}, {age})").on("name" -> c.name, "age" -> c.age)
      insertCustomer.executeUpdate();
    }
  }

  def all: List[Customer] = {
    DB.withConnection { implicit connection =>
      val selCustomers = SQL("SELECT * FROM CUSTOMERS")
      val qResult = selCustomers.executeQuery()
      qResult().map(
        row => Customer(row[String]("name"), row[Int]("age"))
      ).toList
    }
  }

}
