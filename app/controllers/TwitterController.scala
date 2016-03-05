package controllers

import java.io.{File, PrintWriter}
import javax.inject.Inject

import com.google.inject.Singleton
import play.api.libs.oauth.{ConsumerKey, RequestToken, OAuthCalculator}
import play.api.libs.ws.WSClient
import play.api.mvc.{RequestHeader, Action, Controller}
import service.spec.WSService

import scala.concurrent.Future

@Singleton
class TwitterController @Inject()(wsClient: WSClient) extends Controller {

  val KEY = ConsumerKey("xxxxx", "xxxxx")

  def sessionTokenPair(implicit request: RequestHeader): Option[RequestToken] = {
    for {
      token <- request.session.get("token")
      secret <- request.session.get("secret")
    } yield {
      RequestToken(token, secret)
    }
  }

  def timeline = Action.async { implicit request =>
    sessionTokenPair match {
      case Some(credentials) => {
        wsClient.url("https://api.twitter.com/1.1/statuses/home_timeline.json")
          .sign(OAuthCalculator(KEY, credentials))
          .get
          .map(result => Ok(result.json))
      }
      case _ => Future.successful(Redirect(routes.Application.authenticate))
    }
  }
}