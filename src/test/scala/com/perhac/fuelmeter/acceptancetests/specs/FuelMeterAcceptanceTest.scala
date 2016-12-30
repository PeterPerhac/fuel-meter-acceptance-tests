package com.perhac.fuelmeter.acceptancetests.specs

import com.perhac.fuelmeter.acceptancetests.pages.HomePage
import com.perhac.fuelmeter.acceptancetests.{BaseUrl, FuelMeterSuite}
import org.openqa.selenium.WebDriver
import org.scalatest._
import org.scalatest.selenium.WebBrowser
import org.slf4j.{Logger, LoggerFactory}

abstract class FuelMeterAcceptanceTest extends FlatSpec with Matchers with OptionValues with Inside with Inspectors
  with BeforeAndAfterEach with WebBrowser {

  lazy val logger: Logger = LoggerFactory.getLogger(this.getClass)

  implicit lazy val baseUrl: BaseUrl = System.getProperty("fuel-meter.base.url", "http://localhost:9000")

  implicit lazy val webDriver: WebDriver = FuelMeterSuite.driver

  lazy val homePage = new HomePage(baseUrl)

  val defaultReg = "TE5TR3G"

  implicit class TextMatcher(el: Option[Element]) {
    def textShouldBe(expectedString: String): Unit = el.map(_.text) shouldBe Some(expectedString)
  }

  private def displayedErrors() = findAll(cssSelector("span.error")).withFilter(_.isDisplayed)

  def errorsOnPage(): List[String] = {
    displayedErrors().map(_.text).toList
  }

  def assertNoErrorsOnPage(): Unit = {
    displayedErrors() shouldBe empty
  }

}
