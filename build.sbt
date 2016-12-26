name := """fuel-meter-acceptance-tests"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.1.8",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.scalatest" %% "scalatest" % "3.0.1" % Test,
  "org.seleniumhq.selenium" % "selenium-java" % "2.35.0" % Test
)

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )
