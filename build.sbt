name := "scalaray"

version := "0.1"

scalaVersion := "2.9.1"

resolvers ++= Seq("Codahale" at "http://repo.codahale.com")

libraryDependencies ++= Seq("com.codahale" %% "jerkson" % "0.5.0")

libraryDependencies ++= Seq("org.scala-lang" % "scala-swing" % "2.9.1")

scalaSource in Compile <<= baseDirectory / "src"
