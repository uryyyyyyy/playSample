package controllers

import model.TwitterAuthConfig
import org.pac4j.oauth.profile.twitter.TwitterProfile
import org.pac4j.play.scala.ScalaController
import play.api.mvc._
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken

import scala.collection.JavaConversions._

object Application extends ScalaController with TwitterAuthConfig {

  lazy val twitter = {
    val tw = TwitterFactory.getSingleton
    tw.setOAuthConsumer(consumer.key, consumer.secret)
    tw
  }

  def index = Action { request =>
    val newSession = getOrCreateSessionId(request)
    val content = Option(getUserProfile(request)).fold(getRedirectAction(request, newSession, "TwitterClient", "/").getLocation) { p =>
      val tp = p.asInstanceOf[TwitterProfile]
      val ac = new AccessToken(tp.getAccessToken, tp.getAccessSecret)

      twitter.setOAuthAccessToken(ac)
      twitter.getUserTimeline.map(_.getText).mkString("\n")
    }
    Ok(content)
  }
}