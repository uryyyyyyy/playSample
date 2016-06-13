package controllers

import Model.Account
import jp.t2v.lab.play2.auth.LoginLogout
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import views.html

import scala.concurrent.Future

object Application extends Controller with LoginLogout with AuthConfigImpl {

	import scala.concurrent.ExecutionContext.Implicits.global

	/** Your application's login form.  Alter it to fit your application */
	val loginForm = Form {
		mapping("email" -> email, "password" -> text)(Account.authenticate)(_.map(u => (u.id, "")))
				.verifying("Invalid email or password", result => result.isDefined)
	}

	/** Alter the login page action to suit your application. */
	def login = Action { implicit request =>
		Ok(html.login(loginForm))
	}

	/**
		* Return the `gotoLogoutSucceeded` method's result in the logout action.
		*
		* Since the `gotoLogoutSucceeded` returns `Future[Result]`,
		* you can add a procedure like the following.
		*
		*   gotoLogoutSucceeded.map(_.flashing(
		*     "success" -> "You've been logged out"
		*   ))
		*/
	def logout = Action.async { implicit request =>
		// do something...
		gotoLogoutSucceeded
	}

	/**
		* Return the `gotoLoginSucceeded` method's result in the login action.
		*
		* Since the `gotoLoginSucceeded` returns `Future[Result]`,
		* you can add a procedure like the `gotoLogoutSucceeded`.
		*/
	def authenticate = Action.async { implicit request =>
		loginForm.bindFromRequest.fold(
			formWithErrors => Future.successful(BadRequest(html.login(formWithErrors))),
			user => gotoLoginSucceeded(user.get.id)
		)
	}

}