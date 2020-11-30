#!/bin/bash -e

BROWSER_TYPE=$1
ENV=$2

if [ -z "$BROWSER_TYPE" ]; then
    echo "BROWSER_TYPE value not set. This should be passed as a parameter"
    exit 1
fi

# Scalafmt checks have been separated from the test command to avoid OutOfMemoryError in Jenkins
sbt scalafmtCheckAll scalafmtSbtCheck

sbt -Dbrowser=${BROWSER_TYPE} -Denv=${ENV:=local} "testOnly uk.gov.hmrc.test.ui.specs.*"
