ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "mars_rover"
  )
libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-effect" % "3.3.14" withSources () withJavadoc (),
  "org.scalameta" %% "munit"       % "0.7.29" % Test,
  "com.beachape" %% "enumeratum" % "1.7.0"
)
