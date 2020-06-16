ThisBuild / scalaVersion := "2.12.10"
ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(name := "hello")
libraryDependencies += "com.twitter" %% "util-core" % "19.8.0"
libraryDependencies += "com.twitter" %% "inject-core" % "19.8.0"
libraryDependencies += "com.twitter" %% "inject-app" % "19.8.0"
libraryDependencies += "com.twitter" %% "finagle-http" % "19.8.0"
libraryDependencies += "com.twitter" %% "finatra-http" % "19.8.0"
