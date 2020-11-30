/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.ui.specs

import uk.gov.hmrc.test.ui.pages.HomePage
import uk.gov.hmrc.test.ui.specs.tags.ZapTests

class ExampleSpec extends BaseSpec {

  Feature("Examples") {

    Scenario("Example Journey With Error Pages", ZapTests) {

      Given("Example page is loaded")

      go to HomePage
      pageTitle shouldBe HomePage.title

      When("Click continue without selecting vat return period should return an error")
      pageTitle                                should be("Enter your VAT return details")
      click on "continue-button"
      id("error-summary-heading").element.text should be("There are errors on this page")

      Then("Select VAT return period and click continue should go to the next page")
      click on "vatReturnPeriod-annually"
      click on "continue-button"
      pageTitle should be("Enter your turnover")

      When("Click continue without entering amount return an error summary")
      click on "continue-button"
      id("error-summary-heading").element.text should be("There are errors on this page")

      When("Enter an amount and click continue")
      textField("turnover").value = "1000"
      click on "continue-button"
      pageTitle should be("Enter your cost of goods")

      When("Click continue without entering cost should return an error summary")
      click on "continue-button"
      id("error-summary-heading").element.text should be("There are errors on this page")
    }

  }
}
