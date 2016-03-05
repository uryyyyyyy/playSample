package controllers

import javax.inject.Inject

import play.api.libs.ws.WSClient
import play.api.mvc.{Action, Controller}
import play.libs.Json.toJson

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

    println("start")
    // Play 標準の Json ライブラリである Jackson を使って、リクエストボディを作成します。
    // Jackson は Java のライブラリのため、toJson の引数に Map を使うときは
    // Java の Map に変換しています。
    import play.api.libs.json._
    val postBodyJson = Json.obj(
      "client_id" -> toJson(clientID),
      "client_secret" -> toJson(clientSecret),
      "code" -> toJson(code)
    )
    println("postBody=" + postBodyJson)

    val responce = ws.url("https://github.com/login/oauth/access_token")
      .withHeaders("Content-Type" ->  "application/json")
      .withHeaders("Accept" ->  "application/json")
      .post(postBodyJson.toString)
    val ss = Await.result(responce, Duration.Inf)

    // ログインする GitHub ユーザの情報を取得するためのアクセストークンが取得できました。
    println(ss.json)
    val accessToken = (ss.json \ "access_token").asOpt[String].get
    println(accessToken)

    // アクセストークンを使って GitHub にアクセスし、ユーザー情報を取得します。
    val sss = ws.url("https://api.github.com/user")
      .withQueryString("access_token" ->  accessToken)
      .withHeaders("Accept" ->  "application/json")
      .get
    val user = Await.result(sss, Duration.Inf).json
    println(user)

    Ok((user \ "login").asOpt[String].get)



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