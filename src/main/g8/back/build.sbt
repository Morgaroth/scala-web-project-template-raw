name := """$name$-server"""

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-experimental" % "$akka_http_version$",
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "$akka_http_version$",
  "io.github.morgaroth" %% "akka-http-joda-time" % "$joda_time_supp_version$"
)