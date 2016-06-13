package controllers.oauth

import javax.inject.Inject

import play.api.libs.ws.WSClient
import play.api.mvc.{Action, Controller}
import play.libs.Json.toJson

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class TwitterOauthController @Inject()(ws: WSClient) extends Controller {

  def clientID = ""

  def clientSecret = ""

  def showSignIn = Action { implicit request =>
    val opt = request.session.get("access_token")

    Ok(views.html.twitterSignin(clientID, opt))
  }

  def callBackGitHub(code: String) = Action { implicit request =>

    println("start")
    import play.api.libs.json._
    val postForm = Map(
      "client_id" -> Seq(clientID),
      "client_secret" -> Seq(clientSecret),
      "code" -> Seq(code),
      "grant_type" -> Seq("authorization_code"),
      "redirect_uri" -> Seq("http://localhost:9000/twitter/callback")
    )

//      Json.obj(
//      "client_id" -> toJson(clientID),
//      "client_secret" -> toJson(clientSecret),
//      "code" -> toJson(code),
//      "grant_type" -> "authorization_code",
//      "redirect_uri" -> "http://localhost:9000/twitter/callback"
//    )
    println("postBody=" + postForm)

    val responce = ws.url("https://accounts.google.com/o/oauth2/token")
      .withHeaders("Accept" ->  "application/json")
      .post(postForm)
    val ss = Await.result(responce, Duration.Inf)

    println(ss.json)
    val accessToken = (ss.json \ "access_token").asOpt[String].get
    println(accessToken)

    //WARNING: you should not use accessToken directly. it is just sample
    Redirect("/twitter/signin", MOVED_PERMANENTLY).withSession("access_token" -> accessToken)
  }

  def signOut = Action {
    Redirect("/twitter/signin").withNewSession.flashing(
      "success" -> "You are now logged out."
    )
  }
}
