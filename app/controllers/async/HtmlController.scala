package controllers.async

import scala.concurrent.Future

import play.api._
import play.api.mvc._

class HtmlController extends Controller {

  def index = Action.async {
    Future.successful(Ok(views.html.index("Your new application is ready.")))
  }

}
