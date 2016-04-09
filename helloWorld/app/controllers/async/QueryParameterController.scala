package controllers.async

import java.util.concurrent.TimeUnit
import javax.inject.{Inject, _}

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import model.MyObj
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

@Singleton
class QueryParameterController @Inject()(system: ActorSystem) extends Controller {

  val helloActor = system.actorOf(Props[QueryParameterActor])
  implicit val timeout = Timeout(1000, TimeUnit.MILLISECONDS)

  def getWithQuery(str: String) = Action.async { request =>
    (helloActor ? str ).mapTo[MyObj].map { message =>
      Ok(Json.toJson(message))
    }
  }

  def getWithParams(id: Int) = Action.async { request =>
    (helloActor ? id ).mapTo[MyObj].map { message =>
      Ok(Json.toJson(message))
    }
  }
}