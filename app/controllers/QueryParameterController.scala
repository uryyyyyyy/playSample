package controllers

import model.MyObj
import MyObj._
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

class QueryParameterController extends Controller {

  def getWithQuery(str: String) = Action { request =>
    val obj1 = MyObj(1, str)
    Ok(Json.toJson(obj1))
  }

  def getWithParams(id: Int) = Action { request =>
    val obj1 = MyObj(id, "params")
    Ok(Json.toJson(obj1))
  }
}