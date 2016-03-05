package controllers

import play.api.mvc._

class HtmlController extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}
