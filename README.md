# platform-example-ui-journey-tests

Example UI journey tests using ScalaTest.

## Initial Setup

Run the following commands to start services locally:

```bash
docker run --rm -d --name mongo -d -p 27017:27017 mongo:4.0
sm --start PLATFORM_EXAMPLE_UI_TESTS -r --wait 100
```

Using the `--wait 100` argument ensures a health check is run on all the services started as part of the profile. `100` refers to the given number of seconds to wait for services to pass health checks.

## Running Tests - on a developer machine

### Example Spec

The tests can be run against Chrome or Firefox.

To run locally using a local ChromeDriver installation, use the command (note this is only for Mac and Linux OS):

```bash
./run_tests.sh local chrome
```

To start a Chrome or Firefox docker container use `run-browser-with-docker.sh` script. The script requires
`remote-chrome` or `remote-firefox` as an argument.

To run against a containerised Chrome browser:

```bash
./run-browser-with-docker.sh remote-chrome
./run_tests.sh local remote-chrome
```

Read more about the `run-browser-with-docker.sh`'s functionality [here](run-browser-with-docker.sh)

### ZAP Spec

Running ZAP spec requires ZAP tool be running at port 11000.
See [running ZAP
locally](https://github.com/hmrc/dast-config-manager#running-zap-locally)
section in [dast-config-manager](https://github.com/hmrc/dast-config-manager)
repository.

```bash
./run-browser-with-docker.sh remote-chrome
./run_zap_tests.sh
```

## Running Tests - in CI

### Example Spec

Jenkins, our Continuous Integration environment, supports only containerised Chrome and Firefox browsers.
 [BrowserTestsJobBuilder](https://github.com/hmrc/build-jobs/blob/master/src/main/groovy/uk/gov/hmrc/buildjobs/domain/builder/BrowserTestsJobBuilder.groovy)
 takes care of starting a remote-chrome or remote-firefox sidecar container in CI.
 So it is not required to start the browser container when running the tests in CI.

```bash
./run_tests.sh <local|dev|qa|staging> remote-chrome
```

### ZAP Spec

[DynamicApplicationSecurityTestingJobBuilder](https://github.com/hmrc/build-jobs/blob/master/src/main/groovy/uk/gov/hmrc/buildjobs/domain/builder/DynamicApplicationSecurityTestingJobBuilder.groovy)
takes care of starting a browser and ZAP sidecar container in CI.
So it is not required to start neither the browser nor ZAP when running the tests in CI.

```bash
./run_zap_tests.sh
```

## RemoteWebDriver implementation

This test suite's RemoteWebdriver implementation makes use of HMRC's [webdriver-factory](https://github.com/hmrc/webdriver-factory) library.

```scala
import uk.gov.hmrc.webdriver.SingletonDriver
implicit lazy val webDriver: WebDriver = SingletonDriver.getInstance()
```

Additional details on the functionality provided by `webdriver-factory` can be found in the [README](https://github.com/hmrc/webdriver-factory/blob/master/README.md).

## Scalafmt

This repository uses [Scalafmt](https://scalameta.org/scalafmt/), a code formatter for Scala. The formatting rules configured for this repository are defined within [.scalafmt.conf](.scalafmt.conf).

To apply formatting to this repository using the configured rules in [.scalafmt.conf](.scalafmt.conf) execute:

```bash
sbt scalafmtAll
```

 To check files have been formatted as expected execute:

```bash
sbt scalafmtCheckAll scalafmtSbtCheck
```

[Visit the official Scalafmt documentation to view a complete list of tasks which can be run.](https://scalameta.org/scalafmt/docs/installation.html#task-keys)
