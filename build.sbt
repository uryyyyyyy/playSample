name := """play2Sample"""

version := "1.0"
organization := "com.github.uryyyyyyy"
scalaVersion := "2.11.8"

resolvers ++= Seq(
  "Sonatype snapshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
)

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "io.swagger" %% "swagger-play2" % "1.5.1",
  "jp.t2v" %% "stackable-controller" % "0.6.0",
  "jp.t2v" %% "play2-auth"        % "0.14.2",
  "jp.t2v" %% "play2-auth-social" % "0.14.2",
  "jp.t2v" %% "play2-auth-test"   % "0.14.2" % "test",
  play.sbt.Play.autoImport.cache
)

routesGenerator := InjectedRoutesGenerator

enablePlugins(PlayScala)