name := "easy-checkout"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "org.scalamock" %% "scalamock" % "4.1.0" % Test,
  "org.scalacheck" %% "scalacheck" % "1.14.0" % Test,
)
