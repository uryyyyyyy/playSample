package controllers

import play.api.libs.iteratee.Enumerator
import play.api.mvc.{Action, Controller}

class ChunkController extends Controller {

  def download = Action {
    Ok.chunked(
      Enumerator("kiki", "foo", "bar").andThen(Enumerator.eof)
    )
  }

  def upload = Action(parse.multipartFormData) { request =>
    Ok("File uploaded")
  }
}