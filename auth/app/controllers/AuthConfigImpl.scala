package controllers

// Example
import Model.{Account, Administrator, NormalUser, Role}
import jp.t2v.lab.play2.auth._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}
import scala.reflect._
import play.api.mvc.Results._
import views.html
import play.api.http.Status._

trait AuthConfigImpl extends AuthConfig {

	override type Id = String

	override type User = Account

	override type Authority = Role

	override val idTag: ClassTag[Id] = classTag[Id]

	override val sessionTimeoutInSeconds: Int = 3600

	override def resolveUser(id: Id)(implicit ctx: ExecutionContext): Future[Option[User]] = Account.findById(id)

	override def loginSucceeded(request: RequestHeader)(implicit ctx: ExecutionContext): Future[Result] =
		Future.successful(Redirect("/", MOVED_PERMANENTLY))

	override def logoutSucceeded(request: RequestHeader)(implicit ctx: ExecutionContext): Future[Result] =
		Future.successful(Redirect("/", MOVED_PERMANENTLY))

	override def authenticationFailed(request: RequestHeader)(implicit ctx: ExecutionContext): Future[Result] =
		Future.successful(Redirect("/", MOVED_PERMANENTLY))

	override def authorizationFailed(request: RequestHeader, user: User, authority: Option[Authority])(implicit context: ExecutionContext): Future[Result] = {
		Future.successful(Forbidden("no permission"))
	}

	override def authorize(user: User, authority: Authority)(implicit ctx: ExecutionContext): Future[Boolean] = Future.successful {
		(user.role, authority) match {
			case (Administrator, _)       => true
			case (NormalUser, NormalUser) => true
			case _                        => false
		}
	}

//	override lazy val tokenAccessor = new CookieTokenAccessor(
//		/*
//		 * Whether use the secure option or not use it in the cookie.
//		 * Following code is default.
//		 */
//		cookieSecureOption = play.api.Play.isProd(play.api.Play.current),
//		cookieMaxAge       = Some(sessionTimeoutInSeconds)
//	)

}