name := """$name$"""

scalaVersion := "2.11.6"

lazy val codebase = project
lazy val front = project.enablePlugins(ScalaJSPlugin).dependsOn(codebase)
lazy val back = project.dependsOn(codebase)