package $package$.codebase.actors

import $package$.codebase.Todo
import $package$.codebase.actors.TodoRepository.messages._
import akka.actor._
import akka.event._

import scala.collection.mutable
import scala.util.Success


object TodoRepository {

  //@formatter:off
  object messages {
    case class PushTodo(todo: Todo)
    case class PullTodo(id: String)
    case class PullResult(maybeTodo: Option[Todo])
    case object GetTodos
    case class GetTodo(id: String)
  }
  //@formatter:on

  def props = Props(classOf[TodoRepository])
}

class TodoRepository extends Actor with ActorLogging {

  val repository = mutable.Map.empty[String, Todo]

  def receive = LoggingReceive {
    case PushTodo(todo) =>
      repository += todo.id -> todo
      sender ! Success(todo)
      log.info(s"todo \$todo pushed into repository")
    case PullTodo(id) =>
      val previously = repository.remove(id)
      sender() ! Success(PullResult(previously))
      log.info(s"todo of id \$id removed from repository (was in repo: \${previously.isDefined}")
    case GetTodo(id) =>
      val result = repository.get(id)
      sender() ! Success(result)
      log.info(s"todo \$id returned to \${sender()}")
    case GetTodos =>
      sender() ! Success(repository.values.toList)
      log.info(s"sent repository to actor \${sender()}")
  }

}
