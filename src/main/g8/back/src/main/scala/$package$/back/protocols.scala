package $package$.back

import $package$.codebase.{Todo, TodoPost}
import io.github.morgaroth.utils.akka.http.jodatime.JsonJodaTimeProtocol._
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait TodoProtocol extends DefaultJsonProtocol {
  implicit val todoJsonProtocol: RootJsonFormat[Todo] = jsonFormat4(Todo.apply)
}

trait PostTodoProtocol extends DefaultJsonProtocol {
  implicit val todoPostJsonProtocol: RootJsonFormat[TodoPost] = jsonFormat3(TodoPost.apply)
}

trait protocols extends TodoProtocol with PostTodoProtocol

object protocols extends protocols
