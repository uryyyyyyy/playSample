package controllers

import model.MyObj
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import MyObj._

class JsonController extends Controller {

  def get() = Action { request =>
    val obj1 = MyObj(1, "uryyyyyyy")
    val obj2 = MyObj(2, "wasabi")
    Ok(Json.toJson(Seq(obj1, obj2)))
  }

  def post() = Action { request =>

    val myObjOpt = for{
      json <- request.body.asJson
      myObj <- Json.fromJson[MyObj](json).asOpt
    }yield myObj

    myObjOpt match{
      case None => BadRequest(Json.toJson("illegal"))
      case Some(v) => Ok(Json.toJson(v))
    }
  }
}