name := """$name$"""

scalaVersion := "2.11.6"

lazy val codebase = project
lazy val front = project.dependsOn(codebase)
lazy val back = project.dependsOn(codebase)