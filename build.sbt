ThisBuild / scalaVersion := "2.13.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "some-api-with-depends"
  )
  .aggregate(specs, moduleA, moduleB)

lazy val specs = (project in file("specs"))
  .enablePlugins(Smithy4sCodegenPlugin)
  .settings(
    name := "specs",
    libraryDependencies ++= Seq(
      "com.disneystreaming.smithy4s" %% "smithy4s-core" % smithy4sVersion.value
    )
  )
lazy val moduleA = (project in file("module-a"))
  .dependsOn(specs)
  .settings(
    name := "module-a",
    libraryDependencies ++= Seq(
      "com.disneystreaming.smithy4s" %% "smithy4s-http4s" % smithy4sVersion.value,
      "com.disneystreaming.smithy4s" %% "smithy4s-http4s-swagger" % smithy4sVersion.value,
      "org.http4s" %% "http4s-ember-server" % "0.23.11"
    )
  )

lazy val moduleB = (project in file("module-b"))
  .dependsOn(specs)
  .settings(
    name := "module-b",
    libraryDependencies ++= Seq(
      "com.disneystreaming.smithy4s" %% "smithy4s-http4s" % smithy4sVersion.value,
      "com.disneystreaming.smithy4s" %% "smithy4s-http4s-swagger" % smithy4sVersion.value,
      "org.http4s" %% "http4s-ember-server" % "0.23.11"
    )
  )
