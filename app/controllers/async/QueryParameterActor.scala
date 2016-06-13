package controllers.async

import akka.actor.Actor
import model.MyObj
import play.api.libs.json.Json

class QueryParameterActor extends Actor {

  def receive = {
    case name: String => {
      sender() !  MyObj(1, name)
    }

    case id: Int => {
      sender() ! MyObj(id, "params")
    }
  }
}