package model.qiita

import play.api.libs.json.Json

case class QiitaUser(id:String, name:String)

object QiitaUser {
  //these implicit serve convert(Json <-> ScalaObject)
  implicit val writes = Json.writes[QiitaUser]
  implicit val reads = Json.reads[QiitaUser]
}