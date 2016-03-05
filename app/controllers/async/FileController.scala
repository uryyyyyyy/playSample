package controllers.async

import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.{Inject, _}

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{Action, Controller}

@Singleton
class FileController @Inject()(system: ActorSystem) extends Controller {

  val actor = system.actorOf(Props[FileActor])
  implicit val timeout = Timeout(1000, TimeUnit.MILLISECONDS)

  def download() = Action.async { request =>
    (actor ? "hello" ).mapTo[File].map { message =>
      Ok.sendFile(message)
    }
  }

  // cannot use async in Action.apply(anyContext)?
//  def upload() = Action(parse.multipartFormData) { request =>
//    request.body.file("file").map { picture =>
//      (actor ? "hello" ).mapTo[File].map { message =>
//        Ok("File uploaded")
//      }
//    }.getOrElse {
//      Future.successful(BadRequest)
//    }
//  }
}