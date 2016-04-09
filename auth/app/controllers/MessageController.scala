package controllers

import Model.{Administrator, NormalUser}
import jp.t2v.lab.play2.auth.AuthElement
import play.api.mvc._
import views.html

class MessageController extends Controller with AuthElement with AuthConfigImpl {

	// The `StackAction` method
	//    takes `(AuthorityKey, Authority)` as the first argument and
	//    a function signature `RequestWithAttributes[AnyContent] => Result` as the second argument and
	//    returns an `Action`

	// The `loggedIn` method
	//     returns current logged in user

	def main = StackAction(AuthorityKey -> NormalUser) { implicit request =>
		val user = loggedIn
		val title = "message main"
		Ok(html.index(title))
	}

	def list = StackAction(AuthorityKey -> NormalUser) { implicit request =>
		val user = loggedIn
		val title = "all messages"
		Ok(html.index(title))
	}

	def detail(id: Int) = StackAction(AuthorityKey -> NormalUser) { implicit request =>
		val user = loggedIn
		val title = "messages detail "
		Ok(html.index(title + id))
	}

	// Only Administrator can execute this action.
	def write = StackAction(AuthorityKey -> Administrator) { implicit request =>
		val user = loggedIn
		val title = "write message"
		Ok(html.index(title))
	}

}