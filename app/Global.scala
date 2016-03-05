import model.TwitterAuthConfig
import org.pac4j.core.client.Clients
import org.pac4j.oauth.client.TwitterClient
import org.pac4j.play.Config
import play.api.GlobalSettings
import play.api.mvc.{Result, RequestHeader, Results}

import scala.concurrent.Future

object Global extends GlobalSettings with TwitterAuthConfig {

  override def onError(request: RequestHeader, ex: Throwable): Future[Result] = {
    Future.successful(Results.InternalServerError)
  }

  override def onStart(app: play.api.Application): Unit = {
    val ti = consumer
    val tc = new TwitterClient(ti.key, ti.secret)
    val cl = new Clients(callback, tc)
    Config.setClients(cl)
  }
}