ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "mars_rover"
  )

libraryDependencies += "org.scalameta" %% "munit"               % "0.7.29" % Test
libraryDependencies += "org.typelevel" %% "cats-effect"         % "3.3.14" withSources () withJavadoc ()
libraryDependencies += "org.typelevel" %% "munit-cats-effect-3" % "1.0.6" % Test
libraryDependencies += "org.typelevel" %% "cats-free"           % "2.8.0"
