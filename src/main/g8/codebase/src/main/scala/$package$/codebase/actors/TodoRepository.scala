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
    case class PushResult(todo: Todo)
    case class PullTodo(todo: Todo)
    case class PullResult(todo: Todo, wasInRepository: Boolean)
    case object GetTodos
  }
  //@formatter:on

  def props = Props(classOf[TodoRepository])
}

class TodoRepository extends Actor with ActorLogging {

  val repository = mutable.Set.empty[Todo]

  def receive = LoggingReceive {
    case PushTodo(todo) =>
      repository += todo
      sender ! Success(PushResult(todo))
      log.info(s"todo \$todo pushed into repository")
    case PullTodo(todo) =>
      val wasInRepo = repository.remove(todo)
      sender() ! Success(PullResult(todo, wasInRepo))
      log.info(s"todo \$todo removed from repository (was in repo: \$wasInRepo")
    case GetTodos =>
      sender() ! repository.toSet
      log.info(s"sent repository to actor \${sender()}")
  }

}
