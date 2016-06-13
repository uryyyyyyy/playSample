package controllers

import java.io.{File, PrintWriter}

import play.api.mvc.{Action, Controller}

class logicController extends Controller {

  def download() = Action {
    val tempFile = File.createTempFile("prefix_", ".csv")

    val file = new PrintWriter(tempFile)
    file.write("１行目\n")
    file.write("２行目\n")
    file.write("３行目\n")
    file.close()
    Ok.sendFile(tempFile)
  }

  def upload = Action(parse.multipartFormData) { request =>
    request.body.file("file").map { picture =>
      val filename = picture.filename
      val contentType = picture.contentType
      picture.ref.moveTo(new File(s"$filename"))
      Ok("File uploaded")
    }.getOrElse {
      BadRequest
    }
  }
}