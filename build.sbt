name := """playSample"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "org.twitter4j" % "twitter4j-core" % "4.0.4",
  "org.pac4j" % "play-pac4j_scala2.11" % "1.4.0",
  "org.pac4j" % "pac4j-oauth" % "1.8.6"
)

resolvers ++= Seq(
  "Sonatype snapshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
