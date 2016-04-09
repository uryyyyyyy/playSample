name := """playSample"""

version := "1.0"

resolvers ++= Seq(
	"Sonatype snapshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/",
	"scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
)

lazy val commonSettings = Seq(
	organization := "com.github.uryyyyyyy",
	scalaVersion := "2.11.7",
	libraryDependencies ++= Seq(
	)
)

lazy val helloWorld = (project in file("helloWorld")).
		settings(commonSettings: _*).enablePlugins(PlayScala)

lazy val auth = (project in file("auth")).
		settings(commonSettings: _*).enablePlugins(PlayScala)

lazy val stackable = (project in file("stackable")).
		settings(commonSettings: _*).enablePlugins(PlayScala)
