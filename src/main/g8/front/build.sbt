import org.scalajs.sbtplugin.ScalaJSPlugin.AutoImport._

name := """$name$-front"""

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-unchecked", "-deprecation"
  , "-encoding", "utf8"
  , "-Xelide-below", annotation.elidable.ALL.toString
)

libraryDependencies ++= Seq(
  "io.github.widok" %%%! "widok" % "$widok_version$"
)

persistLauncher := true