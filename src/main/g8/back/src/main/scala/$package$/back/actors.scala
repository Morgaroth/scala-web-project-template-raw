package $package$.back

import $package$.codebase.actors.TodoRepository

trait actors {
  this: HasActorSystem =>

  lazy val repository = actorSystem.actorOf(TodoRepository.props, "repository")

}
