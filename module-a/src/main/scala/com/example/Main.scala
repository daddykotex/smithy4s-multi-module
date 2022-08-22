package com.example

import smithy4s.hello._
import cats.effect._
import cats.implicits._
import org.http4s.implicits._
import org.http4s.ember.server._
import org.http4s._
import com.comcast.ip4s._
import smithy4s.http4s.SimpleRestJsonBuilder

object ModuleAImpl extends ModuleAService[IO] {
  def hello(
      name: String,
      email: Email,
      town: Option[String]
  ): IO[Greeting] = IO.pure {
    town match {
      case None    => Greeting(s"Hello $name (${email.value})!")
      case Some(t) => Greeting(s"Hello $name (${email.value}) from $t!")
    }
  }
}

object Routes {
  private val example: Resource[IO, HttpRoutes[IO]] =
    SimpleRestJsonBuilder.routes(ModuleAImpl).resource

  private val docs: HttpRoutes[IO] =
    smithy4s.http4s.swagger.docs[IO](ModuleAService)

  val all: Resource[IO, HttpRoutes[IO]] = example.map(_ <+> docs)
}

object Main extends IOApp.Simple {
  val run = Routes.all.flatMap { routes =>
    EmberServerBuilder
      .default[IO]
      .withPort(port"9000")
      .withHost(host"localhost")
      .withHttpApp(routes.orNotFound)
      .build
  }.useForever

}
