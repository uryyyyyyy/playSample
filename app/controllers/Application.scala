package controllers

import javax.inject.Inject

import play.api.Logger
import play.api.libs.ws.WSClient
import play.api.mvc.{Action, Controller}
import play.libs.Json.toJson

import scala.collection.JavaConverters.mapAsJavaMapConverter
import scala.concurrent.Await
import scala.concurrent.duration.Duration

class Application @Inject()(ws: WSClient) extends Controller {

  def clientID = ""

  def clientSecret = ""

  def showSignIn = Action { implicit request =>
    // GitHubで割り当てられた「client_id」を application.conf から取得し、ビューに渡します。
    Ok(views.html.signinform(clientID))
  }

  def callBackGitHub(code: String) = Action { implicit request =>

    // Play 標準の Json ライブラリである Jackson を使って、リクエストボディを作成します。
    // Jackson は Java のライブラリのため、toJson の引数に Map を使うときは
    // Java の Map に変換しています。
    val postBodyMap = Map(
      "client_id" -> toJson(clientID),
      "client_secret" -> toJson(clientSecret),
      "code" -> toJson(code)
    ).asJava

    val postBodyJson = toJson(postBodyMap)
    Logger.debug("postBody=" + postBodyJson)

    val responce = ws.url("https://github.com/login/oauth/access_token")
      .withHeaders("Accept" ->  "application/json")
      .post(postBodyJson)
    val ss = Await.result(responce, Duration.Inf)
    println("response=" + ss.json)

    // ログインする GitHub ユーザの情報を取得するためのアクセストークンが取得できました。
    println(ss.json)

    Ok

    //    // アクセストークンを使って GitHub にアクセスし、ユーザー情報を取得します。
    //    val user = ws.url("https://api.github.com/user")
    //      .setQueryParameter("access_token", accessToken)
    //      .get
    //      .get.asJson
    //    Logger.debug("userJson=" + user)

    // 任意の必要項目を取得し、GitHubUser クラスに変換しています。
    //    val githubUser = GitHubUser(
    //      user.get(GitHubUserAPI.ID.toString).asLong,
    //      user.get(GitHubUserAPI.Login.toString).asText,
    //      user.get(GitHubUserAPI.Name.toString).asText,
    //      user.get(GitHubUserAPI.Email.toString).asText,
    //      new URL(user.get(GitHubUserAPI.AvatarURL.toString).asText))
    //    Logger.info("GitHubUser=" + githubUser)
    //
    //    // DBに保存しています。
    //    // ユーザ情報は変わっているかもしれないので、初回は INSERT、2回目以降は UPDATE します。
    //    GitHubUser.saveOrUpdate(githubUser)
    //
    //    // セッションにログイン中のユーザ情報(ここではGitHubのID)を保存してログイン状態にしてから、
    //    // 適当なページにリダイレクトします。
    //    Redirect(routes.Programs.listPrograms).withSession(
    //      "githubID" -> githubUser.id.toString)
  }

  def signOut = Action {
    // セッション情報をクリアし、ログアウト状態にしてからログインページにリダイレクトします。
    //    Redirect(routes.Application.showSignIn).withNewSession.flashing(
    //      "success" -> "You are now logged out."
    //    )
    Ok
  }
}