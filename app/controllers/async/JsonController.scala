package controllers.async

import java.util.concurrent.TimeUnit
import javax.inject.{Inject, _}

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class JsonController @Inject() (system: ActorSystem) extends Controller {

  val helloActor = system.actorOf(Props[JsonActor], "hello-actor")

  def get() = Action.async {
    implicit val timeout = Timeout(5000, TimeUnit.MILLISECONDS)
    (helloActor ? "hello" ).mapTo[String].map { message =>
      Ok(message)
    }
  }

  //TODO
  def post() = Action.async {
    implicit val timeout = Timeout(5000, TimeUnit.MILLISECONDS)
    (helloActor ? "hello" ).mapTo[String].map { message =>
      Ok(message)
    }
  }
}