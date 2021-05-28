import sbt._

object Dependencies {

  val test = Seq(
    "uk.gov.hmrc"         %% "webdriver-factory" % "0.20.0"  % Test,
    "org.scalatest"       %% "scalatest"         % "3.2.0"   % Test,
    "org.scalatestplus"   %% "selenium-3-141"    % "3.2.0.0" % Test,
    "com.vladsch.flexmark" % "flexmark-all"      % "0.35.10" % Test,
    "org.pegdown"          % "pegdown"           % "1.2.1"   % Test,
    "uk.gov.hmrc"         %% "zap-automation"    % "2.9.0"   % Test,
    "com.typesafe"         % "config"            % "1.3.2"   % Test
  )

}
