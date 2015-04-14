package controllers

import anorm._
import model.{CustomersDAO, Customer}
import play.api._
import play.api.data.Form
import play.api.db.DB
import play.api.mvc._
import play.api.data.Forms._


object Application extends Controller {

  DB.withConnection { implicit connection =>
    try {
      SQL("CREATE TEABLE CUSTOMERS(NAME VARCHAR(255) PRIMARY KEY, AGE INT);").executeUpdate()
    } catch { case _: Exception => () }
  }(play.api.Play.current)

  def index = Action {
    // Ok("Hello")
    Ok(views.html.index("Your new application is ready."))
  }

  def addCustomer = Action {
    implicit request =>
    customersForm.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(views.html.addCustomer(formWithErrors))
      },
      userData => {
        /* binding success, you get the actual value. */
        val newCustomer = model.Customer(userData.name, userData.age)
        model.CustomersDAO.addCustomer(newCustomer)
        Redirect(routes.Application.index())
      }
    )
  }

  val customersForm: Form[Customer] = Form(
    mapping(
      "name"  -> nonEmptyText,
      "age"   -> number
    )(Customer.apply)(Customer.unapply)
  )
}
