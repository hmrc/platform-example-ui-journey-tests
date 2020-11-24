package uk.gov.hmrc.test.ui.pages

import org.scalatest.matchers.should.Matchers
import org.scalatestplus.selenium.{Page, WebBrowser}

trait BasePage extends Matchers with Page with WebBrowser {

  val url: String

}
