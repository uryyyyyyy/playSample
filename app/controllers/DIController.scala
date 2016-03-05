package controllers

import javax.inject.{Inject, Singleton}

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.spec.WSService
import play.api.libs.concurrent.Execution.Implicits.defaultContext

@Singleton
class DIController @Inject()(service: WSService) extends Controller {
  //this inject target is configured by modules.*DIModule

  def getUsers() = Action.async {
    service.getUsers().map(v => Ok(Json.toJson(v)))
  }
}