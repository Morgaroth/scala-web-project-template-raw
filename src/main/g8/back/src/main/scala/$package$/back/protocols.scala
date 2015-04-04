package $package$.back

import $package$.codebase.{Todo, TodoPost}
import io.github.morgaroth.utils.akka.http.exception.ExceptionJsonProtocol
import io.github.morgaroth.utils.akka.http.jodatime.JsonJodaTimeProtocol._
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

import scala.language.implicitConversions

trait TodoProtocol extends DefaultJsonProtocol {
  implicit val todoJsonProtocol: RootJsonFormat[Todo] = jsonFormat4(Todo.apply)
}

trait PostTodoProtocol extends DefaultJsonProtocol {
  implicit val todoPostJsonProtocol: RootJsonFormat[TodoPost] = jsonFormat2(TodoPost.apply)
}

trait protocols
  extends TodoProtocol
  with PostTodoProtocol
  with ExceptionJsonProtocol

object protocols extends protocols
