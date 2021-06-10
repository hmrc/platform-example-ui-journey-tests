#!/bin/bash -e
ENV=${1:-local}
BROWSER=${2:-chrome}
DRIVER=

if [ "$BROWSER" = "chrome" ]; then
    DRIVER="-Dwebdriver.chrome.driver=/usr/local/bin/chromedriver"
elif [ "$BROWSER" = "firefox" ]; then
    DRIVER="-Dwebdriver.gecko.driver=/usr/local/bin/geckodriver"
fi

# Scalafmt checks have been separated from the test command to avoid OutOfMemoryError in Jenkins
sbt scalafmtCheckAll scalafmtSbtCheck

sbt -Dbrowser="$BROWSER" -Denvironment="$ENV" $DRIVER "testOnly uk.gov.hmrc.test.ui.specs.*"
