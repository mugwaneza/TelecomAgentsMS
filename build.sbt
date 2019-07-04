name := "y"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
"org.mindrot" % "jbcrypt" % "0.3m"
)     

play.Project.playJavaSettings

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.38"
